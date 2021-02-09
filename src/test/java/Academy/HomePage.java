package Academy;

import java.io.IOException;
import java.util.Base64;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.ForgotPassword;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.BaseClass;

public class HomePage extends BaseClass {
	public WebDriver driver;
	Logger logger=LogManager.getLogger(BaseClass.class.getName());
	@BeforeTest
	public void initialize() throws IOException
	{
        driver=initializedriver();
		
		//driver.get(prop.getProperty("url"));// Before test , URL execute only once but we have 2 testdata in dataprovider hence this url needs to move 
		//move from @Beforetest to @Test only for this multiple testdata scenario
		
		driver.manage().window().maximize();
	}
	
	@Test(dataProvider="getData")

	public void baseNavigation(String username,String password, String text) throws IOException, InterruptedException {
		
		driver.get(prop.getProperty("url"));
		
		LandingPage l=new LandingPage(driver);
		//if we not passed driver as argument then we will got null pointer exception as its without driver so hence 
		//we used CTR in Page class where Driver variable get assigned to WD driver & CTR will invoke , error solved 
		
		/*Actions a = new Actions(driver);
		WebElement subscribepopup = driver.findElement(By.xpath("//button[contains(text(),'NO THANKS')]"));
		a.moveToElement(subscribepopup).click().build().perform(); */
		
		//* creating here new operator optimization in getLogin method of landing page 
		//l.getLogin();
		// we are retruning getLogin here from LoginPage hence we need to catch it 
		LoginPage lp=l.getLogin();
		Thread.sleep(4000);
		//LoginPage lp = new LoginPage(driver);
		
		lp.getUsername().sendKeys(username);
		lp.getPassword().sendKeys(password);
		System.out.println(text);
		lp.getLogin().click();
		//logger.info("Successfully validated login process");
		//driver.quit();	
		//* In above code we have 2 New object , it should only one in every test , how to do it ?
		//Suppose you have 10 pages in your test then 10 new operator is not good in test
		//Solution create a 2nd page obj in 1st page method rediration method 
		
		ForgotPassword fp=lp.forgotPassword();
		
		fp.getEmail().sendKeys("abcd");
		fp.sendMeInstructions().click();
	}
	
	@DataProvider
	public Object[][] getData() {
		
		//Row stands for how many different data types test should run
		//Column stands for how many values per each test
		
		Object [][] data = new Object [2][3];
		//Oth Row
		data[0][0]="nonrestricteduser@qw.com";
		data[0][1]="123456";
		data[0][2]="nonrestricteduser";
		
		//1st Row
		data[1][0]="restricteduser@qw.com";
		data[1][1]="123456";
		data[1][2]="restricteduser";
		
		
		return data;
	}
	
	@AfterTest
	public void teardown()
	{
		driver.quit();
	}
	

}
