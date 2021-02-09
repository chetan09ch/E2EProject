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

import junit.framework.Assert;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.BaseClass;

public class ValidateNavigationBar extends BaseClass {
	public WebDriver driver;
	Logger logger=LogManager.getLogger(BaseClass.class.getName());
	
	@BeforeTest
	public void initialize() throws IOException
	{
        driver=initializedriver();
		
		driver.get(prop.getProperty("url"));
		
		driver.manage().window().maximize();
	}
	
	@Test()

	public void baseNavigation() throws IOException, InterruptedException {
		
		
		
		LandingPage l=new LandingPage(driver);
		//if we not passed driver as argument then we will got null pointer exception as its without driver so hence 
		//we used CTR in Page class where Driver variable get assigned to WD driver & CTR will invoke , error solved 
		
		Actions a = new Actions(driver);
		WebElement subscribepopup = driver.findElement(By.xpath("//button[contains(text(),'NO THANKS')]"));
		a.moveToElement(subscribepopup).click().build().perform();
		
		Assert.assertTrue(l.getNavigationBar().isDisplayed());
		logger.info("Successfully validated navigation bar");
		//driver.quit();
	}
	@AfterTest
	public void teardown()
	{
		driver.quit();
	}
	
	


}
