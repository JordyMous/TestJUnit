package cvTool.testCases;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import cvTool.pageObjects.LoginPage;
import framework.BaseTest;

public class LoginTest extends BaseTest {
	@Test
	public void LoginTest1() {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.enterCredentials();
		loginPage.clickLogin();
		takeSnapShot();
	}
	
	@Test
	public void LoginTest2() {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.enterCredentials();
		loginPage.clickLogin();
		takeSnapShot();

	}
	
	@Test
	public void LoginTest3() {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.enterCredentials();
		loginPage.clickLogin();
		takeSnapShot();
	}
	
	/**
	 * Testcase that is Data-driven. Data such as email and password are used from csv. 
	 * @param email	String
	 * @param pw String
	 */
	/*@ParameterizedTest
	@CsvFileSource(resources = "data.csv", numLinesToSkip = 1, delimiter = ',')
	public void LoginTest_DataDriven(String email, String pw) {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.enterCredentialsWithData(email, pw);
		loginPage.clickLogin();
		//takeSnapShot();
	}*/

	
	
}