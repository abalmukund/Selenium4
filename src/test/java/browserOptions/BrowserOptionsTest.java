package browserOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;

public class BrowserOptionsTest {

    WebDriver driver;

    @BeforeAll
    public static void setUpWebDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUpDriverInstance() throws MalformedURLException {
        driver = new ChromeDriver();
    }

    @Test
    public void browserOptionsTest() {
        driver.get("https://www.google.com");
    }

    @AfterEach
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
