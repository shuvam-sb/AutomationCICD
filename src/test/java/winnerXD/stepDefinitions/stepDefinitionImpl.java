package winnerXD.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import winnerXD.TestComponents.BaseTest;
import winnerXD.pageobjects.CartPage;
import winnerXD.pageobjects.CheckOutPage;
import winnerXD.pageobjects.ConfirmationPage;
import winnerXD.pageobjects.ProductCatalogue;

/*
 * "Generate Cucumber step definitions in Java (with @Given, @When, @Then) from this Gherkin feature:"
(followed by your Gherkin content)
 * */
public class stepDefinitionImpl extends BaseTest {
	public ProductCatalogue productCatalogue;
	public ConfirmationPage cp;
	public String country = "india";

	@Given("I landed on Ecommerce page")
	public void i_landed_on_ecommerce_page() throws IOException {
		landingpage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		productCatalogue = landingpage.LoginApplication(username, password);
	}

	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) {
		productCatalogue.addProductToCart(productName);
	}

	@When("^checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) throws InterruptedException {
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean flag = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(flag);
		CheckOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.selectCountry(country);
		cp = checkOutPage.submitOrder();
	}

	@Then("{string} message is displayed in the ConfirmationPage")
	public void message_displayed_ConfirmationPage(String string) {
		String confirmMessage = cp.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void something_message_is_displayed(String string) {
		
		Assert.assertEquals(string, landingpage.getErrorMessage());
		driver.close();
		
	}
}
