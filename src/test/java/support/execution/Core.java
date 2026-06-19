package support.execution;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

public class Core {
    private UiAutomator2Options options;
    private AndroidDriver driver;

    public AndroidDriver initiateApp() throws MalformedURLException {
        options = setCapabilites();
        this.driver = new AndroidDriver(getBaseUrl(), options);;
        return this.driver;
    }

    protected UiAutomator2Options setCapabilites() {
        UiAutomator2Options capabilities = new UiAutomator2Options();
        capabilities.setPlatformName(getPlatformName());
        capabilities.setPlatformVersion(getVersion());
        capabilities.setApp(getApp());
        capabilities.setNoReset(getAppReset());
        capabilities.setAutomationName(getAutomationName());
        capabilities.setAppPackage(getAppPackage());
        capabilities.setAppActivity(getAppActivity());
        return capabilities;
    }

    private URL getBaseUrl() throws MalformedURLException {
        String baseURL = ConfigLoader.getProperty("BASE_URL").trim();
        URL appiumServerUrl = URI.create(baseURL).toURL();
        return appiumServerUrl;
    }

    private String getPlatformName() {
        String platformName = ConfigLoader.getProperty("PLATFORM_NAME");
        return platformName;
    }

    private String getVersion() {
        String platformVersion = ConfigLoader.getProperty("PLATFORM_VERSION");
        return platformVersion;
    }

    private String getAutomationName() {
        String automationName = ConfigLoader.getProperty("AUTOMATION_NAME");
        return automationName;
    }

    private String getAppPackage() {
        String appPackage = ConfigLoader.getProperty("APP_PACKAGE");
        return appPackage;
    }

    private String getAppActivity() {
        String appActivity = ConfigLoader.getProperty("APP_ACTIVITY");
        return appActivity;
    }

    private String getApp() {
        String appPath = ConfigLoader.getProperty("APP");
        return System.getProperty("user.dir") + appPath;
    }

    private boolean getAppReset() {
        String appReset = ConfigLoader.getProperty("APP_RESET");
        boolean reset = Boolean.parseBoolean(appReset);
        return reset;
    }

    //actions


}
