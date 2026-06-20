package objects.products;
import support.drivers.Core;

public class PageProduct {
    private final Core core;

    public PageProduct(Core core) {
        this.core = core;
    }

    private static final String locator_add_to_cart = "Add To Cart button";
    private static final String locator_cart_index = "//android.view.ViewGroup[@content-desc=\"cart badge\"]/android.widget.TextView[@text=\"arg0\"]";
    private static final String locator_open_cart = "//android.view.ViewGroup[@content-desc=\"cart badge\"]/android.widget.ImageView";
    private static final String locator_cart_title = "//android.widget.TextView[@text=\"My Cart\"]";

    public void clickToAddCart() {
        core.clickByAccId(locator_add_to_cart);
    }

    public void openCart() {
        core.clickByXpath(locator_open_cart);
    }

    public boolean isCartDisplayed() {
        return core.findElement(locator_cart_index, "1").isDisplayed();
    }

     public boolean isTitleDisplayed() {
        return core.findElement(locator_cart_title).isDisplayed();
    }

}
