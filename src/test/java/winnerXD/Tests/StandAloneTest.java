package winnerXD.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import winnerXD.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String productName = "ADIDAS ORIGINAL";
		String country = "india";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		
		//LandingPage landingpage = new LandingPage(driver);
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("shuvambasak0@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("I?*Password@123");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'mb-3')]")));
		List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class, 'mb-3')]"));
		WebElement pdt = products.stream()
				.filter(product -> product.findElement(By.xpath(".//b")).getText().equals(productName)).findFirst()
				.orElse(null);
		pdt.findElement(By.xpath(".//div[@class='card-body']//button[last()]")).click();
		//div[contains(@class,'toast-container')]
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'toast-container')]")));
		driver.findElement(By.xpath("//button[contains(@routerlink,'cart')]")).click();
		
		//verify webElement in Cart
		List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cartSection']//h3"));
		Boolean flag =  cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(flag);
		Actions a = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='totalRow']//button")));
		a.moveToElement(driver.findElement(By.xpath("//li[@class='totalRow']//button"))).click().build().perform();
		
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("india");
		List<WebElement > countries = driver.findElements(By.xpath("//button[contains(@class,' list-group-item')]//span"));
		countries.stream().filter(c->c.getText().equalsIgnoreCase(country)).findFirst().ifPresent(WebElement::click);
		a.moveToElement(driver.findElement(By.xpath(" //a[contains(text(), 'Place Order')]"))).click().build().perform();
				
		String confirmMsg  = driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));
		
		
		
		driver.quit();
	}

}
