package pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Myaccountpage extends Basepage{

	public Myaccountpage(WebDriver driver) {
		super(driver);
	}
@FindBy(xpath="//h2[normalize-space()='My Account']")
WebElement msgheader;
@FindBy(xpath="//a[@title='My Account']")
WebElement myAccount ;
@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']")
WebElement lnklogout;



public boolean ismyaccountpageexist()
{
	try
	{
	return (msgheader.isDisplayed());
	}
	catch(Exception e)
	{
		return false;
	}
}
public void clickLogout()
{
	 myAccount.click();
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	 WebElement logout = wait.until(ExpectedConditions.refreshed(
		        ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Logout']"))));
	lnklogout.click();
}
}
