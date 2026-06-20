package objects.products;
import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import support.drivers.Core;

public class PageCart {
    private final Core core;

    public PageCart(Core core) {
        this.core = core;
    }

    private static final By locator_product_counter = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"counter amount\"]/android.widget.TextView");
    private static final By locator_product_counter_sum = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"counter plus button\"]/android.widget.ImageView");
    private static final By locator_btn_checkout = AppiumBy.accessibilityId("Proceed To Checkout button");
   
    public void changeProductQuantity(int qntd) {
        int count = getProductQuantity();
        for (int i = count; i < qntd; i++) {
            core.click(locator_product_counter_sum);
        }
    }

    public int getProductQuantity() {
        return Integer.parseInt(core.getElementText(locator_product_counter));
    }

    public void proceedToCheckout() {
        core.click(locator_btn_checkout);
    }

}
