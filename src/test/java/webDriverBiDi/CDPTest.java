package webDriverBiDi;

import com.google.common.net.MediaType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.remote.http.Contents;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CDPTest {

    WebDriver driver;

    @BeforeAll
    public static void setUpWebDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUpDriverInstance() {
        driver = new ChromeDriver();
    }

    @Test
    public void networkInterceptorTest() throws InterruptedException, IOException {
        Path path = Paths.get("src/main/resources/selenium-logo.png");
        byte[] logo = Files.readAllBytes(path);

        Route route = Route.matching(req -> req.toString().contains("jpg"))
                .to(() -> req -> new HttpResponse()
                        .addHeader("Content-Type", MediaType.PNG.toString())
                        .setContent(Contents.bytes(logo)));
        driver.get("https://automationbookstore.dev/");
        driver.manage().window().maximize();
        try (NetworkInterceptor interceptor = new NetworkInterceptor(driver, route)) {
            driver.get("https://automationbookstore.dev/");
        }
        Thread.sleep(5000);
    }

    @AfterEach
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
