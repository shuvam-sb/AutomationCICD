package winnerXD.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import winnerXD.AbstractComponents.AbstractComponent;
import winnerXD.TestComponents.BaseTest;
import winnerXD.pageobjects.CartPage;
import winnerXD.pageobjects.CheckOutPage;
import winnerXD.pageobjects.ConfirmationPage;
import winnerXD.pageobjects.LandingPage;
import winnerXD.pageobjects.OrderPage;
import winnerXD.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	//comments 1
	
	@Test(dataProvider = "getData",groups = {"Purchase"})
	public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String country = "india";
		ProductCatalogue productCatalogue=landingpage.LoginApplication(input.get("email"),input.get("password"));
		//List<WebElement> products = productCatalogue.getProductList();
		
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage =productCatalogue.goToCartPage(); 
		
		Boolean flag = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(flag);
		CheckOutPage checkOutPage =  cartPage.goToCheckout();
		checkOutPage.selectCountry(country);
		ConfirmationPage cp =  checkOutPage.submitOrder();
		
		String confirmMessage =  cp.getConfirmationMessage();	
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
		
	}
	@Test(dependsOnMethods = {"SubmitOrder"},dataProvider = "getData")
	public void OrderHistoryTest(HashMap<String, String> input) {
		ProductCatalogue productCatalogue=landingpage.LoginApplication(input.get("email"),input.get("password"));
		OrderPage op = productCatalogue.gotoOrderPage();
		
		Boolean flag = op.verifyOrderDisplay(input.get("product"));
		Assert.assertTrue(flag);
	}
	
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		//
		/*HashMap<String,String> map = new HashMap<String, String>();
		map.put("email","shuvambasak0@gmail.com");
		map.put("password", "I?*Password@123");
		map.put("product", "ZARA COAT 3");
		HashMap<String,String> map1 = new HashMap<String, String>();
		map1.put("email","henryd110@gmail.com");
		map1.put("password", "Henry@2001");
		map1.put("product", "IPHONE 13 PRO");
		//Object[][] a = {{"shuvambasak0@gmail.com", "I?*Password@123","ZARA COAT 3"},{"henryd110@gmail.com","Henry@2001","IPHONE 13 PRO"}};
		Object[][] obj = {{map},{map1}};
		return obj;		*/
		List<HashMap<String, String>> data = getJsonDatatoMap(System.getProperty("user.dir")+"/src/test/java/winnerXD/data/purchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
