package swaglabs.test.main;

import com.google.common.collect.Comparators;
import config.ConfigProvider;
import core.BaseTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import swaglabs.entity.Item;
import swaglabs.page.LoginPage;
import swaglabs.test.main.script.LeftMenuTest;
import swaglabs.test.main.script.SortProductTest;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageTest extends BaseTest
        implements SortProductTest, LeftMenuTest {

    private static final String username = ConfigProvider.STANDARD_USER_USERNAME;
    private static final String password = ConfigProvider.STANDARD_USER_PASSWORD;

    @Test
    @Override
    public void sortNameAtoZ() {
        List<Item> products = new LoginPage()
                .auth(username, password)
                .selectProductSortByValue("az")
                .getProducts()
                .stream()
                .map(Item::parse)
                .toList();

        boolean sorted = Comparators.isInOrder(products, Comparator.comparing(Item::name));
        assertTrue(sorted, "The products should be sorted by name in the order A to Z");
    }

    @Test
    @Override
    public void sortNameZtoA() {
        List<Item> products = new LoginPage()
                .auth(username, password)
                .selectProductSortByValue("za")
                .getProducts()
                .stream()
                .map(Item::parse)
                .toList();

        boolean sorted = Comparators.isInOrder(products, Comparator.comparing(Item::name).reversed());
        assertTrue(sorted, "The products should be sorted by name in the order Z to A");
    }

    @Test
    @Override
    public void sortPriceLowToHigh() {
        List<Item> products = new LoginPage()
                .auth(username, password)
                .selectProductSortByValue("lohi")
                .getProducts()
                .stream()
                .map(Item::parse)
                .toList();

        boolean sorted = Comparators.isInOrder(products, Comparator.comparing(Item::price));
        assertTrue(sorted, "The products should be sorted by price in the order low to high");
    }

    @Test
    @Override
    public void sortPriceHighToLow() {
        List<Item> products = new LoginPage()
                .auth(username, password)
                .selectProductSortByValue("hilo")
                .getProducts()
                .stream()
                .map(Item::parse)
                .toList();

        boolean sorted = Comparators.isInOrder(products, Comparator.comparing(Item::price).reversed());
        assertTrue(sorted, "The products should be sorted by price in the order high to low");
    }

    @Disabled("На сайте часто бывают вечные ожидания, не знаю как исправить")
    @Test
    @Override
    public void leftMenuAboutClick() {
        new LoginPage().auth(username, password).clickAboutButton();

        assertEquals(ConfigProvider.ABOUT_URL, driver.getCurrentUrl());
    }

    @Test
    @Override
    public void leftMenuLogoutClick() {
        new LoginPage().auth(username, password).clickLogoutButton();

        assertEquals(ConfigProvider.LOGIN_URL, driver.getCurrentUrl());
    }

    @Disabled("Необходимый функционал ещё не реализован")
    @Test
    @Override
    public void leftMenuResetAppStateClick() {
        // TODO: реализовать после тестирования добавления товаров в корзину
    }
}
