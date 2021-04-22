package pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage {
	private final int DEFAULT_ELEMENT_TIMEOUT = 30;
	private final String URL = "https://www.flipkart.com/";

	@FindBy(xpath = "//input[@placeholder=\"Search for products, brands and more\"]")
	private WebElement txtBx_Search;
	@FindBy(xpath = "//button[@type=\"submit\"]/*[1]")
	private WebElement btn_Search;
	@FindBy(xpath = "//a[@rel=\"noopener noreferrer\"]//div[@class=\"_4rR01T\"]")
	private List<WebElement> lst_searchResults;
	@FindBy(xpath = "(//div[@id='container']//span)[14]")
	private WebElement label_ItemTitleInNavigatedPage;
	@FindBy(xpath = "(//div[@id='container']//div[contains(.,'₹')])[11]")
	private WebElement price;
	@FindBy(xpath = "//button[contains(.,'ADD TO CART')]")
	private WebElement btn_AddToCart;
	@FindBy(xpath = "//div[text()='Save for later']//preceding::button[1]")
	private WebElement btn_Increase;
	@FindBy(xpath = "//div[text()='Total Amount']//following::span[contains(text(),'₹')]")
	private WebElement priceAfterAddingMoreItems;
	@FindBy(xpath = "//button[text()='✕']")
	private WebElement loginPopUpClose;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, DEFAULT_ELEMENT_TIMEOUT), this);
	}
	
	public String searchItemAndNavigate() {
		txtBx_Search.sendKeys("redmi note 8 pro");
		btn_Search.click();
		String firstItemName = lst_searchResults.get(0).getText();
		lst_searchResults.get(0).click();
		return firstItemName;
	}

	public String navigateToItem() {
		String navigatedItemName = label_ItemTitleInNavigatedPage.getText();
		return navigatedItemName;
	}

	public int printPrice() {
		String priceBeforeIncreasingQuantity = price.getText();
		String splitpriceBefore = priceBeforeIncreasingQuantity.substring(1);
		int priceBeforeIncrasingQuantity = Integer.parseInt(splitpriceBefore.replaceAll(",", ""));
		return priceBeforeIncrasingQuantity;
	}

	public String getURL() {
		return URL;
	}

	public void closeLoginPopUp() {
		loginPopUpClose.click();
	}

	public void addItemToCart() {
		btn_AddToCart.click();
	}

	public void IncreaseQuantity() {
		btn_Increase.click();
	}

	public int printPriceAfterIncrease() {
		String priceAfterIncreasingQuantity = priceAfterAddingMoreItems.getText();
		String splitpriceAfter = priceAfterIncreasingQuantity.substring(1);
		int priceAfterIncrasingQuantity = Integer.parseInt(splitpriceAfter.replaceAll(",", ""));
		return priceAfterIncrasingQuantity;
	}
}
