package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage LandingPage;

	// this is activated because before method at line: 48
	// use public to give access to other class
	public WebDriver intializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\GlobalData.properties");
		prop.load(fis);

		// this helps you to read the key value pair in globaldata properties
		// but it needs input stream file
		// so convert your file into input stream
		String browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			// this driver is assigned to global variable
			// so driver.manage will work

		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver();
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		return driver;
	}

	public HashMap<String, String> getDataFromJsonToMap(String filePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		// convert it into string variable
		// then convert string to hashmap
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, String> data = mapper.readValue(jsonContent, new TypeReference<HashMap<String, String>>() {
		});
		return data;
	}

	@BeforeMethod
	// before method also checks if there is any method in parent class
	public LandingPage launchWebApplication() throws IOException {
		driver = intializeDriver();
		LandingPage = new LandingPage(driver);
		// this landing page is initialized into public variable defined at the top of
		// the class
		LandingPage.getLandingPage();
		return LandingPage;
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// we want screenshot as a file
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	@AfterMethod
	public void close() {
		driver.close();
	}

	public WebElement getByText(String locatorName) {
		return driver.findElement(By.xpath("//*[contains(text(), '" + locatorName + "') and not(self::option)]"));
	}

	public void highlightWebelement(WebElement ele) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].style.border='2px solid red'", ele);
		threadWait1();
	}

	public void visibilityBasedOnText(String name) {
		WebElement productLocator = getByText(name);
		waitUntilVisibilityOfWebElementLocated(productLocator);
		boolean checkIt = productLocator.isDisplayed();
		Assert.assertTrue(checkIt);
	}

	public void alertHandling() {
		try {
			Alert alert = driver.switchTo().alert();
			System.out.println("Alert is present");
			alert.accept();
		} catch (NoAlertPresentException e) {
			System.out.println("Alert is not present");
		}
	}

	public void clickBasedOnText(String name) {
		WebElement productLocator = getByText(name);
		highlightWebelement(productLocator);
		productLocator.click();
		threadWait1();
	}

	public void threadWait1() {
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void waitUntilVisibilityOfElementLocated(By ele) {
		WebDriverWait wait;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
	}

	public void waitUntilVisibilityOfWebElementLocated(WebElement ele) {
		WebDriverWait wait;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

}
