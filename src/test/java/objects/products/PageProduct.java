package objects.products;
import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import support.drivers.Core;

public class PageProduct {
    private final Core core;

    public PageProduct(Core core) {
        this.core = core;
    }

    private static final By locator_add_to_cart = AppiumBy.accessibilityId("Add To Cart button");
    private static final String locator_cart_index = "//android.view.ViewGroup[@content-desc=\"cart badge\"]/android.widget.TextView[@text=\"arg0\"]";
    private static final By locator_open_cart = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"cart badge\"]/android.widget.ImageView");
    private static final By locator_cart_title = AppiumBy.xpath("//android.widget.TextView[@text=\"My Cart\"]");

    public void clickToAddCart() {
        core.click(locator_add_to_cart);
    }

    public void openCart() {
        core.click(locator_open_cart);
    }

    public boolean isCartDisplayed() {
        return core.findElement(locator_cart_index, "1").isDisplayed();
    }

     public boolean isTitleDisplayed() {
        return core.findElement(locator_cart_title).isDisplayed();
    }

}
