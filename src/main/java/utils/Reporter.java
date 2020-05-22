package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import generallibraries.TestConstants;

public class Reporter {
	ExtentReports extent;
	ExtentTest test;

	public void configReporter() {
		ExtentHtmlReporter report = new ExtentHtmlReporter("./target/ExtentReports/report.html");
		File dir = new File("./target/ExtentReports");
		if (!dir.exists())
			dir.mkdirs();
		extent = new ExtentReports();
		extent.attachReporter(report); 
		report.config().setTheme(Theme.DARK);
	}

	public void startTest(String name) {
		test = extent.createTest(name);
	}


	public void addInfoInReport(String infoMsg) {
			test.log(Status.INFO, infoMsg);
	}

	public void addErrorInReport(String errorMsg) {
			test.log(Status.ERROR, errorMsg);
	}

	public void addExtraInfoToReport(String name, String value) {
		extent.setSystemInfo(name, value);
	}

	public void testFailed(String message) {
		try {
				test.fail(message,
						MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtil.takeScreenshotAsBase64()).build());		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void testSkipped(String message) {
		try {
				test.skip(message, MediaEntityBuilder
						.createScreenCaptureFromBase64String(ScreenshotUtil.takeScreenshotAsBase64()).build());		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void serviceStepFailed(String message) {
			test.fail(message);
	}

	public void serviceStepSkipped(String message) {
			test.skip(message);
	}

	public void stepPassed(String message) {
		String messageSplit[] = message.replace("By.", "->").split("->");
		try {
				test.pass(messageSplit[0] + " " + StringUtils.chop(messageSplit[2]),
						MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtil.takeScreenshotAsBase64()).build());
		} catch (ArrayIndexOutOfBoundsException e) {
			try {
					test.pass(message,
							MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtil.takeScreenshotAsBase64()).build());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	public void stopReporting() {
		extent.flush();
	}
}