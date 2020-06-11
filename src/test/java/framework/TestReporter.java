package framework;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;


/**
 * Creates XML file that contains the results of each testmethod. 
 * Following data is stored: Name, Status, Time, Screenshot (location)
 * File is saved in /report with unique timestamp
 */
public class TestReporter implements TestWatcher, AfterAllCallback, BeforeAllCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback {

	static File junitReport;
	static BufferedWriter junitWriter;
	public WebDriver driver;
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

	private static final String START_TIME = "start time";

	@Override
	public void beforeTestExecution(ExtensionContext context) throws Exception {
		getStore(context).put(START_TIME, System.currentTimeMillis());
	}

	@Override
	public void afterTestExecution(ExtensionContext context) throws Exception {
		long startTime = getStore(context).remove(START_TIME, long.class);
	    long duration = System.currentTimeMillis() - startTime;
	    long timeSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
	    
	    junitWriter.write("<time testname=\"" + String.format("%s", context.getDisplayName())+ "\" seconds=\"" + String.format("%s", timeSeconds) + "\"></time>\r\n");
	}


	private Store getStore(ExtensionContext context) {
		return context.getStore(Namespace.create(getClass(), context.getRequiredTestMethod()));
	}

	    

	@Override
	public void beforeAll(ExtensionContext context) throws Exception {
		String XMLReportFile = System.getProperty("user.dir") + "./report/results" + timeStamp + ".xml";
		junitReport = new File(XMLReportFile);
		junitWriter = new BufferedWriter(new FileWriter(junitReport, true));
		junitWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
				+ "<?xml-stylesheet type=\"text/xsl\" href=\"index.xsl\"?>\r\n" + "<testsuite>\r\n");


	}

	@Override
	public void testDisabled(ExtensionContext context, Optional<String> reason) {
		try {

			junitWriter.write("<testcase>\r\n" + "<name>" + context.getDisplayName() + "</name>\r\n" + "<status>"
					+ "disabled" + "</status>\r\n" + "</testcase>\r\n");

		} catch (Exception e1) {
			System.out.println(e1.getMessage());

		}

	}

	@Override
	public void testSuccessful(ExtensionContext context) {

		try {

			junitWriter.write("<testcase>\r\n" + "<name>" + context.getDisplayName() + "</name>\r\n" + "<status>"
					+ "passed" + "</status>\r\n" + "<time>" + "</time>\r\n" + "<screenshot>screenshots/"
					+ context.getDisplayName() + ".png</screenshot>\r\n" + "</testcase>\r\n");
			
			
			    

		} catch (Exception e1) {
			System.out.println(e1.getMessage());

		}
		
		//Issue with webdriver, work in progress
		/*TakesScreenshot scrShot = (TakesScreenshot) driver;
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(SrcFile,
					new File("./target/HTMLreport/screenshots/." + context.getDisplayName() + ".png"));
		} catch (IOException e3) {
			e3.printStackTrace();
			System.err.println("Not able to copy the screenshot.");
		}*/
		
	
	
	}

	@Override
	public void testAborted(ExtensionContext context, Throwable cause) {
		
		try {

			junitWriter.write("<testcase>\r\n" + "<name>" + context.getDisplayName() + "</name>\r\n" + "<status>"
					+ "aborted" + "</status>\r\n" + "</testcase>\r\n");

		} catch (Exception e1) {
			System.out.println(e1.getMessage());

		}

	}

	@Override
	public void testFailed(ExtensionContext context, Throwable cause) {
		try {

			junitWriter.write("<testcase>\r\n" + "<name>" + context.getDisplayName() + "</name>\r\n" + "<status>"
					+ "failed" + "</status>\r\n" + "<time>" + "</time>\r\n" + "<screenshot>screenshots/"
					+ context.getDisplayName() + ".png</screenshot>\r\n" + "<error> <![CDATA[" + cause.fillInStackTrace() + "]]> </error>\r\n" + "</testcase>\r\n");

		} catch (Exception e1) {
			System.out.println(e1.getMessage());

		}
	}

	@Override
	public void afterAll(ExtensionContext context) throws Exception {
		junitWriter.write("</testsuite>");
		junitWriter.close();
		Desktop.getDesktop().browse(junitReport.toURI());
	}

}
