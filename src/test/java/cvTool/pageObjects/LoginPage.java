package cvTool.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import framework.BasePage;
import framework.elements.Button;
import framework.elements.TextInput;
import framework.factory.ElementFactory;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		ElementFactory.initElements(driver, this);
		driver.get("https://cvtooldemo-test.herokuapp.com/users/sign_in");
	}

	@FindBy(id = "user_email")
	private TextInput emailFld;
	
	@FindBy(id = "user_password")
	private TextInput passwordFld;

	@FindBy(xpath = "//*[@id=\"login-form\"]/footer/button")
	private Button signInBtn;
	
	public void enterCredentials() {	
		emailFld.set("jordymous@outlook.com");	
		passwordFld.set("ThisIsATest123");
	}
	
	/**
	 * Same functionality as 'enterCredentials' but uses parameters to enter the email and password. 
	 * Needed for the data-driven testcase.
	 * @param email String
	 * @param pw String
	 */
	public void enterCredentialsWithData(String email, String pw) {	
		emailFld.set(email);	
		passwordFld.set(pw);
	}
	
	public void clickLogin() {
		signInBtn.click();
	}
}
