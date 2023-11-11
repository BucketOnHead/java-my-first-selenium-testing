package swaglabs.test.main;

import com.google.common.collect.Comparators;
import config.ConfigProvider;
import core.BaseTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import swaglabs.entity.Item;
import swaglabs.page.LoginPage;
import swaglabs.test.main.script.CartTest;
import swaglabs.test.main.script.LeftMenuTest;
import swaglabs.test.main.script.ProductSortTest;
import utils.SimpleHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MainPageTest extends BaseTest
        implements ProductSortTest, LeftMenuTest, CartTest {

    private static final String username = ConfigProvider.STANDARD_USER_USERNAME;
    private static final String password = ConfigProvider.STANDARD_USER_PASSWORD;

    @Test
    @Override
    public void sortNameAtoZ() {
        List<Item> products = new LoginPage()
                .auth(username, password)
                .selectProductSortByValue("az")
                .getProducts();

        boolean sorted = Comparators.isInOrder(products, Comparator.comparing(Item::getName));
        assertTrue(sorted, "The products should be sorted by name in the order A to Z");
    }

    @Test
    @Override
    public void sortNameZtoA() {
        List<Item> products = new LoginPage()
                .auth(username, password)
                .selectProductSortByValue("za")
                .getProducts();

        boolean sorted = Comparators.isInOrder(products, Comparator.comparing(Item::getName).reversed());
        assertTrue(sorted, "The products should be sorted by name in the order Z to A");
    }

    @Test
    @Override
    public void sortPriceLowToHigh() {
        List<Item> products = new LoginPage()
                .auth(username, password)
                .selectProductSortByValue("lohi")
                .getProducts();

        boolean sorted = Comparators.isInOrder(products, Comparator.comparing(Item::getPriceAsDouble));
        assertTrue(sorted, "The products should be sorted by price in the order low to high");
    }

    @Test
    @Override
    public void sortPriceHighToLow() {
        List<Item> products = new LoginPage()
                .auth(username, password)
                .selectProductSortByValue("hilo")
                .getProducts();

        boolean sorted = Comparators.isInOrder(products, Comparator.comparing(Item::getPriceAsDouble).reversed());
        assertTrue(sorted, "The products should be sorted by price in the order high to low");
    }

    @Disabled("На сайте часто бывают вечные ожидания, не знаю как исправить")
    @Test
    @Override
    public void leftMenuAboutClick() {
        new LoginPage()
                .auth(username, password)
                .clickAboutButton();

        assertEquals(ConfigProvider.ABOUT_URL, driver.getCurrentUrl());
    }

    @Test
    @Override
    public void leftMenuLogoutClick() {
        new LoginPage()
                .auth(username, password)
                .clickLogoutButton();

        assertEquals(ConfigProvider.LOGIN_URL, driver.getCurrentUrl());
    }

    @Disabled("проблема с ожиданием, не знаю как сообщить, что ожидать не нужно")
    @Test
    @Override
    public void leftMenuResetAppStateClick() {
        var cartItems = new LoginPage()
                .auth(username, password)
                .addProductByIdx(0)
                .clickResetAppStateButton()
                .clickCartButton()
                .getCartItems();

        assertTrue(cartItems.isEmpty());
    }

    @Test
    @Override
    public void clickCart() {
        new LoginPage()
                .auth(username, password)
                .clickCartButton();

        assertEquals(ConfigProvider.CART_URL, driver.getCurrentUrl());
    }

    @Test
    @Override
    public void addProduct() {
        final String itemName = "Sauce Labs Backpack";
        final String itemDescription = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
        final String price = "$29.99";

        var cartBadgeValueHolder = new SimpleHolder<String>();
        var item = new Item();
        var cartItems = new LoginPage()
                .auth(username, password)
                .addProductByName(itemName)
                .getProductByName(itemName, item)
                .getCartBadgeValue(cartBadgeValueHolder)
                .clickCartButton()
                .getCartItems();

        var cartItem = cartItems.get(0);
        var cartBadgeValue = cartBadgeValueHolder.getValue();

        assertDoesNotThrow(() -> Integer.parseInt(cartBadgeValue), "item quantity badge displays an incorrect value");
        assertEquals(1, Integer.parseInt(cartBadgeValue), "item quantity badge displays an incorrect value");
        assertEquals(1, cartItems.size(), "There are more items in cart than there should be");
        assertEquals(item.getName(), cartItem.getName());
        assertEquals(item.getDescription(), cartItem.getDescription());
        assertEquals(item.getPrice(), cartItem.getPrice());

        assertEquals(itemName, item.getName());
        assertEquals(itemDescription, item.getDescription());
        assertEquals(price, item.getPrice());
    }

    @Override
    public void removeProduct() {
        // TODO: проблема с ожиданием, не знаю как сообщить, что ожидать не нужно
    }

    @Override
    public void removeProductFromCart() {
        // TODO: проблема с ожиданием, не знаю как сообщить, что ожидать не нужно
    }

    @Test
    @Override
    public void productInCartInAdditionOrder() {
        final int itemIdx1 = 0;
        final int itemIdx2 = 2;
        final int itemIdx3 = 5;

        List<Integer> itemIdxs = new ArrayList<>();
        itemIdxs.add(itemIdx1);
        itemIdxs.add(itemIdx2);
        itemIdxs.add(itemIdx3);
        Collections.shuffle(itemIdxs);

        var item1 = new Item();
        var item2 = new Item();
        var item3 = new Item();
        var cartItems = new LoginPage()
                .auth(username, password)
                .addProductByIdx(itemIdxs.get(0))
                .addProductByIdx(itemIdxs.get(1))
                .addProductByIdx(itemIdxs.get(2))
                .getProductByIdx(itemIdxs.get(0), item1)
                .getProductByIdx(itemIdxs.get(1), item2)
                .getProductByIdx(itemIdxs.get(2), item3)
                .clickCartButton()
                .getCartItems();
        var cartItem1 = cartItems.get(0);
        var cartItem2 = cartItems.get(1);
        var cartItem3 = cartItems.get(2);

        assertEquals(item1.getName(), cartItem1.getName());
        assertEquals(item2.getName(), cartItem2.getName());
        assertEquals(item3.getName(), cartItem3.getName());
    }

    @Test
    @Override
    public void cartSaveAfterLogout() {
        final int itemIdx = 0;

        var item = new Item();
        var cartBadgeValueHolder = new SimpleHolder<String>();
        var cartItems = new LoginPage()
                .auth(username, password)
                .addProductByIdx(itemIdx)
                .getProductByIdx(itemIdx, item)
                .clickLogoutButton()
                .auth(username, password)
                .getCartBadgeValue(cartBadgeValueHolder)
                .clickCartButton()
                .getCartItems();
        var cartBadgeValue = cartBadgeValueHolder.getValue();
        var cartItem = cartItems.get(0);

        assertDoesNotThrow(() -> Integer.parseInt(cartBadgeValue), "item quantity badge displays an incorrect value");
        assertEquals(1, Integer.parseInt(cartBadgeValue), "item quantity badge displays an incorrect value");
        assertEquals(1, cartItems.size(), "There are more items in cart than there should be");
        assertEquals(item.getName(), cartItem.getName());
        assertEquals(item.getDescription(), cartItem.getDescription());
        assertEquals(item.getPrice(), cartItem.getPrice());
    }
}
