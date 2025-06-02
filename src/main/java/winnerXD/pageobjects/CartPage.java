package winnerXD.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import winnerXD.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = ".totalRow button")
	private WebElement checkOutBtn;
	
	@FindBy(xpath = "//div[@class='cartSection']//h3")
	private List<WebElement> productsTitles;
	
	public Boolean verifyProductDisplay(String productName) {
		
		Boolean flag = productsTitles.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return flag; 
	}
	public CheckOutPage goToCheckout() throws InterruptedException {
		Actions a = new Actions(driver);
		a.moveToElement(checkOutBtn).build().perform();
		Thread.sleep(1000);
		checkOutBtn.click();
		return new CheckOutPage(driver);
	}

}
