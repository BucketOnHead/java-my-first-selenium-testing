package swaglabs.test.login;

import config.ConfigProvider;
import config.PropertiesProvider;
import core.BaseTest;
import org.junit.jupiter.api.Test;
import swaglabs.page.LoginPage;
import swaglabs.test.login.script.AuthFormTest;
import swaglabs.test.login.script.LockedUserAuthTest;
import swaglabs.test.login.script.StandardUserAuthTest;

import static org.junit.jupiter.api.Assertions.*;

class LoginPageTest extends BaseTest
        implements AuthFormTest, StandardUserAuthTest, LockedUserAuthTest {

    @Test
    @Override
    public void authEmptyUsername() {
        var username = "";
        var password = ConfigProvider.STANDARD_USER_PASSWORD;

        var loginPage = new LoginPage();
        var passed = loginPage.tryAuth(username, password);

        assertFalse(passed, "Authorization should not be passed");
        assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
        assertEquals(PropertiesProvider.USERNAME_REQUIRED_MSG, loginPage.getErrorMessageText());
    }

    @Test
    @Override
    public void authEmptyPasswordName() {
        var username = ConfigProvider.STANDARD_USER_USERNAME;
        var password = "";

        var loginPage = new LoginPage();
        var passed = loginPage.tryAuth(username, password);

        assertFalse(passed, "Authorization should not be passed");
        assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
        assertEquals(PropertiesProvider.PASSWORD_REQUIRED_MSG, loginPage.getErrorMessageText());
    }

    @Test
    @Override
    public void authEmptyUsernameAndPassword() {
        var username = "";
        var password = "";

        var loginPage = new LoginPage();
        var passed = loginPage.tryAuth(username, password);

        assertFalse(passed, "Authorization should not be passed");
        assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
        assertEquals(PropertiesProvider.USERNAME_REQUIRED_MSG, loginPage.getErrorMessageText());
    }

    @Test
    @Override
    public void authStandardUserBadUsername() {
        var username = "bad_username";
        var password = ConfigProvider.STANDARD_USER_PASSWORD;

        var loginPage = new LoginPage();
        var passed = loginPage.tryAuth(username, password);

        assertFalse(passed, "Authorization should not be passed");
        assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
        assertEquals(PropertiesProvider.LOGIN_ERROR_MSG, loginPage.getErrorMessageText());
    }

    @Test
    @Override
    public void authStandardUserBadPassword() {
        var username = ConfigProvider.STANDARD_USER_USERNAME;
        var password = "bad_password";

        var loginPage = new LoginPage();
        var passed = loginPage.tryAuth(username, password);

        assertFalse(passed, "Authorization should not be passed");
        assertTrue(loginPage.isErrorMessageDisplayed());
        assertEquals(PropertiesProvider.LOGIN_ERROR_MSG, loginPage.getErrorMessageText());
    }

    @Test
    @Override
    public void authStandardUserBadUsernameAndPassword() {
        var username = "bad_username";
        var password = "bad_password";

        var loginPage = new LoginPage();
        var passed = loginPage.tryAuth(username, password);

        assertFalse(passed, "Authorization should not be passed");
        assertTrue(loginPage.isErrorMessageDisplayed());
        assertEquals(PropertiesProvider.LOGIN_ERROR_MSG, loginPage.getErrorMessageText());
    }

    @Test
    @Override
    public void authStandardUserSuccess() {
        var username = ConfigProvider.STANDARD_USER_USERNAME;
        var password = ConfigProvider.STANDARD_USER_PASSWORD;

        assertDoesNotThrow(() -> new LoginPage().auth(username, password),
                "Authorization should be successful, but there was an error");
    }

    @Test
    @Override
    public void authLockedUser() {
        var username = ConfigProvider.LOCKED_USER_USERNAME;
        var password = ConfigProvider.LOCKED_USER_PASSWORD;

        var loginPage = new LoginPage();
        var passed = loginPage.tryAuth(username, password);

        assertFalse(passed, "Authorization should not be passed");
        assertTrue(loginPage.isErrorMessageDisplayed());
        assertEquals(PropertiesProvider.USER_LOCKED_MSG, loginPage.getErrorMessageText());
    }
}
