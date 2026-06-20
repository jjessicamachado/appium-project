package support.drivers;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.datautils.ConfigLoader;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

public class Core {
    private AndroidDriver driver;
    private WebDriverWait wait;
    private final int timeout = Integer.parseInt(ConfigLoader.getProperty("TIMEOUT"));

    public void initializeApp() {
        UiAutomator2Options options = setCapabilities();
        try {
            this.driver = new AndroidDriver(getBaseUrl(), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Falha ao conectar ao servidor Appium: " + e.getMessage());
        }
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }
    
    public void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    // ===== CAPABILITIES =====

    private UiAutomator2Options setCapabilities() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(ConfigLoader.getProperty("PLATFORM_NAME"));
        options.setPlatformVersion(ConfigLoader.getProperty("PLATFORM_VERSION"));
        options.setApp(System.getProperty("user.dir") + ConfigLoader.getProperty("APP"));
        options.setNoReset(Boolean.parseBoolean(ConfigLoader.getProperty("NO_RESET")));
        options.setAutomationName(ConfigLoader.getProperty("AUTOMATION_NAME"));
        options.setAppPackage(ConfigLoader.getProperty("APP_PACKAGE"));
        options.setAppActivity(ConfigLoader.getProperty("APP_ACTIVITY"));
        return options;
    }

    private URL getBaseUrl() throws MalformedURLException {
        return URI.create(ConfigLoader.getProperty("BASE_URL").trim()).toURL();
    }

    // ===== ACTIONS =====

    public void clickByXpath(String element) {
        By path = AppiumBy.xpath(element);
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(path));
        el.click();
    }

    public void clickByXpath(String element, String value) {
        By path = AppiumBy.xpath(element.replace("arg0", value));
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(path));
        el.click();
    }

    public void clickByAccId(String element) {
        By path = AppiumBy.accessibilityId(element);
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(path));
        el.click();
    }

    public WebElement findElement(String element) {
        By path = AppiumBy.xpath(element);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(path));
    }

     public WebElement findElement(String element, String value) {
        By path = AppiumBy.xpath(element.replace("arg0", value));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(path));
    }

    public String getElementText(String element) {
        By path = AppiumBy.xpath(element);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(path)).getText();
    }
}
