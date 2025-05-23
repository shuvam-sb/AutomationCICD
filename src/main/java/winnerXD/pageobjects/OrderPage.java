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

public class OrderPage extends AbstractComponent{
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//tr//td[2]")
	List<WebElement> productNames;
	
	public Boolean verifyOrderDisplay(String productName) {
		Boolean flag = productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return flag; 
	}
	
}
