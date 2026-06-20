package objects.products;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import support.drivers.Core;

public class PageCheckout {
    private final Core core;

    public PageCheckout(Core core) {
        this.core = core;
    }

    private static final By locator_username = AppiumBy.accessibilityId("Username input field");
    private static final By locator_password = AppiumBy.accessibilityId("Password input field");
    private static final By locator_btn_login = AppiumBy.accessibilityId("Login button");
    private static final By locator_fullname = AppiumBy.accessibilityId("Full Name* input field");
    private static final By locator_address = AppiumBy.accessibilityId("Address Line 1* input field");
    private static final By locator_city = AppiumBy.accessibilityId("City* input field");
    private static final By locator_state = AppiumBy.accessibilityId("State/Region input field");
    private static final By locator_zip = AppiumBy.accessibilityId("Zip Code* input field");
    private static final By locator_country = AppiumBy.accessibilityId("Country* input field");
    private static final By locator_btn_to_payment = AppiumBy.accessibilityId("To Payment button");
          

    public void loginCheckout(String username, String password) {
        core.sendKeys(locator_username, username);
        core.sendKeys(locator_password, password);
        core.click(locator_btn_login);
    }

    public void fillInAddress() {
        String fullname;
        String address;
        String city;
        String state;
        String zip;
        String country;
        
        core.sendKeys(locator_fullname, fullname);
        core.sendKeys(locator_address, address);
        core.sendKeys(locator_city, city);
        core.sendKeys(locator_state, state);
        core.sendKeys(locator_zip, zip);
        core.sendKeys(locator_country, country);
        core.click(locator_btn_to_payment);
    }



}