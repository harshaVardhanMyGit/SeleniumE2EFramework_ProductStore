package mainclass;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pageobjects.ProductPage;

public class MainCode extends BaseTest {
	public ProductPage GiftcardPage;

	@Test(dataProvider = "getData")
	public void automationCode(HashMap<String, String> input) throws IOException {

	
	}

	@DataProvider
	public Object[] getData() throws IOException {
		HashMap<String, String> map = getDataFromJsonToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\data\\urban_ladder.json");
		return new Object[] { map };
		// for multiple sets of data Object[][]
	}
}
