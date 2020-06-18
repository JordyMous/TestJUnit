package framework.tools;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

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

import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;

import framework.BaseTest;

/**
 * Used to create an XML file that contains the results of each testmethod. Following data
 * is stored: Name, Status, Time File is saved in /report with unique timestamp
 */
public class TestReporter extends BaseTest implements TestWatcher, AfterAllCallback, BeforeAllCallback,
		BeforeTestExecutionCallback, AfterTestExecutionCallback {

	static File junitReport;
	static BufferedWriter junitWriter;
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
		String name = (context.getDisplayName().replace("(TestInfo)", ""));
		junitWriter.write("<time testname=\"" + String.format("%s", name) + "\" seconds=\""
				+ String.format("%s", timeSeconds) + "\"></time>\r\n");
	}

	private Store getStore(ExtensionContext context) {
		return context.getStore(Namespace.create(getClass(), (context.getDisplayName().replace("(TestInfo)", ""))));
	}

	@Override
	public void beforeAll(ExtensionContext context) throws Exception {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		

		
		String XMLReportFile = System.getProperty("user.dir") + "./report/Results-" +context.getDisplayName() +"-" + timeStamp + ".xml";
		junitReport = new File(XMLReportFile);
		junitWriter = new BufferedWriter(new FileWriter(junitReport, true));
		junitWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
				+ "<?xml-stylesheet type=\"text/xsl\" href=\"index.xsl\"?>\r\n" + "<testsuite>\r\n");
	}

	@Override
	public void testDisabled(ExtensionContext context, Optional<String> reason) {
		try {

			junitWriter.write("<testcase>\r\n" + "<name>" + (context.getDisplayName().replace("(TestInfo)", "")) + "</name>\r\n" + "<status>"
					+ "disabled" + "</status>\r\n" + "</testcase>\r\n");

		} catch (Exception e1) {
			System.out.println(e1.getMessage());

		}

	}

	@Override
	public void testSuccessful(ExtensionContext context) {

		try {

			junitWriter.write("<testcase>\r\n" + "<name>" + (context.getDisplayName().replace("(TestInfo)", "")) + "</name>\r\n" + "<status>"
					+ "passed" + "</status>\r\n" + "<time>" + "</time>\r\n" + "<screenshot>screenshots/screenshot-"
					+ (context.getDisplayName().replace("(TestInfo)", "")) + ".png</screenshot>\r\n" + "</testcase>\r\n");

			System.err.println(context.getConfigurationParameter("driver"));
		} catch (Exception e1) {
			System.out.println(e1.getMessage());

		}

	}

	@Override
	public void testAborted(ExtensionContext context, Throwable cause) {

		try {

			junitWriter.write("<testcase>\r\n" + "<name>" + (context.getDisplayName().replace("(TestInfo)", "")) + "</name>\r\n" + "<status>"
					+ "aborted" + "</status>\r\n" + "</testcase>\r\n");

		} catch (Exception e1) {
			System.out.println(e1.getMessage());

		}

	}

	@Override
	public void testFailed(ExtensionContext context, Throwable cause) {
		try {

			junitWriter.write("<testcase>\r\n" + "<name>" + (context.getDisplayName().replace("(TestInfo)", "")) + "</name>\r\n" + "<status>"
					+ "failed" + "</status>\r\n" + "<time>" + "</time>\r\n" + "<screenshot>screenshots/screenshot-"
					+ (context.getDisplayName().replace("(TestInfo)", "")) + ".png</screenshot>\r\n" + "<error> <![CDATA["
					+ cause.fillInStackTrace() + "]]> </error>\r\n" + "</testcase>\r\n");

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
