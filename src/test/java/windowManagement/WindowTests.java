package windowManagement;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class WindowTests {

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
    public void WinTest() throws InterruptedException {
        driver.get("https://www.selenium.dev/");
        Thread.sleep(2000);
        String originalWindow = driver.getWindowHandle();
        assert driver.getWindowHandles().size() == 1;

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.navigate().to("https://www.google.com");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(numberOfWindowsToBe(2));


        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("https://www.amazon.in");
        Thread.sleep(2000);

        driver.switchTo().window(originalWindow);
    }

    @AfterEach
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
