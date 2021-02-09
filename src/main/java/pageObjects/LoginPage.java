package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	public WebDriver driver;
	
	By emaild= By.xpath("//input[@id='user_email']");
	By password= By.xpath("//input[@id='user_password']");
	By login=By.cssSelector("[value='Log In']");
	By forgetpassword=By.xpath("//a[contains(text(),'Forgot Password?')]");
	//By subscribepopup = By.xpath("//button[contains(text(),'NO THANKS')]");
	
	
	public LoginPage(WebDriver driver) {
		
		this.driver=driver;
		//this.driver is a local variable that we created at top which only used when we assigned WebDriver driver to it 
		// once this done in CTR then this.driver will get knowledge of Test case driver 
	}


	public ForgotPassword forgotPassword() {
		
		driver.findElement(forgetpassword).click();
		//ForgotPassword fp = new ForgotPassword(driver);
		//return fp;
		//less code 
		return new ForgotPassword(driver);
	}
	
	public WebElement getUsername() {
		
		return driver.findElement(emaild);
	}
	
    public WebElement getPassword() {
		
		return driver.findElement(password);
	}
	
    public WebElement getLogin() {
		
		return driver.findElement(login);
	}
	

}
