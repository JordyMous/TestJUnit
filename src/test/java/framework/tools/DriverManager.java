package framework.tools;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class DriverManager {
	
	private static String browser;
	private static String platform;
	private static boolean headless;
	private static WebDriver driver;
	private static Properties configProperties;

	public DriverManager(Properties configProperties) {
		this.configProperties = configProperties;
		this.browser = configProperties.getProperty("browser").toLowerCase();
		this.platform = configProperties.getProperty("platform");
		/**
		 * Run the test in a headless browser when the system doesn't support a GUI or when the user defines this in the config
		 */
		if (GraphicsEnvironment.isHeadless() || Boolean.parseBoolean(configProperties.getProperty("headless"))) {
			headless = true;
		}
	}
	
	/**
	 * Downloads and unzips the necessary webdrivers
	 */
	public void download() throws IOException {	
		if (!Boolean.parseBoolean(configProperties.getProperty("cloud"))) {
			switch(browser) {
				case "chrome":
					File chromeDriver = new File(configProperties.getProperty("chromepath"));
					if (!chromeDriver.exists() || Boolean.parseBoolean(configProperties.getProperty("forcedownload"))) {
						killDriver("chromedriver");
						new DriverDownloader(configProperties.getProperty("chromeurl"), "drivers");
					}
					break;
				case "firefox":
					File firefoxDriver = new File(configProperties.getProperty("chromepath"));
					if (!firefoxDriver.exists() || Boolean.parseBoolean(configProperties.getProperty("forcedownload"))) {	
						killDriver("geckodriver");
						new DriverDownloader(configProperties.getProperty("firefoxurl"), "drivers");
					}
					break;
				case "edge":
					File edgeDriver = new File(configProperties.getProperty("edgepath"));
					if (!edgeDriver.exists() || Boolean.parseBoolean(configProperties.getProperty("forcedownload"))) {
						killDriver("msedgedriver");
						new DriverDownloader(configProperties.getProperty("edgeurl"), "drivers");
					}
					break;
			}
		}
	}
	
	/**
	 * Creates the correct webdriver instance
	 */
	public WebDriver getDriver() throws MalformedURLException {
		if (Boolean.parseBoolean(configProperties.getProperty("cloud"))) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("browserName", browser);
			capabilities.setCapability("platform", platform);
			
			driver = new RemoteWebDriver(new URL("https://" + configProperties.getProperty("username") + ":" + configProperties.getProperty("accesskey") + configProperties.getProperty("gridurl")), capabilities);
		} else {
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
					killDriver("chromedriver");
				}
				
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver", configProperties.getProperty("firefoxpath"));
				
				try {
					if (headless) {
						FirefoxOptions firefoxOptions = new FirefoxOptions();
						firefoxOptions.setHeadless(true);
						driver = new FirefoxDriver(firefoxOptions);
					} else {
						driver = new FirefoxDriver();
					}
				} catch (WebDriverException e) {
					e.printStackTrace();
					killDriver("geckodriver");
				}
				
				break;
			case "edge":
				System.setProperty("webdriver.edge.driver", configProperties.getProperty("edgepath"));
				
				try {
					driver = new EdgeDriver();
				} catch (WebDriverException e) {
					e.printStackTrace();
					killDriver("msedgedriver");
				}
				
				break;
			}
		}
		
		/**
		 * Maximizes the browser window when the user defines this in the config
		 */
		if (Boolean.parseBoolean(configProperties.getProperty("maximize"))) {
			driver.manage().window().maximize();
		}
		
		return driver;
	}
	
	private void killDriver(String driver) {
		new DriverKiller(driver);
	}
}