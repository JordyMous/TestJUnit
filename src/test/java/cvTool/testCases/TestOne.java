package cvTool.testCases;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import cvTool.pageObjects.LoginPage;
import framework.BaseTest;

public class TestOne extends BaseTest {
	
	@Tag("Test")
	@DisplayName("Test1.1")
	@Test
	public void TestOne1(TestInfo testInfo) {
		LoginPage loginPage = new LoginPage(getDriver());
		getLogger().info("Opening Login page");
		loginPage.enterCredentials();
		getLogger().info("Entering credentials to login");
		loginPage.clickLogin();
		getLogger().info("Click login button");
		takeScreenshot(testInfo);
		String url = getCurrentURL();
		String URI = getBaseURI();
		assertEquals(url, URI, "Unsuccessfull Login:");
	
	}
}