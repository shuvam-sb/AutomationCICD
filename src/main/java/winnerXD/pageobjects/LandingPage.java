package winnerXD.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import winnerXD.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//input[@id='userEmail']")
	WebElement userEmail;
	@FindBy(xpath = "//input[@id='userPassword']")
	WebElement userPassword;
	@FindBy(xpath = "//input[@id='login']")
	WebElement submit;
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;
	
	public String getErrorMessage() {
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	public ProductCatalogue LoginApplication(String email,String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;

	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	


}
