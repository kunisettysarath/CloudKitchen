package generallibraries;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import utils.PropertyUtil;
import utils.Reporter;
import utils.ScreenshotUtil;

public class BaseClass{

	private static WebDriver driver = null;
	private static Reporter report = null;
	ScreenshotUtil screenshotUtil = null;
	Logger log = Logger.getLogger(BaseClass.class);
	String browserName=null;

	public void initializeTestReporting(String testName) {
		report.startTest(testName);
		log.info("testcase " + testName + " initialized for reporting");
	}

	@BeforeSuite(alwaysRun = true)
	public void beforeSuit() {
		report = new Reporter();
		report.configReporter();
		log.info("configuring reports");
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeTest() {
		browserName = PropertyUtil.getData("BrowserName");
		report.addExtraInfoToReport("Os", "Windows");
		report.addExtraInfoToReport("Browser", browserName);
		log.info("browser type:  " + browserName);
		driver = new InitializeBrowser(driver).getDriver(browserName);
		screenshotUtil = new ScreenshotUtil(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(TestConstants.TIMEOUT_PERIOD_MEDIUM, TimeUnit.SECONDS);
		log.info(PropertyUtil.getData("websiteURL") + " website launching");
		driver.get(PropertyUtil.getData("websiteURL"));
		new ObjectRepository(driver, report).initializeClasses();
		log.info("Object repository instantiated");
	}

	@AfterMethod(alwaysRun = true)
	public void testListeners(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				log.fatal(result.getThrowable().getMessage());
				report.testFailed("TEST FAILED ===> " + result.getName() + " | REASON ===> "
						+ result.getThrowable().getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (ITestResult.SUCCESS == result.getStatus()) {
			try {
				log.info(result.getName() + " TEST PASSED");
				report.stepPassed("TEST PASSED ===> " + result.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (ITestResult.SKIP == result.getStatus()) {
			try {
				log.info(result.getName() + " TEST SKIPPED");
				report.testFailed("TEST SKIPPED ===> " + result.getName() + " | REASON ===> "
						+ result.getThrowable().getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		driver.close();
	}

	@AfterSuite(alwaysRun = true)
	public void aSuit() {
		log.info("wrapping up with driver and reporting");
		report.stopReporting();
	}
}