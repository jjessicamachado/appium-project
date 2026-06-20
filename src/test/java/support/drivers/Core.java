package support.drivers;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumBy.ByAndroidUIAutomator;
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

    private WebElement waitFor(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void click(By locator) {
        waitFor(locator).click();
    }

     public void click(String locator, String value) {
        By xpath = AppiumBy.xpath(locator.replace("arg0", value));
        waitFor(xpath).click();
    }

    public WebElement findElement(By locator) {
        return waitFor(locator);
    }

     public WebElement findElement(String locator, String value) {
        By xpath = AppiumBy.xpath(locator.replace("arg0", value));
        return waitFor(xpath);
    }

    public String getElementText(By locator) {
        return waitFor(locator).getText();
    }

    public void sendKeys(By locator, String value) {
        waitFor(locator).sendKeys(value);
    }

    public void scrollToElement(String locator) {
        String path = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(locator);";
        path = path.replace("locator", locator);
        driver.findElement(AppiumBy.androidUIAutomator(path));
    }
}


