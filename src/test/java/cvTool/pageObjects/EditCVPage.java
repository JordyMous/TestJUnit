package cvTool.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import framework.BasePage;
import framework.elements.Button;
import framework.elements.TextInput;
import framework.factory.ElementFactory;

/**
 * Page object containing the elements and possible interactions.
 */
public class EditCVPage extends BasePage {

	public EditCVPage(WebDriver driver) {
		super(driver);
		ElementFactory.initElements(driver, this);
		driver.get("https://cvtooldemo-test.herokuapp.com/curriculum_vitae_preview/edit");
	}
	
	@FindBy(xpath = "//a[contains(text(), 'Edit User Info')]")
	private Button editUserInfoBtn;
	
	@FindBy(xpath = "//a[contains(text(), 'Add project')]")
	private Button newProjectBtn;
	
	@FindBy(xpath = "//a[contains(text(), 'Add certificate')]")
	private Button newCertificateBtn;
	
	@FindBy(xpath = "//a[contains(text(), 'Add technical skill')]")
	private Button newSkillBtn;
	
	@FindBy(xpath = "//a[contains(text(), 'Add training')]")
	private Button newTrainingBtn;
	
	@FindBy(xpath = "//a[contains(text(), 'Add language')]")
	private Button newLanguageBtn;
	
	@FindBy(xpath = "//tr[1]/td[1]" )
	private TextInput firstProjectNameFld;
	
	@FindBy(xpath = "//th[.='Certificate']/../../../tbody/tr[1]/td[1]" )
	private TextInput firstCertificatetNameFld;
	
	@FindBy(xpath = "//th[.='Technical skill']/../../../tbody/tr[1]/td[1]" )
	private TextInput firstSkillFld;
	
	@FindBy(xpath = "//th[.='Training']/../../../tbody/tr[1]/td[1]" )
	private TextInput firstTrainingsnameFld;

	@FindBy(xpath = "//th[.='Language']/../../../tbody/tr[1]/td[1]" )
	private TextInput firstLanguageFld;

	@FindBy(xpath = "//*[contains(@class, 'btn-xs btn-danger')]")
	private Button deleteContentBtn;

	
	/**
	 * Click the edit user info button.
	 */
	public void clickEditUserInfoBtn() {
		editUserInfoBtn.click();
	}
	
	/**
	 * Click the new project button.
	 */
	public void clickNewProjectBtn() {
		newProjectBtn.click();
	}
	
	/**
	 * Click the new certificate button.
	 */
	public void clickNewCertificateBtn() {
		newCertificateBtn.click();
	}
	
	/**
	 * Click the new skill button.
	 */
	public void clickNewSkillBtn() {
		newSkillBtn.click();
	}
	
	/**
	 * Click the new training button.
	 */
	public void clickNewTrainingBtn() {
		newTrainingBtn.click();
	}
	
	/**
	 * Click the new language button.
	 */
	public void clickNewLanguageBtn() {
		newLanguageBtn.click();
	}
	
	public String getFirstProjectName() {
		return firstProjectNameFld.getAttribute("innerHTML");
	}
	
	public String getFirstCertificateName() {
		return firstCertificatetNameFld.getAttribute("innerHTML");
	}
	
	public String getSkill() {
		return firstSkillFld.getAttribute("innerHTML");
	}
	
	public String getTrainingsName() {
		return firstTrainingsnameFld.getAttribute("innerHTML");
	}
	
	public String getLanguage() {
		return firstLanguageFld.getAttribute("innerHTML");
	}

	
	public void deleteContent() {
		deleteContentBtn.click();
		driver.switchTo().alert().accept();
	}
	

}
