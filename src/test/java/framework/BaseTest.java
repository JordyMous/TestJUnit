package framework;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;

public class BaseTest {

	private WebDriver driver;
	private static Properties configProperties;
	private static FileInputStream configPropertiesFile;
	private static boolean headless;
	
	private static DriverManager manager;

	@BeforeAll
	public static void beforeAll() throws IOException {
		configProperties = new Properties();
		configPropertiesFile = new FileInputStream("properties/config.properties");
		configProperties.load(configPropertiesFile);
		
		manager = new DriverManager(configProperties);
		manager.download();
	}

	@BeforeEach
	public void beforeSuite() throws MalformedURLException, IOException {			
		driver = manager.getDriver();
	}

	@AfterEach
	public void afterSuite() {
		if (driver != null) {
			driver.quit();
		}
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	/**
	 * Method used to take a screenshot during a certain step in the testcase. 
	 * Screenshot will be saved in separate folder.
	 */
    public void takeSnapShot() {
    	if (Boolean.parseBoolean(configProperties.getProperty("takesnapshot"))) {   		
            TakesScreenshot scrShot =(TakesScreenshot)driver;
            File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            try {
    			FileHandler.copy(SrcFile,new File("./screenshots/screenshot "+this.getClass().getName()+timeStamp+".png"));
    		} catch (IOException e) {
    			e.printStackTrace();
    			System.err.println("Not able to copy the screenshot.");
    		}
    	}
    }
    
    
}