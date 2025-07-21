package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Loginpage extends Basepage
{

	public Loginpage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(xpath="//input[@id='input-email']")
	 WebElement lnkemail;
	 
	 @FindBy(xpath="//input[@id='input-password']")
	 WebElement lnkpwd;
	 
	 @FindBy(xpath="//input[@value='Login']")
	 WebElement btnlogin;
	 
	 public void setemail(String email)
	 {
		 lnkemail.sendKeys(email);	 
	 }
	 public void password(String password)
	 {
		 lnkpwd.sendKeys(password);; 
	 }
	 public void clicklogin()
	 {
		 btnlogin.click(); 
	 }
	
}
