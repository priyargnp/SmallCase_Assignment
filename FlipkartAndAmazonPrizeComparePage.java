package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class FlipkartAndAmazonPrizeComparePage {
	private final int DEFAULT_ELEMENT_TIMEOUT = 40;
	private final static String URL = "https://www.amazon.in/ref=nav_logo";

	@FindBy(xpath = "//input[@id=\"twotabsearchtextbox\"]")
	private WebElement txtBx_Search;
	@FindBy(xpath = "//input[@id=\"nav-search-submit-button\"]")
	private WebElement btn_Search;
	@FindBy(xpath = "//span[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]//span[@class=\"a-size-medium a-color-base a-text-normal\"]")
	private List<WebElement> lst_searchResults;
	@FindBy(xpath = "//span[@id=\"productTitle\"]")
	private WebElement label_ItemTitleInNavigatedPage;
	@FindBy(xpath = "(//span[@data-action=\"swatchthumb-action\"])[2]")
	private WebElement btn_RAM;
	@FindBy(xpath = "//span[@id=\"priceblock_ourprice\"]")
	private WebElement price;
	@FindBy(xpath = "//input[@id=\"add-to-cart-button\"]")
	private WebElement btn_AddToCart;
	@FindBy(xpath = "//*[@id=\"attach-sidesheet-view-cart-button-announce\" or @id=\"hlb-view-cart-announce\"]")
	private WebElement btn_Cart;
	@FindBy(xpath = "//span[@data-action=\"a-dropdown-button\"]")
	private WebElement btn_Increase;
	@FindBy(xpath = "//a[.=\"2\"]")
	private WebElement select_Quantity;
	@FindBy(xpath = "//span[@id='sc-subtotal-amount-activecart']")
	private WebElement priceAfterAddingMoreItems;

	public FlipkartAndAmazonPrizeComparePage(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, DEFAULT_ELEMENT_TIMEOUT), this);
	}

	public static String getURL() {
		return URL;
	}

	public String searchItemAndNavigate() {
		txtBx_Search.sendKeys("redmi note 8 pro");
		btn_Search.click();
		String firstItemName = lst_searchResults.get(1).getText();
		lst_searchResults.get(1).click();
		return firstItemName;
	}

	public String navigateToItem() {
		String navigatedItemName = label_ItemTitleInNavigatedPage.getText();
		return navigatedItemName;
	}

	public int printPrice() {
		String priceBeforeIncreasingQuantity = price.getText();
		String splitpriceBefore = priceBeforeIncreasingQuantity.substring(1);
		String splitpriceBeforeRemoved = splitpriceBefore.substring(1, 7);
//		int priceBeforeIncrasingQuantity = Integer.parseInt(splitpriceBeforeRemoved);
		int priceBeforeIncrasingQuantity = Integer.parseInt(splitpriceBeforeRemoved.replaceAll(",", ""));
//		priceBeforeIncrasingQuantity = Integer.parseInt(splitpriceBefore.replaceAll("&"+"nbsp;", ""));
		return priceBeforeIncrasingQuantity;
	}

	public WebElement selectRAM() {
		return btn_RAM;
	}

	public WebElement addItemToCart() {
		return btn_AddToCart;
	}

	public WebElement clickCart() {
		return btn_Cart;
	}

	public void IncreaseQuantity() {
		btn_Increase.click();
		select_Quantity.click();
	}

	public int printPriceAfterIncrease() {
		String priceAfterIncreasingQuantity = priceAfterAddingMoreItems.getText();
		String splitpriceAfter = priceAfterIncreasingQuantity.substring(1);
		String splitpriceAfterRemoved = splitpriceAfter.substring(2, 8);
		int priceAfterIncrasingQuantity = Integer.parseInt(splitpriceAfterRemoved.replaceAll(",", ""));
//		priceAfterIncrasingQuantity = Integer.parseInt(splitpriceAfter.replaceAll("&"+"nbsp;", ""));
		return priceAfterIncrasingQuantity;
	}
}
