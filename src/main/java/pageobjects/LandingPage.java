package pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import reusables.ReusableComponents;

public class LandingPage extends ReusableComponents {
	WebDriver driver;
	public static ExtentTest logger;
	public static ExtentReports report;
	WebElement productLocator;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "a[onclick=\"byCat('notebook')\"]")
	WebElement productCss;
	
	public void getLandingPage() {
		driver.get("https://www.demoblaze.com/");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)", "");
		threadWait1();
	}

	public void clickonProduct(String productName)
	{
		clickBasedOnText(productName);
	}


}
