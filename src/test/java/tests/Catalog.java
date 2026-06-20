package tests;

import objects.products.PageCart;
import objects.products.PageCatalog;
import objects.products.PageProduct;

import org.junit.Assert;
import org.junit.Test;
import support.execution.BaseTest;

public class Catalog extends BaseTest {
    PageCatalog pageCatalog = new PageCatalog(core);
    PageCart pageCart = new PageCart(core);
    PageProduct pageProduct = new PageProduct(core);

    @Test
    public void addProductToCart() {
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
    }
}
