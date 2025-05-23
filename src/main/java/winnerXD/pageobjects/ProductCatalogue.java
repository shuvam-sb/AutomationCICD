package winnerXD.pageobjects;

import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import winnerXD.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(xpath="//div[contains(@class, 'mb-3')]")
	List <WebElement> products;
	By productsBy = By.xpath("//div[contains(@class, 'mb-3')]");
	By addToCart = By.xpath(".//div[@class='card-body']//button[last()]");
	By toastMessage = By.xpath("//div[@id='toast-container']");
	By spinner = By.cssSelector(".ng-animating");
	By spinner_S = By.cssSelector(".ngx-spinner-overlay");
			

	public List<WebElement> getProductList() {
		return products;
	}
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.xpath(".//b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		//waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
		waitForElementToDisappear(spinner_S);
	}

	


}
