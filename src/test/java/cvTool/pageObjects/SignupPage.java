package cvTool.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import framework.BasePage;
import framework.elements.Alert;
import framework.elements.Button;
import framework.elements.TextInput;
import framework.factory.ElementFactory;

/**
 * Page object containing the elements and possible interactions.
 */
public class SignupPage extends BasePage {

	public SignupPage(WebDriver driver) {
		super(driver);
		ElementFactory.initElements(driver, this);
		driver.get("https://cvtooldemo-test.herokuapp.com/users/sign_up");
	}
	
	@FindBy(id = "user_email")
	private TextInput emailFld;
	
	@FindBy(id = "user_password")
	private TextInput passwordFld;
	
	@FindBy(id = "user_password_confirmation")
	private TextInput passwordConfirmFld;
	
	@FindBy(id = "user_first_name")
	private TextInput firstNamedFld;
	
	@FindBy(id = "user_last_name")
	private TextInput lastNameFld;
	
	@FindBy(id = "create_account_register_button")
	private Button signUpBtn;
	
	
	/**
	 * Used to enter Signup credentials
	 * @param email = email adress as string
	 * @param pw = password as string
	 * @param pw2 = password(confirmation) as string
	 * @param firstName = first name as string
	 * @param lastName = last name as string
	 */
	public void enterCredentialsWithData(String email, String pw, String pw2, String firstName, String lastName) {	
		emailFld.set(email);	
		passwordFld.set(pw);
		passwordConfirmFld.set(pw2);
		firstNamedFld.set(firstName);
		lastNameFld.set(lastName);
	}
	
	/**
	 * Click signup button
	 */
	public void clickSignup() {
		signUpBtn.click();
	}
	

	
}


