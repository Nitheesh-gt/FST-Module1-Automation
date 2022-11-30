package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.data_read;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;

public class TC005 {
	
	public static WebDriver driver;
	
	@BeforeClass
	public static void BrowserURLSetup() throws Exception {
		
		Thread.sleep(2000);
		System.out.println("Executing TC_005");
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\0015NA744\\Documents\\Test Specialist\\FST Training\\eclipse-workspace\\Automation_Selenium\\drivers\\chromedriver.exe"); 
		driver = new ChromeDriver();
		
		data_read data = new data_read();
		
		String BROWSER = data.getBrowser();
		System.out.println("BROWSER : " + BROWSER);
		
		String URL = data.getSauceDemoApp();
		System.out.println("URL : " + URL);
		System.out.println("");
		
		driver.manage().window().maximize(); 
		System.out.println("Browser Maximized");
		
		driver.get(URL);
		System.out.println("Landed on the Application Successfully!!!");
		System.out.println("");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterClass
	public static void Quit() throws Exception {	
		driver.quit();
	}

	@Test
	public static void Login() throws Exception{
		
//Swag Labs - Title and Login button Validation
		String ActualTitle = driver.getTitle();		
		System.out.println("Actual Title : " + ActualTitle);
		
		String ExpectedTitle = "Swag Labs";		
		System.out.println("Expected Title : " + ExpectedTitle);
		
		Assert.assertEquals(ExpectedTitle, ActualTitle);
		System.out.println("Title Validation is PASSED");
		System.out.println("");
		
		String A = driver.findElement(By.id("login-button")).getCssValue("text-transform");
		String B = "uppercase";
		
		Assert.assertEquals(B, A);
		System.out.println("Login Button text is displayed as 'LOGIN'");
		System.out.println("");

//Logging In
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).submit();

//Validating default filter
		String dp = driver.findElement(By.className("active_option")).getText();
		String filter = "NAME (A TO Z)";
		Assert.assertEquals(filter, dp);
		System.out.println("The default option of filter is 'NAME (A TO Z)'");
		System.out.println("");
		
//Adding products to cart and verifying them
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		System.out.println("Adding Saucelabs backpack to Cart");
		
		WebElement cart = driver.findElement(By.className("shopping_cart_badge"));
		
		String n1badge = cart.getText();
		String one = "1";
		Assert.assertEquals(one, n1badge);
		System.out.println("The cart badge has 1 product");
		
		driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
		System.out.println("Then Adding Test.allTheThings T-Shirt(Red) to Cart");

		String n2badge = cart.getText();
		
		if(n2badge == "1") {
			System.out.println("The cart badge product is not increased");
		}else {
			System.out.println("The cart badge product is increased to " + n2badge);
			System.out.println("");
		}

//Removing product from cart and verifying them
		driver.findElement(By.id("remove-sauce-labs-backpack")).click();
		System.out.println("Removed Saucelabs backpack to Cart");
		
		String n3badge = cart.getText();
		Assert.assertEquals(one, n3badge);
		System.out.println("The cart badge has 1 product");
		System.out.println("");
		
		cart.click();
		
		String item = driver.findElement(By.className("inventory_item_name")).getText();
		System.out.println(item);
		String item1 = "Test.allTheThings() T-Shirt (Red)";
		Assert.assertEquals(item1, item);
		System.out.println("The inventory iteam has been verified with the added product");

		driver.findElement(By.id("continue-shopping")).click();
		System.out.println("Shopping continues");
		System.out.println("");

//Filtering by Price List from Low to High and verifying them
		System.out.println("Non - Filtered Product Price List");
		List<WebElement> itemprice1 = driver.findElements(By.className("inventory_item_price"));
		List<Double> price1 = new ArrayList<>();
		for(WebElement p : itemprice1) {
			price1.add(Double.valueOf(p.getText().replace("$","")));
		}
		for(int i = 0; i<itemprice1.size(); i++) {
			System.out.println("Product " + i + " : " + itemprice1.get(i).getText());
		}System.out.println("");

		
		Select filterdp = new Select(driver.findElement(By.className("product_sort_container")));
		filterdp.selectByValue("lohi");
		System.out.println("Filtering the Price from low to high");
		
		System.out.println("Filtered Product Price List");	
		List<WebElement> itemprice2 = driver.findElements(By.className("inventory_item_price"));
		List<Double> price2 = new ArrayList<>();
		for(WebElement p : itemprice2) {
			price2.add(Double.valueOf(p.getText().replace("$","")));
		}		
		for(int i = 0; i<itemprice2.size(); i++) {
			System.out.println("Product " + i + " : " + itemprice2.get(i).getText());
		}System.out.println("");

//Verifying the filtered price
		System.out.println("Verfying the filtered price list");
		System.out.println("Price list before filtered : " + price1);
		Collections.sort(price1);
		System.out.println("Price list before filtered(after sorting) : " + price1);
		System.out.println("Price list before filtered : " + price2);
		System.out.println("Now comparing them");
		Assert.assertEquals(price2, price1);
		System.out.println("The Filtered price has been SORTED correctly ");
		System.out.println("");

	}
	
}