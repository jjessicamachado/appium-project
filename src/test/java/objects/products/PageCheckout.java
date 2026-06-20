package objects.products;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.datafaker.providers.base.CreditCardType;

import org.junit.Assert;
import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import net.datafaker.Faker;
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
    private static final By locator_card_details = AppiumBy.accessibilityId("Card Number* input field");
    private static final By locator_expiration_date = AppiumBy.accessibilityId("Expiration Date* input field");
    private static final By locator_security_code = AppiumBy.accessibilityId("Security Code* input field");
    private static final By locator_btn_review_order = AppiumBy.accessibilityId("Review Order button");
    private static final By locator_number_items = AppiumBy.xpath("//android.widget.TextView[@content-desc=\"total number\"]");
    private static final By locator_total_items = AppiumBy.accessibilityId("total price");
    private static final By locator_product_price = AppiumBy.accessibilityId("product price");
    private static final By locator_card_page = AppiumBy.xpath("//android.widget.TextView[@text=\"Enter a payment method\"]");
    private static final String locator_web_standard_delivery_fee = "new UiSelector().text(\"$5.99\")";
    private static final By locator_standard_delivery_fee = AppiumBy.xpath("//android.widget.TextView[@text=\"$5.99\"]");
          
    Faker faker = new Faker();
    String fullname = faker.name().fullName();
    String address = faker.address().streetAddress();
    String city = faker.address().city();
    String state = faker.address().state();
    String zip = faker.address().zipCode();
    String country = faker.address().country();
    String cardNumber = faker.finance().creditCard(CreditCardType.VISA);
    Date now = new Date();
    Date fiveYearsFromNow = new Date(now.getTime() + 5L * 365 * 24 * 60 * 60 * 1000);
    Date randomFutureDate = faker.date().between(now, fiveYearsFromNow);
    String expirationDate = new SimpleDateFormat("MM/yy").format(randomFutureDate);
    String securityCode = faker.numerify("###");

    public void loginCheckout(String username, String password) {
        core.sendKeys(locator_username, username);
        core.sendKeys(locator_password, password);
        core.click(locator_btn_login);
    }

    public void fillInAddress() {
        core.sendKeys(locator_fullname, fullname);
        core.sendKeys(locator_address, address);
        core.sendKeys(locator_city, city);
        core.sendKeys(locator_state, state);
        core.sendKeys(locator_zip, zip);
        core.sendKeys(locator_country, country);
        core.click(locator_btn_to_payment);
    }

    public void enterCardDetails() {
        Assert.assertTrue(core.findElement(locator_card_page).isDisplayed());
        core.sendKeys(locator_fullname, fullname);
        core.sendKeys(locator_card_details, cardNumber);
        core.sendKeys(locator_expiration_date, expirationDate);
        core.sendKeys(locator_security_code, securityCode);
        core.click(locator_btn_review_order);
    }

    public int getNumberItems() {
       return Integer.parseInt(core.getElementText(locator_number_items).replace("items", "").trim());
    }

    public String getTotal(int items) {
        double productPrice = Double.parseDouble(core.getElementText(locator_product_price).replace("$", "").trim());
        core.scrollToElement(locator_web_standard_delivery_fee);

        double dhlPrice = Double.parseDouble(core.getElementText(locator_standard_delivery_fee).replace("$", "").trim());
        
        double valueTotal = (productPrice * items) + dhlPrice;
        return "$" + String.format("%.2f", valueTotal);
    }

     public String getTotalText() {
        return core.getElementText(locator_total_items);
        
    }



}