package stepdefintion;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import base.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.ProductPage;

public class ProductStoreDef extends BaseTest {
	String userName = "practice";
	String password = "SuperSecretPassword!";
	WebElement productLocator;
	
	@Given("User opens the website")
	public void open_the_website() throws IOException
	{
		launchWebApplication();
	}
	
	@When("User clicks on {string}")
	public void user_clicks_on_product(String productName)
	{
		clickBasedOnText(productName);
	}
	
	@And("User is able to see {string}")
	public void user_is_able_to_see(String productName) {
		visibilityBasedOnText(productName);
	}
	
	@Then("User accepts the alert button")
	public void accept_alert_button()
	{
		ProductPage ProductPage = new ProductPage(driver);
		ProductPage.handleAlert();
	}
}
