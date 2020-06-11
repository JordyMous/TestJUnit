package cvTool.testCases;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import cvTool.pageObjects.LoginPage;
import framework.BaseTest;

public class TestThree extends BaseTest {
	
    /*private static RemoteWebDriver driver = null;
	
    private static String username = "jordy.mous";
    private static String accesskey = "hSPKLw8qq2FYgCfQ9dhCxSMN6PiIns1zJYW5mJN0yrsmWSNzVQ";
    private static String gridURL = "@hub.lambdatest.com/wd/hub";
 
    private static String platform = "WIN10";
    private static String browserName = "chrome";
    private static String browserVersion = "77.0";
	
	@Test
	public void LoginTest55() {
       DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("version", browserVersion);
        capabilities.setCapability("platform", platform);
        
        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        try {
    		LoginPage loginPage = new LoginPage(driver);
    		loginPage.enterCredentials();
    		takeSnapShot();
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
        
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=");
            driver.quit();
        }
	}	*/
}