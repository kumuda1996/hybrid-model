package testbase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class Baseclass {
    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups= {"Sanity","Regression","Master","Datadriven"})
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException {
        // Load config file
        FileReader file = new FileReader("./testresources/config.properties");
        p = new Properties();
        p.load(file);

        // Initialize logger
        logger = LogManager.getLogger(this.getClass());
        logger.info("Launching browser: " + br);

          if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
          {
        	  DesiredCapabilities capabilities=new DesiredCapabilities();
        	  
        	  if(os.equalsIgnoreCase("windows"))
        	  {
        		  capabilities.setPlatform(Platform.WIN10);
        	  }
        	  else if(os.equalsIgnoreCase("mac"))
        	  {
        		  capabilities.setPlatform(Platform.MAC);
        	  }
        	  else
        	  {
        		  System.out.println("no matching os");
        		  return;
        	  }
        	  
        	  switch (br.toLowerCase()) {
              case "chrome":capabilities.setBrowserName("chrome");break;
              case "firefox":capabilities.setBrowserName("firefox");break;
              case "edge":capabilities.setBrowserName("edge");break;
              default:System.out.println("no matching browser");return;             
          }
        	  driver=new RemoteWebDriver(new URL("http://192.168.0.203:4444/wd/hub"),capabilities);
          
         }  
        
          if(p.getProperty("execution_env").equalsIgnoreCase("local"))  
          {
        // Launch browser
        switch (br.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Invalid browser: " + br);
        }
      }
          
          

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        // Read and open URL
        String url = p.getProperty("appurl");
        if (url == null || url.isEmpty()) {
            throw new RuntimeException("Application URL is missing in config.properties.");
        }

        driver.get(url);
        driver.manage().window().maximize();
    }

    @AfterClass(groups={"Sanity","Regression","Master","Datadriven"})
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Utility random methods
    public String randomeString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomeNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomAlphaNumeric() {
    	// TODO Auto-generated method stub
    	return RandomStringUtils.randomAlphabetic(5) + "@" + RandomStringUtils.randomNumeric(5);
    }

	public String captureScreen(String tname) throws IOException{
		String timestamp=new SimpleDateFormat("YYYY.MM.dd.HH.mm.ss").format(new Date());
		
		TakesScreenshot ts=(TakesScreenshot) driver;
		File srcfile=ts.getScreenshotAs(OutputType.FILE);
		
		String targetfilepath=System.getProperty("user.dir")+"\\screenshots"+ tname + "_"+ timestamp +".png";
		File targetfile=new File(targetfilepath);
		
		srcfile.renameTo(targetfile);
		return targetfilepath;
		
	}
}
