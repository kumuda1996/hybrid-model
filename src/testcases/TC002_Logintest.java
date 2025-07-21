package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.Homepage;
import pageobjects.Loginpage;
import pageobjects.Myaccountpage;
import testbase.Baseclass;

public class TC002_Logintest extends Baseclass 
{
@Test(groups={"sanity","Master"})
 public void verify_login()
 {
	 logger.info("starting TC002_login...");
	 try {
	 Homepage hp=new Homepage(driver);
	 hp.clickmyaccount();
	 hp.clicklogin();
	 
	 Loginpage lp=new Loginpage(driver);
	 lp.setemail(p.getProperty("email"));
	 lp.password(p.getProperty("password"));
	 lp.clicklogin();
	 
	 Myaccountpage macc=new Myaccountpage(driver);
	 boolean targetpage =macc.ismyaccountpageexist();
	 Assert.assertTrue(targetpage);
	 }
	 
	 catch(Exception e)
	 {
	// Assert.assertEquals(targetpage, true,"Login failed");
	 Assert.fail();
	 }
	 
	 logger.info("finished TC002_login...");
 }
}
