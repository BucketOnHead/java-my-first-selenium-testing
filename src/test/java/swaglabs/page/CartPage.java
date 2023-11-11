package swaglabs.page;

import config.ConfigProvider;
import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import swaglabs.entity.CartItem;

import java.util.List;

// page_url = https://www.saucedemo.com/cart.html
public class CartPage extends BasePage {

    @FindBy(id = "cart_contents_container")
    private WebElement productContainer;

    public CartPage() {
        driver.get(ConfigProvider.CART_URL);
        PageFactory.initElements(driver, this);
    }

    public List<CartItem> getCartItems() {
        return productContainer.findElements(By.className("cart_item"))
                .stream()
                .map(CartPage::parseCartItem)
                .toList();
    }

    private static CartItem parseCartItem(WebElement cartItemElement) {
        return parseCartItem(cartItemElement, null);
    }

    private static CartItem parseCartItem(WebElement cartItemElement, CartItem cartItem) {
        var name = cartItemElement.findElement(By.className("inventory_item_name")).getText();
        var price = cartItemElement.findElement(By.className("inventory_item_price")).getText();
        var description = cartItemElement.findElement(By.className("inventory_item_desc")).getText();

        if (cartItem == null) {
            cartItem = new CartItem();
        }

        cartItem.setName(name);
        cartItem.setPrice(price);
        cartItem.setDescription(description);

        return cartItem;
    }
}
