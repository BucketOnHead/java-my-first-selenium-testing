package swaglabs.test.main;

import com.google.common.collect.Comparators;
import config.ConfigProvider;
import core.BaseTest;
import org.junit.jupiter.api.Test;
import swaglabs.entity.Item;
import swaglabs.page.LoginPage;
import swaglabs.test.main.script.SortProductTest;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageTest extends BaseTest
        implements SortProductTest {

    @Test
    @Override
    public void sortNameAtoZ() {
        var username = ConfigProvider.STANDARD_USER_USERNAME;
        var password = ConfigProvider.STANDARD_USER_PASSWORD;

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
        var username = ConfigProvider.STANDARD_USER_USERNAME;
        var password = ConfigProvider.STANDARD_USER_PASSWORD;

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
        var username = ConfigProvider.STANDARD_USER_USERNAME;
        var password = ConfigProvider.STANDARD_USER_PASSWORD;

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
        var username = ConfigProvider.STANDARD_USER_USERNAME;
        var password = ConfigProvider.STANDARD_USER_PASSWORD;

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
}