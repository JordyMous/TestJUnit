package cvTool.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import framework.BasePage;
import framework.elements.Button;
import framework.elements.Dropdown;
import framework.elements.TextInput;
import framework.factory.ElementFactory;

/**
 * Page object containing the elements and possible interactions.
 */
public class EditInfoPage extends BasePage {

	public EditInfoPage(WebDriver driver) {
		super(driver);
		ElementFactory.initElements(driver, this);
		driver.get("https://cvtooldemo-test.herokuapp.com/users/edit");
	}
	
	@FindBy(id = "user_email")
	private TextInput emailFld;
	
	@FindBy(id = "user_password")
	private TextInput passwordFld;
	
	@FindBy(id = "user_password_confirmation")
	private TextInput passwordConfirmFld;
	
	@FindBy(id = "user_first_name")
	private TextInput firstNameFld;
	
	@FindBy(id = "user_last_name")
	private TextInput lastNameFld;
	
	@FindBy(id = "user_gender")
	private Dropdown genderDrpdwn;
	
	@FindBy(id = "user_date_of_birth")
	private TextInput dateFld;
	
	@FindBy(id = "user_country")
	private Dropdown countryDrpdwn;
	
	@FindBy(id = "user_city")
	private TextInput cityFld;
	
	@FindBy(id = "user_profile")
	private TextInput profileFld;
	
	@FindBy(id = "user_competencies")
	private TextInput competenciesFld;
	
	@FindBy(id = "user_education")
	private TextInput educationFld;

	@FindBy(id = "user_current_password")
	private TextInput currentPwFld;
	
	@FindBy(xpath = "//*[@type='submit']")
	private Button updateBtn;
	
	/**
	 * Used to enter new email adress. 
	 * @param email = email adress as string
	 */
	public void editEmai(String email) {	
		emailFld.set(email);
	}
	
	/**
	 * used to enter new password and the second confirmation field for new password. 
	 * @param pw = new password as string
	 * @param pw2 = new password(confirmation) as string
	 */
	public void editPw(String pw, String pw2) {		
		passwordFld.set(pw);
		passwordConfirmFld.set(pw2);
	}
	
	/**
	 * Used to enter new first and last name.
	 * @param firstName = first name as string
	 * @param lastName = last name as string
	 */
	public void editName(String firstName, String lastName) {		
		firstNameFld.set(firstName);
		lastNameFld.set(lastName);
	}
	
	/**
	 * Used to select new country from dropdown.
	 * @param value = ISO code of country (e.g. BE, CA, ..) as string
	 */
	public void editCountry(String value) {		
		countryDrpdwn.selectByValue(value);
	}
	
	/**
	 * Used to enter new city. 
	 * @param city = name of city as string
	 */
	public void editCity(String city) {		
		cityFld.set(city);
	}
	
	/**
	 * Used to enter gender as string
	 * @param value = 'Male' or 'Female'
	 */
	public void editGender(String value) {		
		genderDrpdwn.selectByValue(value);
	}
	
	/**
	 * Used to enter date of birth.
	 * @param date = date of birth as string (dd/mm/yyyy)
	 */
	public void editDate(String date) {		
		dateFld.set(date);
	}
	
	/**
	 * Used to clear input and enter profile description. 
	 * @param profileDescr = profile description as string
	 */
	public void editProfile(String profileDescr) {		
		profileFld.set(profileDescr);
	}
	
	/**
	 * Used to clear input and enter competencies description.
	 * @param competenciesDescr = competencies as string
	 */
	public void editCompetencies(String competenciesDescr) {		
		competenciesFld.set(competenciesDescr);
	}
	
	/**
	 * Used to return the comptencies.
	 * @return = comptencies as string
	 */
	public String getCompetencies() {		
		return competenciesFld.getAttribute("value");
	}
	
	/**
	 * Used to clear inputfield and enter education.
	 * @param educationDescr = education as string
	 */
	public void editEducation(String educationDescr) {		
		educationFld.set(educationDescr);
	}
	
	/**
	 * Used to enter current password (confirmation before able to update)
	 * @param pw = password as string
	 */
	public void enterCurrentPw(String pw) {		
		currentPwFld.set(pw);
	}
	
	/**
	 * Save changes
	 */
	public void clickUpdate() {
		updateBtn.click();
	}
	
	/**
	 * Used to return profile description
	 * @return = profile description as string
	 */
	public String getProfile() {
		return profileFld.getAttribute("value");
	}
	
	/**
	 *  Used to return education description
	 * @return = education description as string
	 */
	public String getEducation() {
		return educationFld.getAttribute("value");
	}
	
	/**
	 * Used to return first name
	 * @return = first name as string
	 */
	public String getFirstName() {
		return firstNameFld.getAttribute("value");
	}
	
	/**
	 * Used to return last name
	 * @return = last name as string
	 */
	public String getLastName() {
		return lastNameFld.getAttribute("value");
	}
	
	/**
	 * Used to return gender
	 * @return = gender as string
	 */
	public String getGender() {
		return genderDrpdwn.getAttribute("value");
	}
	
	/**
	 * Used to return date of birth
	 * @return = date of birth as string
	 */
	public String getDateOfBirth() {
		return dateFld.getAttribute("value");
	}
	
	/**
	 * Used to return the country
	 * @return = Country as string
	 */
	public String getCountry() {
		return countryDrpdwn.getAttribute("value");
	}
	
	/**
	 * Used to return the city
	 * @return = city as string
	 */
	public String getCity() {
		return cityFld.getAttribute("value");
	}
	
	
	
}
