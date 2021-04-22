package tests;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import drivers.ThreadLocalDriver;
import pages.FlipkartAndAmazonPrizeCompareThreadLocal;
import pages.HomepageThreadLocal;

public class GlobalTests {
	protected ThreadLocalDriver threadLocalDriver;
	protected Logger logger = Logger.getGlobal();

	protected HomepageThreadLocal homePage;
	protected FlipkartAndAmazonPrizeCompareThreadLocal FlipkartAndAmazonPrizeCompare;

	protected final int BROWSER_WAIT_MILLISECONDS = 4000;

	@BeforeMethod
	void setTestConfiguration() {
		threadLocalDriver = new ThreadLocalDriver();
		homePage = new HomepageThreadLocal(getDriver());
		FlipkartAndAmazonPrizeCompare = new FlipkartAndAmazonPrizeCompareThreadLocal(getDriver());
	}

	@AfterMethod
	void closeBrowser() {
		getDriver().quit();
	}

	protected void delay() throws InterruptedException {
		Thread.sleep(BROWSER_WAIT_MILLISECONDS);
	}

	protected void waitBeforeClosingBrowser() throws InterruptedException {
		Thread.sleep(BROWSER_WAIT_MILLISECONDS);
	}

	protected WebDriver getDriver() {
		return threadLocalDriver.getDriver();
	}

//Property file creation
	public boolean createPropertyFile(String name, Map<String, String> values) {
		try (OutputStream out = new FileOutputStream(new File(name + ".properties"));) {
			Properties props = new Properties();
			props.putAll(values);
			props.store(out, name);
			return true;
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return false;
		}
	}
}
