package generallibraries;

import org.openqa.selenium.WebDriver;

import pages.CloudKitchenHomePage;
import steps.CloudKitchenHomeSteps;
import utils.Reporter;

public class ObjectRepository {
	static WebDriver driver =null;
	static Reporter report =null;
	public ObjectRepository(WebDriver driver, Reporter report){
		ObjectRepository.driver = driver;
		ObjectRepository.report = report;
	}
	
	public static CloudKitchenHomePage cloudKitchenHomePage;
	public static CloudKitchenHomeSteps cloudKitchenHomeSteps;
	public void initializeClasses() {
		cloudKitchenHomePage = new CloudKitchenHomePage(driver, report);
		cloudKitchenHomeSteps = new CloudKitchenHomeSteps(driver, report);
		
	}
	
}
