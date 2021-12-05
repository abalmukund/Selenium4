package shadowDOM;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ShadowDOMTest {

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
    public void ShadowDomTest() throws InterruptedException {
        driver.get("https://books-pwakit.appspot.com/");
        WebElement shadowHost = driver.findElement(By.cssSelector("book-app"));
        WebElement shadowObject = shadowHost.getShadowRoot().findElement(By.cssSelector("#input"));
        shadowObject.sendKeys("Hello");
        Thread.sleep(2000);
    }

    @Test
    public void ShadowTest1() {
        driver.get("https://radogado.github.io/shadow-dom-demo/");
        WebElement shadowHost = driver.findElement(By.cssSelector("#app"));
        WebElement shadowObject = shadowHost.getShadowRoot().findElement(By.cssSelector("#container"));
        System.out.println("Text is:" + shadowObject.getText());
        WebElement anotherShadowObj = shadowHost.getShadowRoot().findElement(By.cssSelector("#container > p"));
        System.out.println("Text is:" + anotherShadowObj.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
