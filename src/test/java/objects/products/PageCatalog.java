package objects.products;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageCatalog {
    AndroidDriver driver;
    WebDriverWait wait;

    public PageCatalog(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //locators
    String locator_product = "//android.widget.TextView[@content-desc=\"store item text\" and @text=\"arg0\"]";
    By locator_add_to_cart = AppiumBy.accessibilityId("Add To Cart button");
    String locator_cart_index = "//android.view.ViewGroup[@content-desc=\"cart badge\"]/android.widget.TextView[@text=\"arg0\"]";
    By locator_open_cart = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"cart badge\"]/android.widget.ImageView");
    By locator_cart_title = AppiumBy.xpath("//android.widget.TextView[@text=\"My Cart\"]");
    By locator_product_counter = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"counter amount\"]/android.widget.TextView");
    By locator_product_counter_sum = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"counter plus button\"]/android.widget.ImageView");

    //actions
    public void selectProduct(String product) {
        By productPath = AppiumBy.xpath(locator_product.replace("arg0", product));
        WebElement index_cart = wait.until(ExpectedConditions.visibilityOfElementLocated(productPath));
        driver.findElement(productPath).click();
    }

    public void clickToAddCart() {
        driver.findElement(locator_add_to_cart).click();
        WebElement index_cart = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath(locator_cart_index.replace("arg0", "1"))
        ));
        Assert.assertTrue(index_cart.isDisplayed());
    }

    public void openCart() {
        driver.findElement(locator_open_cart).click();
        WebElement cartTitle = driver.findElement(locator_cart_title);
        Assert.assertTrue(cartTitle.isDisplayed());
    }

    public void changeProductQuantity(int qntd) {
        WebElement counter = wait.until(ExpectedConditions.visibilityOfElementLocated(locator_product_counter));
        int count = Integer.parseInt(counter.getText());
        for (int i = count; i < qntd; i++) {
            System.out.println("adding +1 product, the total is: " + (i+1));
            driver.findElement(locator_product_counter_sum).click();
        }
        count = Integer.parseInt(driver.findElement(locator_product_counter).getText());
        Assert.assertEquals(qntd, count);
    }

}
