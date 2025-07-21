package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Accountregistrationpage extends Basepage
{
 public Accountregistrationpage(WebDriver driver)
 {
	 super(driver);
 }
 
 @FindBy(xpath="//input[@id='input-firstname']")
 WebElement lnkfirstname;
 @FindBy(xpath="//input[@id='input-lastname']")
 WebElement lnklastname;
 @FindBy(xpath="//input[@id='input-telephone']")
 WebElement lnkphone;
 @FindBy(xpath="//input[@id='input-email']")
 WebElement lnkemail;
 @FindBy(xpath="//input[@id='input-password']")
 WebElement lnkpassword;
 @FindBy(xpath="//input[@id='input-confirm']")
 WebElement lnkpassword2;
 @FindBy(xpath="//input[@type='checkbox']")
 WebElement btnprivacy;
 @FindBy(xpath="//input[@value='Continue']")
 WebElement btnregister;
 @FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
 WebElement confmsg;

 
 public void setfirstname(String fname)
 {
	 lnkfirstname.sendKeys(fname);
 }
 public void setlastname(String lname)
 {
	 lnklastname.sendKeys(lname);
 }
 public void setphone(String phone)
 {
	 lnkphone.sendKeys(phone);
 }
 public void setemail(String email)
 {
	 lnkemail.sendKeys(email);
 }
 public void setpassword(String password)
 {
	 lnkpassword.sendKeys(password);
 }
 public void confimpassword(String password)
 {
	 lnkpassword2.sendKeys(password);
 }
 public void privacypolicy()
 {
	 btnprivacy.click();
 }
 public void register()
 {
	 btnregister.click();
 }

 public String getconfirmationmsg()
 {
	 
	return	confmsg.getText();
 }
}
