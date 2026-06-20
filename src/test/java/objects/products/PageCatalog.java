package objects.products;

import support.drivers.Core;

public class PageCatalog {
    private final Core core;

    public PageCatalog(Core core) {
        this.core = core;
    }

    private static final String locator_product = "//android.widget.TextView[@content-desc=\"store item text\" and @text=\"arg0\"]";

    public void selectProduct(String product) {
        core.clickByXpath(locator_product, product);
    }
}
