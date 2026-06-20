package tests;

import objects.products.PageCart;
import objects.products.PageCatalog;
import objects.products.PageCheckout;
import objects.products.PageProduct;

import org.junit.Assert;
import org.junit.Test;
import support.execution.BaseTest;

public class CatalogTest extends BaseTest {
    PageCatalog pageCatalog = new PageCatalog(core);
    PageCart pageCart = new PageCart(core);
    PageProduct pageProduct = new PageProduct(core);
    PageCheckout pageCheckout = new PageCheckout(core);

    @Test
    public void userBuyMoreThanOneProduct() {
        int quantity = 3;

        pageCatalog.selectProduct("Sauce Labs Backpack");
        pageProduct.clickToAddCart();
        Assert.assertTrue(pageProduct.isCartDisplayed());
        pageProduct.openCart();
        Assert.assertTrue(pageProduct.isTitleDisplayed());
        pageCart.changeProductQuantity(quantity);
        int count = pageCart.getProductQuantity();
        Assert.assertEquals(quantity, count);
        pageCart.proceedToCheckout();
        pageCheckout.loginCheckout("bob@example.com", "10203040");
        pageCheckout.fillInAddress();
        pageCheckout.enterCardDetails();
        int numberItems = pageCheckout.getNumberItems();
        Assert.assertEquals(quantity, numberItems);
        String totalItems = pageCheckout.getTotal(quantity);
        String totalText = pageCheckout.getTotalText();
        Assert.assertEquals(totalItems, totalText);

    }


}
