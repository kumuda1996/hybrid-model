package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends Basepage
{
 
 public Homepage(WebDriver driver)
 {
	 super(driver);
 }
 
 @FindBy(xpath="//a[normalize-space()='Register']")
 WebElement lnkregister;
 
 @FindBy(xpath="//a[@title='My Account']")
 WebElement lnkmyaccount;
 
 @FindBy(xpath="//a[normalize-space()='Login']")
 WebElement lnklogin;
 
 public void clickmyaccount()
 {
	 lnkmyaccount.click();	 
 }
 public void clickregister()
 {
	 lnkregister.click(); 
 }
 public void clicklogin()
 {
	 lnklogin.click(); 
 }
}
