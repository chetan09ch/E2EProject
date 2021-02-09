package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
	
	public WebDriver driver;
	
	By sigin= By.xpath("//span[contains(text(),'Login')]");
	
	By title= By.xpath("//h2[contains(text(),'Featured Courses')]");
	
	By navigationbar= By.xpath("//nav[@class='navbar-collapse collapse']");
	
	//By subscribepopup = By.xpath("//button[contains(text(),'NO THANKS')]");
	
	
	public LandingPage(WebDriver driver) {
		
		this.driver=driver;
		//this.driver is a local variable that we created at top which only used when we assigned WebDriver driver to it 
		// once this done in CTR then this.driver will get knowledge of Test case driver 
	}


	public LoginPage getLogin() {
		
		driver.findElement(sigin).click();
		LoginPage lp = new LoginPage(driver);
		return lp;
	}
	
    public WebElement getTitle() {
		
		return driver.findElement(title);
	}
    
    public WebElement getNavigationBar() {
		
		return driver.findElement(navigationbar);
	}
	
	

}
