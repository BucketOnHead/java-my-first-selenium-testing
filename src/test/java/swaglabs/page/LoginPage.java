package swaglabs.page;

import config.ConfigProvider;
import core.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://www.saucedemo.com/
public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(xpath = "//div[@class='error-message-container error']")
    private WebElement errorMessageContainer;

    public LoginPage() {
        driver.get(ConfigProvider.LOGIN_URL);
        PageFactory.initElements(driver, this);
    }

    public boolean tryAuth(String login, String password) {
        this.username.sendKeys(login);
        this.password.sendKeys(password, Keys.ENTER);

        return driver.getCurrentUrl().equals(ConfigProvider.MAIN_URL);
    }

    public MainPage auth(String login, String password) {
        if (!tryAuth(login, password)) {
            throw new RuntimeException("Authorization error");
        }

        return new MainPage();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return errorMessageContainer.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException ex) {
            return false;
        }
    }

    public String getErrorMessageText() {
        return errorMessageContainer.getText();
    }
}