package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import reusables.ReusableComponents;

public class ProductPage extends ReusableComponents {
	WebDriver driver;
	public static ExtentTest logger;
	public static ExtentReports report;

	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void handleAlert()
	{
		alertHandling();
	}

	public void driverClose()
	{
		close();
	}

}
