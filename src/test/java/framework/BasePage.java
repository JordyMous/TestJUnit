package framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.factory.ElementFactory;

/**
 * BasePage that needs to be implemented in each page object (extends)
 *
 */
public class BasePage {

	private static final int TIMEOUT = 5; // seconds
	private static final int POLLING = 100; // milliseconds
	
	
	private static Properties configProperties;
	private static FileInputStream configPropertiesFile;
	
	protected WebDriver driver;
	private static WebDriverWait wait;
	
	/**
	 * Variable webdriver wait that can be set from the config.properties file. 
	 * Is implemented in the page objects. 
	 * @return
	 * @throws FileNotFoundException
	 */
    public WebDriverWait variableWait() throws FileNotFoundException {
		configProperties = new Properties();
		configPropertiesFile = new FileInputStream("properties/config.properties");
		try {
			configProperties.load(configPropertiesFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    	String wait = configProperties.getProperty("wait");
    	Long seconds = Long.parseLong(wait);
    	return new WebDriverWait(driver, seconds);
    
    }
    
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, TIMEOUT, POLLING);
		ElementFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
		
	}
	


	protected void waitForElementToAppear(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	protected void waitForElementToDisappear(By locator) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	protected void waitForTextToDisappear(By locator, String text) {
		wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, text)));
	}
	
	
	public void waitforElementToBeClickable(By locator) {
		try {
			variableWait().until(ExpectedConditions.elementToBeClickable(locator));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
}