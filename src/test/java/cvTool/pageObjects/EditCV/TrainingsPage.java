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
public class TrainingsPage extends BasePage {

	public TrainingsPage(WebDriver driver) {
		super(driver);
		ElementFactory.initElements(driver, this);
		driver.get("https://cvtooldemo-test.herokuapp.com/trainings/new");
	}
	
	
	@FindBy(id = "training_training")
	private TextInput trainingNameFld;
	
	@FindBy(id = "training_distributor")
	private TextInput trainingDistributorFld;
	
	@FindBy(id = "training_year")
	private Dropdown yearDrpdwn;
	
	@FindBy(id = "training_topics")
	private TextInput trainingTopicsFld;

	@FindBy(xpath = "//*[@type='submit']")
	private Button saveBtn;
	
	
	/**
	 * Used to clear inputfield and enter name of training.
	 * @param name = name of training as string
	 */
	public void editTrainingName(String name) {	
		trainingNameFld.set(name);
	}
	
	/**
	 * Used to clear inputfield and enter name of distributor of the training. 
	 * @param distributor = name of distributor as string
	 */
	public void editTrainingDistributor(String distributor) {	
		trainingDistributorFld.set(distributor);
	}
	
	/**
	 * Used to select year of training.
	 * @param year = year between 1980 - 2030 as string
	 */
	public void selectTrainingsYear(String year) {		
		yearDrpdwn.selectByValue(year);
	}
	
	/**
	 * Used to clear inputfield and enter topics of training.
	 * @param topics = topics of the training as string
	 */
	public void editTrainingTopics(String topics) {	
		trainingTopicsFld.set(topics);
	}
	
	/**
	 * Save changes. 
	 */
	public void clickSave() {
		saveBtn.click();
	}

}
