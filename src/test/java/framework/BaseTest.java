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
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class BaseTest {

	private WebDriver driver;
	private static Properties configProperties;
	private static FileInputStream configPropertiesFile;
	private static boolean headless;

	@BeforeAll
	public static void beforeAll() throws IOException {
			
		configProperties = new Properties();
		configPropertiesFile = new FileInputStream("properties/config.properties");
		configProperties.load(configPropertiesFile);
		
		String browser = configProperties.getProperty("browser");
		
		switch(browser.toLowerCase()) {
			case "chrome":
				File chromeDriver = new File(configProperties.getProperty("chromepath"));
				if (!chromeDriver.exists() || Boolean.parseBoolean(configProperties.getProperty("forcedownload"))) {	
					new DriverDownloader(configProperties.getProperty("chromeurl"), "drivers");
				}
				break;
			case "firefox":
				File firefoxDriver = new File(configProperties.getProperty("chromepath"));
				if (!firefoxDriver.exists() || Boolean.parseBoolean(configProperties.getProperty("forcedownload"))) {	
					new DriverDownloader(configProperties.getProperty("firefoxurl"), "drivers");
				}
				break;
			case "edge":
				File edgeDriver = new File(configProperties.getProperty("edgepath"));
				if (!edgeDriver.exists() || Boolean.parseBoolean(configProperties.getProperty("forcedownload"))) {
					new DriverDownloader(configProperties.getProperty("edgeurl"), "drivers");
				}
				break;
		}
		
		/**
		 * Run the test in a headless browser when the system doesn't support a GUI or when the user defines this in the config
		 */
		if (GraphicsEnvironment.isHeadless() || Boolean.parseBoolean(configProperties.getProperty("headless"))) {
			headless = true;
		}
	}

	@BeforeEach
	public void beforeSuite() throws MalformedURLException, IOException {			
		
		String browser = configProperties.getProperty("browser").toLowerCase();
		
		switch(browser) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver", configProperties.getProperty("chromepath"));
				
				try {				
					if (headless) {
						ChromeOptions chromeOptions = new ChromeOptions();
						chromeOptions.setHeadless(true);
						driver = new ChromeDriver(chromeOptions);
					} else {
						driver = new ChromeDriver();
					}
				} catch (SessionNotCreatedException e) {
					e.printStackTrace();
					new DriverKiller("chromedriver");
				}
				
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver", configProperties.getProperty("firefoxpath"));
				
				try {
					if (headless ) {
						FirefoxOptions firefoxOptions = new FirefoxOptions();
						firefoxOptions.setHeadless(true);
						driver = new FirefoxDriver(firefoxOptions);
					} else {
						driver = new FirefoxDriver();
					}
				} catch (WebDriverException e) {
					e.printStackTrace();
					new DriverKiller("geckodriver");
				}
				
				break;
			case "edge":
				System.setProperty("webdriver.edge.driver", configProperties.getProperty("edgepath"));
				
				try {
					driver = new EdgeDriver();
				} catch (WebDriverException e) {
					e.printStackTrace();
					new DriverKiller("msedgedriver");
				}
				
				break;
		}
	}

	@AfterEach
	public void afterSuite() {
		if (null != driver) {
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