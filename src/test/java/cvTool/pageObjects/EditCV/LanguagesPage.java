package cvTool.pageObjects.EditCV;

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
public class LanguagesPage extends BasePage {

	public LanguagesPage(WebDriver driver) {
		super(driver);
		ElementFactory.initElements(driver, this);
		driver.get("https://cvtooldemo-test.herokuapp.com/languages/new");
	}
	
	
	@FindBy(id = "language_language")
	private TextInput languageFld;
	
	@FindBy(id = "language_level_spoken")
	private Dropdown languageLevelSpokenDrpdwn;
	
	@FindBy(id = "language_level_written")
	private Dropdown languageLevelWrittenDrpdwn;
	
	@FindBy(id = "language_level_comprehension")
	private Dropdown languageLevelComprehensionDrpdwn;

	@FindBy(xpath = "//*[@type='submit']")
	private Button saveBtn;
	
	
	/**
	 * Used to clear inputfield and enter new language.
	 * @param language = language as string
	 */
	public void editLanguage(String language) {	
		languageFld.set(language);
	}
	
	/**
	 * Used to enter level as string
	 * @param level Mother Tongue='1', Fluent='2', Advanced='3' and Beginner='4'
	 */
	public void selectLanguageLevelSpoken(String level) {		
		languageLevelSpokenDrpdwn.selectByValue(level);
	}
	
	/**
	 * Used to enter level as string
	 * @param level Mother Tongue='1', Fluent='2', Advanced='3' and Beginner='4'
	 */
	public void selectLanguageLevelWritten(String level) {		
		languageLevelWrittenDrpdwn.selectByValue(level);
	}
	
	/**
	 * Used to enter level as string
	 * @param level Mother Tongue='1', Fluent='2', Advanced='3' and Beginner='4'
	 */
	public void selectLanguageLevelComprehension(String level) {		
		languageLevelComprehensionDrpdwn.selectByValue(level);
	}
	
	/**
	 * Save changes
	 */
	public void clickSave() {
		saveBtn.click();
	}

}
