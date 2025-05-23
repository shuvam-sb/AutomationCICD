package winnerXD.AbstractComponents;

import java.awt.print.Pageable;
import java.time.Duration;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import winnerXD.pageobjects.CartPage;
import winnerXD.pageobjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath = "//button[contains(@routerlink,'cart')]")
	WebElement cartBtn;
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement orderBtn;
	

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	
	public void waitForElementToDisappear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	public void waitForElementToBeClickable(WebElement findBy) throws InterruptedException {
		//Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(findBy));
	}
	public void safeClick(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.ignoring(ElementClickInterceptedException.class)
		    .until(driver -> {
		        try {
		            element.click();
		            return true;
		        } catch (Exception e) {
		            return false;
		        }
		    });
	}
	public CartPage goToCartPage() throws InterruptedException {
		waitForElementToBeClickable(cartBtn);
		cartBtn.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	public OrderPage gotoOrderPage() {
		orderBtn.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	
}
