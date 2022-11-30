package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utility.data_read;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;

public class TC001 {
	
	public static WebDriver driver;

	@BeforeTest
	public static void TestStart() throws Exception {
		System.out.println("***************************************************************************************************");
		System.out.println("------------------------------------Testing Started------------------------------------------------");
	}
	
	@AfterTest
	public static void TestEnd() throws Exception {
		System.out.println("------------------------------------Testing Ended--------------------------------------------------");
	}
	
	@BeforeClass
	public static void BrowserURLSetup() throws Exception {
		
		System.out.println("Executing TC_001");
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\0015NA744\\Documents\\Test Specialist\\FST Training\\eclipse-workspace\\Automation_Selenium\\drivers\\chromedriver.exe"); 
		driver = new ChromeDriver();
		
		data_read data = new data_read();
		Thread.sleep(2000);
		
		String BROWSER = data.getBrowser();
		System.out.println("BROWSER : " + BROWSER);
		
		String URL = data.getServiceNowApp();
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
		
//Service Now - Title Validation
		String ActualTitle = driver.getTitle();		
		System.out.println("Actual Title : " + ActualTitle);
		
		String ExpectedTitle = "Sign in";		
		System.out.println("Expected Title : " + ExpectedTitle);
		
		Assert.assertEquals(ExpectedTitle, ActualTitle);
		System.out.println("Title Validation is PASSED");
		System.out.println("");
		
//The Login ID and Password text field label Validation
		String email = driver.findElement(By.id("email")).getText();
		String password = driver.findElement(By.name("password")).getText();
		System.out.println("The Login ID and Password text field has the Label as " + email + "and " + password);
		System.out.println("");

//Forgot your password? Link validation
		Boolean forgot = driver.findElement(By.linkText("Forgot your password?")).isDisplayed();
		if(forgot == true) {
		System.out.println("'Forgot your password?' Link is displayed");}
		else {
		System.out.println("'Forgot your password?' Link is not displayed");}
		System.out.println("");
//Login Negative 
	//No Inputs
		System.out.println("No Inputs are provided :");
		
		driver.findElement(By.linkText("Sign In")).click();
		Thread.sleep(3000);
		
		if(driver.getPageSource().contains("Required")) 
		{System.out.println("Invalid message is displayed as 'Required'");}
		else {System.out.println("Invalid message is not displayed as 'Required'");}
		System.out.println("");
		
	//Only Incorrect Mail ID
		System.out.println("Only Incorrect Mail ID is provided :");
		
    	WebElement UserName = driver.findElement(By.xpath("(//*[@name = 'username'])[2]"));
    	UserName.sendKeys("nitheeshkumarv7");
		System.out.println("Entered Username");
		
		driver.findElement(By.linkText("Sign In")).click();
		Thread.sleep(3000);
		
		if(driver.getPageSource().contains("Email is not valid")) 
		{System.out.println("Invalid message is displayed as 'Email is not valid'");}
		else {System.out.println("Invalid message is not displayed as 'Email is not valid'");}
		System.out.println("");
		
	//Right Mail ID and Incorrect password
		System.out.println("Right Mail ID and Incorrect password are provided :");
		UserName.clear();
    	UserName.sendKeys("nitheeshkumarv7@gmail.com");
		System.out.println("Entered Username");
 	    
		WebElement Password = driver.findElement(By.xpath("(//*[@name = 'password'])[2]"));
		Password.sendKeys("Nn023$101");
		System.out.println("Entered Password");
		
		driver.findElement(By.linkText("Sign In")).click();
		Thread.sleep(10000);
		
		if(driver.getPageSource().contains("Invalid Credentials"))
		{System.out.println("Invalid message is displayed as 'Invalid Credentials'");}
		else {System.out.println("Invalid message is not displayed as 'Invalid Credentials'");}		
		System.out.println("");
		
//Login Positive 
		System.out.println("Right Mail ID and Password are provided :");
    	UserName.clear();
    	UserName.sendKeys("nitheeshkumarv7@gmail.com");
		System.out.println("Entered Username");
 
		Password.clear();
		Password.sendKeys("Nn023$100");
		System.out.println("Entered Password");
  
		driver.findElement(By.linkText("Sign In")).click();
		System.out.println("Clicked SignIn button");
		System.out.println("");
		Thread.sleep(10000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		System.out.println("Validating the Title");
		String PActualTitle = driver.getTitle();		
		System.out.println(PActualTitle);
		
		String PExpectedTitle = "ServiceNow – The world works with ServiceNow™";		
		System.out.println(PExpectedTitle);
		
		Assert.assertEquals(PExpectedTitle, PActualTitle);
		System.out.println("");
		
		System.out.println("Logged In Successfully");
		System.out.println("");
	}
}
