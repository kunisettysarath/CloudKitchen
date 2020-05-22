package generallibraries;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import utils.PropertyUtil;

public class InitializeBrowser {
	protected WebDriver driver = null;
	public InitializeBrowser(WebDriver driver) {
		// TODO Auto-generated constructor stub
		driver = this.driver;
	}

	public WebDriver getDriver(String browserType) {
		if (browserType.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", PropertyUtil.getData("chromeDriverPath"));
			driver = new ChromeDriver();
		} else {
			Assert.fail("invalid browser name passed");
		}
		return driver;
	}

}
