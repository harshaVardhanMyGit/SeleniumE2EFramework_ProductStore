package reusables;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableComponents {

	WebDriver driver;

	public ReusableComponents(WebDriver driver) {
		this.driver = driver;
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

	public WebElement getByText(String locatorName) {
		return driver.findElement(By.xpath("//*[contains(text(), '" + locatorName + "') and not(self::option)]"));
	}

	public void windowsScroll() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,700)", "");
		threadWait();
	}

	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-200)", "");
	}

	public void scrollDownforGiftCard() {
		threadWait1();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-1200)", "");
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

	public Actions actionsClass() {
		Actions a = new Actions(driver);
		return a;
	}

	public void moveToElement(WebElement ele) {
		actionsClass().moveToElement(ele).build().perform();
		threadWait();
	}

	public void javascriptClick(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele);
		threadWait();
	}

	public void click(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele);
		threadWait1();
	}

	public void clickBasedOnText(String name) {
		WebElement productLocator = getByText(name);
		productLocator.click();
		threadWait1();
	}

	public void dragAndDrop(WebElement ele, int x, int y) {
		actionsClass().clickAndHold(ele).dragAndDropBy(ele, x, y).build().perform();
		threadWait();
	}

	public void scrollIntoView(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		threadWait();
	}

	public void threadWait() {
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void threadWait1() {
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void highlightWebelement(WebElement ele) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].style.border='2px solid red'", ele);
		threadWait1();
	}

	public void highlightSelect(Select ele) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].style.border='2px solid red'", ele);
		threadWait1();
	}

	public String getScreenshot(String testCaseName, WebDriver driver, String filepath) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// we want screenshot as a file
		if (filepath.equalsIgnoreCase("bookshelves")) {
			File file = new File(
					System.getProperty("user.dir") + "//screenshots//bookshelves//" + dateName + testCaseName + ".png");
			FileUtils.copyFile(source, file);
			return System.getProperty("user.dir") + "//screenshots//bookshelves//" + dateName + testCaseName + ".png";
		} else {
			File file = new File(
					System.getProperty("user.dir") + "//screenshots//giftcard//" + dateName + testCaseName + ".png");
			FileUtils.copyFile(source, file);
			return System.getProperty("user.dir") + "//screenshots//giftcard//" + dateName + testCaseName + ".png";
		}

	}

	public void getMessage() {
		threadWait();
		WebElement element = driver.findElement(By.xpath("//section/section[4]/div[2]/ul/li"));
		if (element.isDisplayed()) {
			System.out.println("Error message: " + element.getText());
		} else {
			System.out.println("Successfully completed");
		}
	}

	public void close() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}

}
