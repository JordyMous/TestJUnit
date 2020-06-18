package framework;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.tools.DriverManager;
import framework.tools.TestLogger;
import framework.tools.TestReporter;

public class BaseTest  {

	protected WebDriver driver;
	
	private static Properties configProperties;
	private static FileInputStream configPropertiesFile;
	
	private static String baseURI;
	
	private static DriverManager manager;

	/**
	 * Method that runs once before all tests
	 * Initializes the Properties-file & the DriverManager
	 */
	@BeforeAll
	public static void beforeAll() throws IOException {
		configProperties = new Properties();
		configPropertiesFile = new FileInputStream("properties/config.properties");
		configProperties.load(configPropertiesFile);
		
		baseURI = configProperties.getProperty("baseuri");
		
		manager = new DriverManager(configProperties);
		manager.download();
	}

	/**
	 * Method that runs before every test
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	@BeforeEach
	public void beforeSuite() throws MalformedURLException, IOException {			
		driver = manager.getDriver();
	}

	/**
	 * Method that runs after every test
	 * @param testInfo
	 */
	@AfterEach
	public void afterEach(TestInfo testInfo) {
	 
		if (Boolean.parseBoolean(configProperties.getProperty("takescreenshotafter"))) {   		
            TakesScreenshot scrShot =(TakesScreenshot)driver;
            File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
            try {
            	FileHandler.copy(SrcFile,new File("./report/screenshots/"+ "screenshot-"+ (testInfo.getDisplayName().replace("(TestInfo)", ""))+".png"));
            } catch (IOException e) {
    			e.printStackTrace();
    			System.err.println("Not able to copy the screenshot.");
    		}
    	}
		
		if (driver != null) {
			driver.quit();
		}
	}
	
	public Logger getLogger() {
	final Logger LOG = LoggerFactory.getLogger(TestLogger.class);
	return LOG;
	}

	/**
	 * Return the webdriver
	 * @return
	 */
	public WebDriver getDriver() {
		return driver;
	}
	
	/**
	 * Return the baseURI
	 * @return
	 */
	public String getBaseURI() {
		return baseURI;
	}
	
	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	/**
	 * Method used to take a screenshot during a certain step in the testcase.
	 * Screenshot will be saved in separate folder.
	 * @param testInfo 
	 */
    public void takeScreenshot(TestInfo testInfo) {
    	if (Boolean.parseBoolean(configProperties.getProperty("takescreenshotduring"))) {   		
            TakesScreenshot scrShot =(TakesScreenshot)driver;
            File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            try {
    			FileHandler.copy(SrcFile,new File("./screenshots/screenshot "+ (testInfo.getDisplayName().replace("(TestInfo)", " "))+timeStamp+".png"));
    		} catch (IOException e) {
    			e.printStackTrace();
    			System.err.println("Not able to copy the screenshot.");
    		}
    	}
    }

    
}