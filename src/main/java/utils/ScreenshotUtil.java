package utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
	static WebDriver driver = null;

	public ScreenshotUtil(WebDriver driver) {
		ScreenshotUtil.driver = driver;
	}
	
	public static String takeScreenshotAsBase64() {
		String screenshotLocation = System.getProperty("user.dir")+"target/ScreenShots/"+UUID.randomUUID().toString()+".jpg";
		String scrBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		File file = OutputType.FILE.convertFromBase64Png(scrBase64);
		try {
			FileUtils.copyFile(file, new File(screenshotLocation), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scrBase64;
	}

}
