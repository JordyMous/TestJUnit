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
public class CertificatesPage extends BasePage {

	public CertificatesPage(WebDriver driver) {
		super(driver);
		ElementFactory.initElements(driver, this);
		driver.get("https://cvtooldemo-test.herokuapp.com/certificates/new");
	}
	
	@FindBy(id = "certificate_certificate")
	private TextInput certificateNameFld;
	
	@FindBy(id = "certificate_distributor")
	private TextInput certificateDistributorFld;
	
	@FindBy(id = "certificate_year")
	private Dropdown certificateYearDrpdwn;

	@FindBy(xpath = "//*[@type='submit']")
	private Button saveBtn;
	

	/**
	 * Used to clear the inputfield and enter a name for the certificate.
	 * @param name = name of certificate as string
	 */
	public void editCertificateName(String name) {	
		certificateNameFld.set(name);
	}
	
	/**
	 * Used to clear the inputfield and enter the distributors name.
	 * @param distributor = name of distributor as string
	 */
	public void editCertificateDistributor(String distributor) {	
		certificateDistributorFld.set(distributor);
	}
	
	/**
	 * Used to enter year as string
	 * @param year = year between 1980 - 2030 as string
	 */
	public void selectCertificateYear(String year) {		
		certificateYearDrpdwn.selectByValue(year);
	}
	
	/**
	 * Used to save changes
	 */
	public void clickSave() {
		saveBtn.click();
	}

}
