package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.Homepage;
import pageobjects.Loginpage;
import pageobjects.Myaccountpage;
import testbase.Baseclass;
import utilities.Dataproviders;

public class Tc3_LoginDDT extends Baseclass{
	@Test(dataProvider = "LoginData", dataProviderClass = Dataproviders.class,groups="Datadriven")
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException {
	    logger.info("Starting Tc3_LoginDDT...");

	    try {
	        Homepage hp = new Homepage(driver);
	        hp.clickmyaccount();
	        hp.clicklogin();

	        Loginpage lp = new Loginpage(driver);
	        lp.setemail(email);
	        lp.password(pwd);
	        lp.clicklogin();

	        Myaccountpage macc = new Myaccountpage(driver);
	        boolean targetpage = macc.ismyaccountpageexist();

	        if (exp.equalsIgnoreCase("valid")) {
	            if (targetpage==true) {
	                Assert.assertTrue(true);
	                macc.clickLogout();
	            } else {
	                Assert.assertTrue(false);
	            }
	        } else if (exp.equalsIgnoreCase("invalid")) {
	            if (targetpage==true) {
	                macc.clickLogout();
	                Assert.assertTrue(false);
	            } else {
	                Assert.assertTrue(true);
	            }
	        }
	    } catch (Exception e) {
	        logger.error("Exception in Tc3_LoginDDT: " + e.getMessage());
	        Assert.fail();
	    }

	    Thread.sleep(3000);
	    logger.info("Finishing Tc3_LoginDDT...");
	}

}

