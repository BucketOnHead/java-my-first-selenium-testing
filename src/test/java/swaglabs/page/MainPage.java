package swaglabs.page;

import config.ConfigProvider;
import core.BasePage;
import org.openqa.selenium.support.PageFactory;

// page_url = https://www.saucedemo.com/inventory.html
public class MainPage extends BasePage {

    public MainPage() {
        driver.get(ConfigProvider.MAIN_URL);
        PageFactory.initElements(driver, this);
    }
}
