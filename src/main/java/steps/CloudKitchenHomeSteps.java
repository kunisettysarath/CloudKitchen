package steps;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import pages.CloudKitchenHomePage;
import utils.Reporter;

public class CloudKitchenHomeSteps extends CloudKitchenHomePage{

	public CloudKitchenHomeSteps(WebDriver driver, Reporter report) {
		super(driver, report);
		// TODO Auto-generated constructor stub
	}

	public void verifytitleAndSelectMamacita() {
		Assert.assertTrue(isElementExists(homePageLogo));
		clickOnElement(agreeCookie);
		clickOnElement(mamcitaImage);
	}
	
	public void verifyAddressPopUpAndInput(String address) {
		System.out.println(addressPopUp);
		Assert.assertTrue(isElementExists(addressPopUp));
		sendTextToElement(addressLocation, address);
		clickOnElement(toTheMenuButton);
		waitForElementNotVisible(loadingIcon);
	}
	
	public void addItemToCart(String item) {
		isElementExists(mamcitaTitle);
		clickOnElement(getItemXpath(item));
		isElementExists(addedCartItemName);
		System.out.println(getElementText(addedCartItemName));
		Assert.assertTrue(item.equals(getElementText(addedCartItemName)));
	}
}
