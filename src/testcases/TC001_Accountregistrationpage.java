package testcases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageobjects.Accountregistrationpage;
import pageobjects.Homepage;
import testbase.Baseclass;

public class TC001_Accountregistrationpage extends Baseclass
{
@Test(groups={"Regression","Master"})
 public void verify_account_registration()
 {
	logger.info("STARTING Tc1_Accountregistrationpage");
	try
	{
	Homepage hp=new Homepage(driver);
	hp.clickmyaccount();
	logger.info("clicked on myaccount link");
	hp.clickregister();
	logger.info("clicked on register link");
	
	Accountregistrationpage regpage=new Accountregistrationpage(driver);
	logger.info("providing customer details...");
	regpage.setfirstname(randomeString().toUpperCase());
	regpage.setlastname(randomeString().toUpperCase());
	regpage.setemail(randomeString()+"@gmail.com");
	regpage.setphone(randomeNumber());
	
	String password=randomAlphaNumeric();
	
	regpage.setpassword(password);
	regpage.confimpassword(password);
	
	regpage.privacypolicy();
	regpage.register();
	
	logger.info("validating expected msg...");
	String confmsg=regpage.getconfirmationmsg();
	Assert.assertEquals(confmsg, "Your Account Has Been Created!","confirmation message mismatched");
	}
	catch(Exception e)
	{
		logger.error("test failed...");
		logger.debug("Debug logs...");
		Assert.fail();
	}
	logger.info("FINISHED Tc1_Accountregistrationpage");
 }


}
