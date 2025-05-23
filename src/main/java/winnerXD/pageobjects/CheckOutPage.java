package winnerXD.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import winnerXD.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement sendCtxt;
	
	@FindBy(xpath = "//button[contains(@class,' list-group-item')]//span")
	List<WebElement> countries;
	
	@FindBy(xpath = "//a[contains(text(), 'Place Order')]")
	WebElement placeOrder;
	
	
	public void selectCountry(String countryName) {
		sendCtxt.sendKeys(countryName);
		countries.stream().filter(c->c.getText().equalsIgnoreCase(countryName)).findFirst().ifPresent(WebElement::click);

	}
	public ConfirmationPage submitOrder() {
		Actions a = new Actions(driver);
		a.moveToElement(placeOrder).click().build().perform();
		return new ConfirmationPage(driver);
	}
}
