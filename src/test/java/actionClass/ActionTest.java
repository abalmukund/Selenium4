package actionClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionTest {
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
    public void actionClickTest() throws InterruptedException {
        driver.get("https://www.google.com");
        driver.findElement(By.xpath("//input[@name=\"q\"]")).sendKeys("panda");
        driver.findElement(By.xpath("//input[@name=\"q\"]")).submit();
        Actions action = new Actions(driver);
        action.click(driver.findElement(By.xpath("(//div[contains(text(),\"All\")])[2]"))); //has replaced moveToElement(onElement).click() {}
        Thread.sleep(2000);
        action.clickAndHold(driver.findElement(By.xpath("//h3[contains(text(),'Giant panda - Wikipedia')]"))); //has replaced moveToElement(onElement).clickAndHold()
        Thread.sleep(2000);
        action.release(); //which was ealier a part of org.openqa.selenium.interactions.ButtonReleaseAction class has now been moved to Action class
        Thread.sleep(2000);
        action.contextClick(driver.findElement(By.xpath("(//img)[1]"))); //has replaced moveToElement(onElement).contextClick()
        Thread.sleep(2000);
        action.doubleClick(driver.findElement(By.xpath("(//img)[1]"))); //has replaced moveToElement(element).doubleClick()
        Thread.sleep(2000);
    }

    @AfterEach
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
