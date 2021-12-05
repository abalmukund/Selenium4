package relativeLocators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class RelativeLocatorTest {

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
    public void RelativeLocTest() throws InterruptedException {
        driver.get("https://automationbookstore.dev/");
        WebElement firstBook = driver.findElement(By.cssSelector("#pid1_title"));
        WebElement theBookRightOf = driver.findElement(with(By.tagName("h2")).toRightOf(firstBook));
        System.out.println("The text is: " + theBookRightOf.getText());
        WebElement advSelBook = driver.findElement(By.cssSelector("#pid6_title"));
        WebElement theJavaTesterBook = driver.findElement(with(By.tagName("h2")).toLeftOf(advSelBook).below(firstBook));
        System.out.println("The text is: " + theJavaTesterBook.getText());
        Thread.sleep(2000);
    }

    @AfterEach
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
