package cvTool.pageObjects.EditCV;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import framework.BasePage;
import framework.elements.Button;
import framework.elements.TextInput;
import framework.factory.ElementFactory;

/**
 * Page object containing the elements and possible interactions.
 */
public class ProjectsPage extends BasePage {
	
	public ProjectsPage(WebDriver driver) {
		super(driver);
		ElementFactory.initElements(driver, this);
		driver.get("https://cvtooldemo-test.herokuapp.com/projects/new");
	}

	
	
	@FindBy(id = "project_name")
	private TextInput projectNameFld;
	
	@FindBy(id = "project_role")
	private TextInput projectRoleFld;
	
	@FindBy(id = "project_start_date")
	private TextInput projectStartDateFld;
	
	@FindBy(id = "project_end_date")
	private TextInput projectEndDateFld;
	
	@FindBy(id = "project_description")
	private TextInput projectDescriptionFld;
	
	@FindBy(id = "project_tasks")
	private TextInput projectTasksFld;
	
	@FindBy(id = "project_tools")
	private TextInput projectToolsFld;
	
	@FindBy(id = "project_industry")
	private TextInput projectIndustryFld;
	
	@FindBy(id = "project_methodology")
	private TextInput projectMethodologyFld;
	
	@FindBy(xpath = "//*[@type='submit']")
	private Button saveBtn;
	
	
	
	/**
	 * Used to clear inputfield and enter name of new project.
	 * @param name = name of project as string
	 */
	public void editProjectName(String name) {	
		projectNameFld.set(name);
	}
	
	/**
	 * Used to clear inputfield and enter role during project
	 * @param role = role during project as string
	 */
	public void editProjectRole(String role) {	
		projectRoleFld.set(role);
	}
	
	/**
	 * Used to select the start date of a project.
	 * @param date = start date as string (dd/mm/yyyy)
	 */
	public void editProjectStartDate(String date) {	
		projectStartDateFld.set(date);
	}
	
	/**
	 * Used to select the end date of a project.
	 * @param date = end date as string (dd/mm/yyyy)
	 */
	public void editProjectEndDate(String date) {	
		projectEndDateFld.set(date);
	}
	
	/**
	 * Used to clear inputfield and enter project description.
	 * @param description = project description as string
	 */
	public void editProjectDescription(String description) {	
		projectDescriptionFld.set(description);
	}
	
	/**
	 * Used to clear inputfield and enter tasks during project.
	 * @param tasks = tasks during project as string
	 */
	public void editProjectTasks(String tasks) {	
		projectTasksFld.set(tasks);
	}
	
	/**
	 * Used to clear inputfield and enter tools used during project.
	 * @param tools = tools used during project as string
	 */
	public void editProjectTools(String tools) {	
		projectToolsFld.set(tools);
	}
	
	/**
	 * Used to clear inputfield and enter industry. 
	 * @param industry = project industry as string
	 */
	public void editProjectIndustry(String industry) {	
		projectIndustryFld.set(industry);
	}
	
	/**
	 * Used to clear inputfield and enter methodology
	 * @param methodology = methodology as string
	 */
	public void editProjectMethodology(String methodology) {	
		projectMethodologyFld.set(methodology);
	}
	
	/**
	 * Save changes.
	 */
	public void clickSave() {
		saveBtn.click();
	}
}
