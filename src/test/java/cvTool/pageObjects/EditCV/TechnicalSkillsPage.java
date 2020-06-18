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
public class TechnicalSkillsPage extends BasePage{

	public TechnicalSkillsPage(WebDriver driver) {
		super(driver);
		ElementFactory.initElements(driver, this);
		driver.get("https://cvtooldemo-test.herokuapp.com/technical_skills/new");
	}
	
	@FindBy(id = "technical_skill_technical_skill")
	private TextInput skillFld;
	
	@FindBy(id = "technical_skill_level")
	private Dropdown levelDrpdwn;

	@FindBy(xpath = "//*[@type='submit']")
	private Button saveBtn;
	
	
	/**
	 * Used to clear inputfield and enter a new skill.
	 * @param skill = technical skill as string
	 */
	public void editTechnicalSkill(String skill) {	
		skillFld.set(skill);
	}
	
	
	/**
	 * Used to enter technical skill level
	 * @param level Basic = '1', Intermediate = '2' and Advanced = '3'
	 */
	public void selectSkillLevel(String level) {		
		levelDrpdwn.selectByValue(level);
	}
	
	/**
	 * Save changes.
	 */
	public void clickSave() {
		saveBtn.click();
	}
}
