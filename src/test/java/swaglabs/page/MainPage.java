package swaglabs.page;

import config.ConfigProvider;
import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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

    public MainPage() {
        driver.get(ConfigProvider.MAIN_URL);
        PageFactory.initElements(driver, this);
    }

    public MainPage selectProductSortByValue(String value) {
        var productSort = new Select(productSortContainer);
        productSort.selectByValue(value);

        return this;
    }

    public List<WebElement> getProducts() {
        return productContainer.findElements(By.xpath("//div[@class='inventory_item']"));
    }

    public AboutPage clickAboutButton() {
        leftMenuButton.click();
        aboutButton.click();

        return new AboutPage();
    }

    public LoginPage clickLogoutButton() {
        leftMenuButton.click();
        logoutButton.click();

        return new LoginPage();
    }
}
