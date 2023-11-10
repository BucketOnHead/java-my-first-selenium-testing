package swaglabs.page;

import config.ConfigProvider;
import core.BasePage;
import org.openqa.selenium.support.PageFactory;

// url_page = https://saucelabs.com/
public class AboutPage extends BasePage {

    public AboutPage() {
        driver.get(ConfigProvider.ABOUT_URL);
        PageFactory.initElements(driver, this);
    }
}
