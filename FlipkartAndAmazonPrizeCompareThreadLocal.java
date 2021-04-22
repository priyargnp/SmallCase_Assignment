package pages;

import org.openqa.selenium.WebDriver;

public class FlipkartAndAmazonPrizeCompareThreadLocal {
	private static ThreadLocal<FlipkartAndAmazonPrizeComparePage> FlipkartAndAmazonPrizeCompare = new ThreadLocal<>();

    public FlipkartAndAmazonPrizeCompareThreadLocal(WebDriver driver) {
    	FlipkartAndAmazonPrizeCompare.set(new FlipkartAndAmazonPrizeComparePage(driver));
    }

    public FlipkartAndAmazonPrizeComparePage getFlipkartAndAmazonPrizeCompare() {
        return this.FlipkartAndAmazonPrizeCompare.get();
    }

}
