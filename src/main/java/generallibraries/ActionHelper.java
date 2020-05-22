package generallibraries;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.Reporter;

public class ActionHelper {

	WebDriver driver;
	Reporter report;
	Logger log = Logger.getLogger(ActionHelper.class);

	public ActionHelper(WebDriver driver, Reporter report) {
		this.driver = driver;
		this.report = report;
	}

	protected void clickOnElement(WebElement element) {
		report.stepPassed("clicking on the element:->" + element);
		try {
			log.info("clicking on element----->" + element);
			element.click();
		} catch (Exception e) {
			log.info(ExceptionUtils.getStackTrace(e));
			report.testFailed(ExceptionUtils.getStackTrace(e));
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	protected void sendTextToElement(WebElement element, String data) {
		try {
			log.info("sending text to element----->" + element);
			element.sendKeys(data);
			report.stepPassed("text sent to element:->" + element);
		} catch (Exception e) {
			log.info(ExceptionUtils.getStackTrace(e));
			report.testFailed(ExceptionUtils.getStackTrace(e));
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	protected String getElementText(WebElement element) {
		waitForElement(element);
		log.info("\"" + element.getText() + "\" is the text obtained from element -->" + element);
		return element.getText();
	}

	protected boolean isElementExists(WebElement element) {
		waitForElement(element);
		report.stepPassed("Element found--->" + element);
		log.info("element found -->" + element);
		return element.isDisplayed();
	}

	protected void waitForElement(WebElement element) {
		log.info("waiting for element---->" + element);
		try {
			WebDriverWait wait = new WebDriverWait(driver, TestConstants.TIMEOUT_PERIOD_MEDIUM);
			wait.until(ExpectedConditions.visibilityOf(element));
			log.info("element found--->" + element);
		} catch (TimeoutException e) {
			log.info(ExceptionUtils.getStackTrace(e));
			report.testFailed(ExceptionUtils.getStackTrace(e));
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	protected void waitForElementNotVisible(WebElement element) {
		log.info("waiting for element to be invisible---->" + element);
		try {
			WebDriverWait wait = new WebDriverWait(driver, TestConstants.TIMEOUT_PERIOD_SHORT);
			if (element.isDisplayed())
				wait.until(ExpectedConditions.invisibilityOf(element));
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
