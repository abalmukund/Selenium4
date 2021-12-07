package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.Architecture;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClass {
    public static WebDriver of(Capabilities capabilities) {
        switch (capabilities.getBrowserName()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().architecture(Architecture.X64).setup();
                break;
            default:
                break;
        }
        return RemoteWebDriver.builder().oneOf(capabilities).build();
    }
}
