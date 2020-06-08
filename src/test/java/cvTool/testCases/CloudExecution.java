package cvTool.testCases;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import cvTool.pageObjects.LoginPage;
import framework.BaseTest;
import framework.ParallelizedTest;
 
@RunWith(ParallelizedTest.class)
public class CloudExecution extends BaseTest{
	public String username = "jordy.mous";
    public String accesskey = "hSPKLw8qq2FYgCfQ9dhCxSMN6PiIns1zJYW5mJN0yrsmWSNzVQ";
    public String gridURL = "@hub.lambdatest.com/wd/hub";
 
    public String platform;
    public String browserName;
    public String browserVersion;
 
 
    public RemoteWebDriver driver = null;
 
    boolean status = false;
  
    @Parameterized.Parameters
    public static LinkedList<String[]> getEnvironments() throws Exception {
    	LinkedList<String[]> env = new LinkedList<String[]>();
        env.add(new String[]{"WIN10", "chrome", "70.0"});
        env.add(new String[]{"macos 10.12","firefox","62.0"});
        env.add(new String[]{"WIN8","internet explorer","10"});
        return env;
    }
 
 
   public CloudExecution(String platform, String browserName, String browserVersion) {
        this.platform = platform;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
     }
 
    @Before
    public void setUp() throws Exception {
    	System.out.println("HAHAHAHAHAHAHA");
    	
       DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("version", browserVersion);
        capabilities.setCapability("platform", platform); // If this cap isn't specified, it will just get the any available one
        capabilities.setCapability("build", "Sample");
        capabilities.setCapability("name", "Sample");
        capabilities.setCapability("network", true); // To enable network logs
        capabilities.setCapability("visual", true); // To enable step by step screenshot
        capabilities.setCapability("video", true); // To enable video recording
        capabilities.setCapability("console", true); // To capture console logs
        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }    
    
    @Test
    public void testParallel() throws Exception {
        try {
        		LoginPage loginPage = new LoginPage(driver);
        		loginPage.enterCredentials();
        		takeSnapShot();

        	}
        	catch (Exception e) {
             System.out.println(e.getMessage());
         }
     }
   

    @After
    public void tearDown() throws Exception {
       if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}