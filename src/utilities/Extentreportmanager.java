package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testbase.Baseclass;

public class Extentreportmanager implements ITestListener
{
 public ExtentSparkReporter sparkreporter;
 public ExtentReports extent;
 public ExtentTest test;
 
 String repName;
 @Override
 public void onStart(ITestContext testcontext) {
	 
	// SimpleDateFormat df=new SimpleDateFormat("YYYY.MM.dd.HH.mm.ss");
	//		 Date dt=new Date();
	//		 String currentdatetimestamp=df.format(dt);
	 
	 String timestamp=new SimpleDateFormat("YYYY.MM.dd.HH.mm.ss").format(new Date());
	 repName="Test-Report-"+timestamp+".html";
	 sparkreporter=new ExtentSparkReporter(".\\reports\\"+repName);
	 
	 sparkreporter.config().setReportName("opencart functional testing");
	 sparkreporter.config().setDocumentTitle("opencart Automation report");
	 sparkreporter.config().setTheme(Theme.DARK);

     extent = new ExtentReports();
     extent.attachReporter(sparkreporter);
     extent.setSystemInfo("Application", "opencart");
     extent.setSystemInfo("Module", "Admin");
     extent.setSystemInfo("Sub Module", "customers");
     extent.setSystemInfo("User Name", System.getProperty("user.name"));
     extent.setSystemInfo("Environment", "QA");
     
     String os=testcontext.getCurrentXmlTest().getParameter("os");
     extent.setSystemInfo("Operating System", os);
     
     String browser=testcontext.getCurrentXmlTest().getParameter("browser");
     extent.setSystemInfo("Browser", browser);
     
     List<String> includegroups=testcontext.getCurrentXmlTest().getIncludedGroups();
     if(!includegroups.isEmpty())
     {
    	 extent.setSystemInfo("Groups", includegroups.toString()); 
     }  
 }

 public void onTestSuccess(ITestResult result) {
    test=extent.createTest(result.getTestClass().getName());
    test.assignCategory(result.getMethod().getGroups());
    test.log(Status.PASS,result.getName()+"got successfully executed");
 }

 public void onTestFailure(ITestResult result) {
	 test=extent.createTest(result.getTestClass().getName());
	    test.assignCategory(result.getMethod().getGroups());
	    
	    test.log(Status.FAIL,result.getName()+"got failed");
	    test.log(Status.INFO,result.getThrowable().getMessage());
	    
	    try
	    {
	    	String imgpath=new Baseclass().captureScreen(result.getName());
	    	test.addScreenCaptureFromPath(imgpath);
	    }catch(Exception e1)
	    {
	    	e1.printStackTrace();
	    }
 }
 public void onTestSkipped(ITestResult result) {
	 test=extent.createTest(result.getTestClass().getName());
	    test.assignCategory(result.getMethod().getGroups());
	    test.log(Status.SKIP,result.getThrowable()+"got skipped");
	    test.log(Status.INFO,result.getThrowable().getMessage());
 }
 
 public void onFinish(ITestContext testcontext) {
	 
	 extent.flush();
	 
	 String pathofExtentreport=System.getProperty("user.dir")+"\\reports\\"+repName;
	 File extentreport=new File(pathofExtentreport);
	 
	 try {
		 Desktop.getDesktop().browse(extentreport.toURI());
	 }catch(IOException e)
	 {
		 e.printStackTrace();
	 }
	 try
	 {
		 /*URL url=new URL("file:///"+System.getProperty("user.dir")+"\\\\reports"+repName);
		 
		 ImageHtmlEmail email=new ImageHtmlEmail();
		 email.setDataSourceResolver(new DataSourceUrlResolver(url));
		 email.setHostName("smtp.googlemail.com");
		 email.setSmtpPort(465);
		 email.setAuthenticator(new DefaultAuthenticator("kumudanayak96@gmail.com","password"));
		 email.setSSLOnConnect(true);
		 email.setFrom("kumudanayak96@gmail.com");
		 email.setSubject("Test Results");
		 email.setMsg("please find attched reports...");
		 email.addTo("kumudanayak706@gmail.com");
		 email.attach(url,"extent report","please check reports...");
		 email.send(); */
	 }catch(Exception e)
	 {
		 e.printStackTrace();
	 }
	 
 }
}
