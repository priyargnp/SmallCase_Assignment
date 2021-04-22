package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

public class Tests extends GlobalTests {

	@Test(priority = 0)
	public void homePageTests() throws InterruptedException {

		// Navigate to URL.
		getDriver().get(homePage.getHomePage().getURL());
		getDriver().manage().window().maximize();
		homePage.getHomePage().closeLoginPopUp();

		String ItemClickedTitle = homePage.getHomePage().searchItemAndNavigate();
		logger.info("Item Selected is " + ItemClickedTitle);
		ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
		getDriver().switchTo().window(tabs.get(1));
		String ItemTitleInNavigatedPage = homePage.getHomePage().navigateToItem();
		logger.info("Item in Navigated Page is " + ItemTitleInNavigatedPage);
		if (ItemTitleInNavigatedPage.contains("ItemClickedTitle")) {
			logger.info("FirstItem in the search results is selected");
		}
		int priceBefore = homePage.getHomePage().printPrice();
		logger.info("Price before increasing Quantity " + priceBefore);
		String priceBeforeInString = Integer.toString(priceBefore);
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("FlipkartPrice", priceBeforeInString);
		if (!createPropertyFile("Price", data)) {
			logger.info("Property file not created");
		}
		data.put("FlipkartPrice", priceBeforeInString);
		homePage.getHomePage().addItemToCart();
		delay();
		homePage.getHomePage().IncreaseQuantity();
		getDriver().navigate().refresh();
		int priceAfter = homePage.getHomePage().printPriceAfterIncrease();
		logger.info("Price After increasing Quantity " + priceAfter);
		if (priceAfter > priceBefore) {
			logger.info("Price got increased post increasing quantity");
		} else {
			logger.info("Price didnot get increased post increasing quantity");
		}
		// Wait before closing browser..
		waitBeforeClosingBrowser();
	}

	@Test(priority = 1)
	public void ComparePriceTests() throws InterruptedException, IOException {

		// Navigate to URL.
		getDriver().get(FlipkartAndAmazonPrizeCompare.getFlipkartAndAmazonPrizeCompare().getURL());
		getDriver().manage().window().maximize();
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		String ItemClickedTitle = FlipkartAndAmazonPrizeCompare.getFlipkartAndAmazonPrizeCompare()
				.searchItemAndNavigate();
		logger.info("Item Selected is " + ItemClickedTitle);
		ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
		getDriver().switchTo().window(tabs.get(1));
		String ItemTitleInNavigatedPage = FlipkartAndAmazonPrizeCompare.getFlipkartAndAmazonPrizeCompare()
				.navigateToItem();
		logger.info("Item in Navigated Page is " + ItemTitleInNavigatedPage);
		if (ItemTitleInNavigatedPage.contains("ItemClickedTitle")) {
			logger.info("FirstItem in the search results is selected");
		}
		if (FlipkartAndAmazonPrizeCompare.getFlipkartAndAmazonPrizeCompare().selectRAM().isDisplayed()) {
			FlipkartAndAmazonPrizeCompare.getFlipkartAndAmazonPrizeCompare().selectRAM().click();
		}
		int priceBefore = FlipkartAndAmazonPrizeCompare.getFlipkartAndAmazonPrizeCompare().printPrice();
		logger.info("Price before increasing Quantity " + priceBefore);
		delay();
		executor.executeScript("arguments[0].click();",
				FlipkartAndAmazonPrizeCompare.getFlipkartAndAmazonPrizeCompare().addItemToCart());
//		FlipkartAndAmazonPrizeCompare.getFlipkartAndAmazonPrizeCompare().addItemToCart();
		delay();
		executor.executeScript("arguments[0].click();",
				FlipkartAndAmazonPrizeCompare.getFlipkartAndAmazonPrizeCompare().clickCart());
		delay();
		FileInputStream f = new FileInputStream(new File("Price.properties"));
		Properties p = new Properties();
		p.load(f);
		String priceInFlipkart = p.getProperty("FlipkartPrice");
		System.out.println(priceInFlipkart);
		int priceInFlipkartToInt = Integer.parseInt(priceInFlipkart);
		if (priceInFlipkartToInt > priceBefore) {
			logger.info("Flipkart price is higher than Amazon price");
		} else if (priceInFlipkartToInt < priceBefore) {
			logger.info("Flipkart price is cheaper than Amazon price");
		} else {
			logger.info("Flipkart and Amazon has same price");
		}
		// Wait before closing browser..
		waitBeforeClosingBrowser();
	}
}
