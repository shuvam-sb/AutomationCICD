package winnerXD.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;


import winnerXD.TestComponents.BaseTest;
import winnerXD.TestComponents.Retry;
import winnerXD.pageobjects.CartPage;
import winnerXD.pageobjects.ProductCatalogue;

public class ErrorValidation extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	//
	public void LoginErrorValidation() {
		// TODO Auto-generated method stub
		landingpage.LoginApplication("shuvambasak0@gmail.com", "I?Password@123");
		try {
			String getErrmsg = landingpage.getErrorMessage();
			Assert.assertEquals("Incorrect email or password.", getErrmsg);
		} catch (Exception e) {
			e.printStackTrace(); // This will confirm if it throws
			Assert.fail("Exception while fetching error message: " + e.getMessage());
		}
	}

	@Test
	public void productErrorValidation() throws InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingpage.LoginApplication("shuvambasak0@gmail.com", "I?*Password@123");
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean flag = cartPage.verifyProductDisplay(productName + "33");
		Assert.assertFalse(flag);

	}

}
