package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BaseClass {
	
	public WebDriver driver;
	public Properties prop ;
	public static Logger logger;
	
	public WebDriver initializedriver() throws IOException {
	
	prop = new Properties();
	
	FileInputStream fis = new FileInputStream("D:\\Automation\\E2Eproject\\src\\main\\java\\Resources\\config.properties");
	
	prop.load(fis);
	
	String browserName = prop.getProperty("browser");

	if (browserName.equals("chrome")) {
		System.setProperty("webdriver.chrome.driver", prop.getProperty("chromepath"));
		driver=new ChromeDriver();
	}
	else if (browserName.equals("ie")) {
		System.setProperty("webdriver.ie.driver", prop.getProperty("iepath"));
		driver=new InternetExplorerDriver();
	}
	else if (browserName.equals("firefox")) {
		System.setProperty("webdriver.ie.driver", prop.getProperty("firefoxpath"));
		driver=new FirefoxDriver();
	}
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	return driver;
	
}
	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException
	{
	TakesScreenshot ts = (TakesScreenshot) driver;
	File source=ts.getScreenshotAs(OutputType.FILE);
	String destinationfile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
	FileUtils.copyFile(source,new File(destinationfile));
    return destinationfile;
	}

}