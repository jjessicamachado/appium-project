package tests;

import io.appium.java_client.android.AndroidDriver;
import objects.products.PageCatalog;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import support.execution.BaseTest;
import support.execution.Core;

import java.net.MalformedURLException;

public class Catalog extends BaseTest {
    private AndroidDriver driver;
    private Core core;
    PageCatalog pageCatalog;

    @Before
    public void setUp() throws MalformedURLException {
        core = new Core();
        driver = core.initiateApp();
        pageCatalog = new PageCatalog(driver);
    }

    @After
    public void tearDown() {
        // Garante que o app feche e a sessão no servidor do Appium seja encerrada com sucesso
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void addProductToCart() {
        pageCatalog.selectProduct("Sauce Labs Backpack");
        pageCatalog.clickToAddCart();
        pageCatalog.openCart();
        pageCatalog.changeProductQuantity(3);
    }

}
