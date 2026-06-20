package objects.products;
import support.drivers.Core;

public class PageCart {
    private final Core core;

    public PageCart(Core core) {
        this.core = core;
    }

    private static final String locator_product_counter = "//android.view.ViewGroup[@content-desc=\"counter amount\"]/android.widget.TextView";
    private static final String locator_product_counter_sum = "//android.view.ViewGroup[@content-desc=\"counter plus button\"]/android.widget.ImageView";
    private static final String locator_btn_checkout = "Proceed To Checkout button";

    public void changeProductQuantity(int qntd) {
        int count = getProductQuantity();
        for (int i = count; i < qntd; i++) {
            core.clickByXpath(locator_product_counter_sum);
        }
    }

    public int getProductQuantity() {
        return Integer.parseInt(core.getElementText(locator_product_counter));
    }

    public void proceedToCheckout() {
        core.clickByAccId(locator_btn_checkout);
    }
}
