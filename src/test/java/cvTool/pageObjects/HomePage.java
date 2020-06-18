package cvTool.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import framework.BasePage;
import framework.elements.Alert;
import framework.elements.Button;
import framework.factory.ElementFactory;

/**
 * Page object containing the elements and possible interactions.
 */
public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		ElementFactory.initElements(driver, this);
		driver.get("https://cvtooldemo-test.herokuapp.com/");
		
	}
	

	@FindBy(xpath = "//*[@data-modal='true']")
	private Button userInfoBtn;

	@FindBy(xpath = "//a[contains(@href, '/users/edit')]")
	public Button editInfoBtn;
	
	@FindBy(xpath = "//*[@class='alert alert-success fade in']")
	public Alert signUpAlert;
	
	@FindBy(className = "collapse-sign")
	private Button openCVMenuBtn;
	
	@FindBy(xpath = "//span[.='Edit']")
	public Button editCVBtn;


	/**
	 * Click the user info button.
	 */
	public void clickUserInfo() {
		userInfoBtn.click();
	}
	
	/**
	 * Click the edit info button.
	 */
	public void clickEditInfo() {
		waitforElementToBeClickable(By.xpath("//a[contains(@href, '/users/edit')]"));
		editInfoBtn.click();
	}
	
	/**
	 * Click to open the CV menu.
	 */
	public void clickOpenCVMenu() {
		openCVMenuBtn.click();
	}
	
	/**
	 * Click to edit the CV.
	 * Wait for element is already implemented
	 */
	public void clickEditCV() {
		waitforElementToBeClickable(By.xpath("//span[.='Edit']"));
		editCVBtn.click();
	}
	
	/**
	 * Used to return the text of alert message
	 * @return = alert message as string
	 */
	public String getAlertText() {
		return signUpAlert.getInnerText();
	}
	
	
	
}
