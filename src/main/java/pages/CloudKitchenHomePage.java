package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generallibraries.ActionHelper;
import utils.Reporter;

public class CloudKitchenHomePage extends ActionHelper{
	public WebDriver driver = null;
	
	public CloudKitchenHomePage(WebDriver driver, Reporter report) {
		super(driver, report);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="img[alt='Clubkitchen - zur Startseite wechseln']")
	public WebElement homePageLogo;
	
	@FindBy(css="button[class=\"agree-cookie\"]")
	public WebElement agreeCookie;
	
	@FindBy(xpath="/html/body/div/section/div/div/div/div/div[2]/section/div[4]/div/div/a")
	public WebElement mamcitaImage;
	
	@FindBy(id="address-input")
	public WebElement addressLocation;
	
	@FindBy(xpath="(//*[text()=' Gib deine Adresse ein, um das Menü zu sehen. '])[2]")
	public WebElement addressPopUp;
	
	@FindBy(css="input[type=\"submit\"]")
	public WebElement toTheMenuButton;
	
	@FindBy(css="div[class=\"icon--default\"]")
	public WebElement loadingIcon;
	
	@FindBy(xpath="(//a[@title=\"Mamacita\"])[2]")
	public WebElement mamcitaTitle;

	@FindBy(css="span[class=\"item--name\"]")
	public WebElement addedCartItemName;
	
	String itemAddToCartXpath = "//*[text()=' && ']/parent::a/parent::form/following-sibling::div[3]/child::form/child::button";
	
	public WebElement getItemXpath(String item) {
		itemAddToCartXpath = itemAddToCartXpath.replace("&&", item);
		return driver.findElement(By.xpath(itemAddToCartXpath));
	}
	
}
