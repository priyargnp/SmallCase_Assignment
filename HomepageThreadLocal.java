package pages;

import org.openqa.selenium.WebDriver;

public class HomepageThreadLocal {

	private static ThreadLocal<HomePage> homePage = new ThreadLocal<>();

    public HomepageThreadLocal(WebDriver driver) {
    	homePage.set(new HomePage(driver));
    }

    public HomePage getHomePage() {
        return this.homePage.get();
    }

}
