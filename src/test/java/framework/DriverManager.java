package framework;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManager {
	
	private static String browser;
	private static WebDriver driver;
	private static Properties configProperties;
	private static boolean headless;

	public DriverManager(Properties configProperties) {
		this.configProperties = configProperties;
		this.browser = configProperties.getProperty("browser").toLowerCase();
		/**
		 * Run the test in a headless browser when the system doesn't support a GUI or when the user defines this in the config
		 */
		if (GraphicsEnvironment.isHeadless() || Boolean.parseBoolean(configProperties.getProperty("headless"))) {
			headless = true;
		}
	}
	
	public void download() throws IOException {	
		System.out.println("///////////////////////////////// " + browser);
		
		switch(browser) {
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
	}
	
	public WebDriver getDriver() {
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
				if (headless) {
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
		
		return driver;
	}
}