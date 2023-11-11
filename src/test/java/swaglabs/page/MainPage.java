package swaglabs.page;

import config.ConfigProvider;
import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import swaglabs.entity.Item;
import utils.holder.Holder;

import java.util.List;

// page_url = https://www.saucedemo.com/inventory.html
public class MainPage extends BasePage {

    @FindBy(xpath = "//select[@class='product_sort_container']")
    private WebElement productSortContainer;

    @FindBy(xpath = "//div[@class='inventory_list']")
    private WebElement productContainer;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement leftMenuButton;

    @FindBy(id = "about_sidebar_link")
    private WebElement aboutButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;

    @FindBy(id = "reset_sidebar_link")
    private WebElement resetAppStateButton;

    @FindBy(id = "shopping_cart_container")
    private WebElement cartButton;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    public MainPage() {
        driver.get(ConfigProvider.MAIN_URL);
        PageFactory.initElements(driver, this);
    }

    public MainPage selectProductSortByValue(String value) {
        var productSort = new Select(productSortContainer);
        productSort.selectByValue(value);

        return this;
    }

    public MainPage addProductByName(String name) {
        var productElement = getProductElementByName(name);
        var addToCartButton = productElement.findElement(By.xpath("//div[@class='inventory_item']//button"));
        addToCartButton.click();

        return this;
    }

    public MainPage addProductByIdx(int i) {
        String xpath = String.format("//div[@class='inventory_item'][%d]//button", i + 1);
        var addToCartButton = driver.findElement(By.xpath(xpath));
        addToCartButton.click();

        return this;
    }

    public MainPage getProductByName(String name, Item item) {
        var productElement = getProductElementByName(name);
        parseItem(productElement, item);

        return this;
    }

    public MainPage getProductByIdx(Integer i, Item item) {
        var productElement = getProductElementByIdx(i);
        parseItem(productElement, item);

        return this;
    }

    public MainPage getCartBadgeValue(Holder<String> holder) {
        holder.setValue(cartBadge.getText());
        return this;
    }

    public MainPage clickLeftMenuButton() {
        leftMenuButton.click();
        return this;
    }

    public MainPage clickResetAppStateButton() {
        resetAppStateButton.click();
        return this;
    }

    public AboutPage clickAboutButton() {
        aboutButton.click();
        return new AboutPage();
    }

    public LoginPage clickLogoutButton() {
        logoutButton.click();
        return new LoginPage();
    }

    public CartPage clickCartButton() {
        cartButton.click();
        return new CartPage();
    }

    public List<Item> getProducts() {
        return productContainer.findElements(By.xpath("//div[@class='inventory_item']"))
                .stream()
                .map(MainPage::parseItem)
                .toList();
    }

    private WebElement getProductElementByName(String name) {
        var xpath = String.format("//div[@class='inventory_item' and contains(.//text(), '%s')]", name);
        return productContainer.findElement(By.xpath(xpath));
    }

    private WebElement getProductElementByIdx(Integer i) {
        var xpath = String.format("//div[@class='inventory_item'][%d]", i + 1);
        return productContainer.findElement(By.xpath(xpath));
    }

    private static Item parseItem(WebElement itemWebElement) {
        return parseItem(itemWebElement, null);
    }

    private static Item parseItem(WebElement itemElement, Item item) {
        var name = itemElement.findElement(By.className("inventory_item_name")).getText();
        var price = itemElement.findElement(By.className("inventory_item_price")).getText();
        var description = itemElement.findElement(By.className("inventory_item_desc")).getText();

        if (item == null) {
            item = new Item();
        }

        item.setName(name);
        item.setPrice(price);
        item.setDescription(description);

        return item;
    }
}
