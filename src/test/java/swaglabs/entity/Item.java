package swaglabs.entity;

import lombok.Builder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Builder
public record Item(String name, Double price) {

    public static Item parse(WebElement item) {
        var name = item.findElement(By.className("inventory_item_name"))
                .getText();
        var price = Double.parseDouble(item.findElement(By.className("inventory_item_price"))
                .getText()
                .replace("$", ""));

        return Item.builder()
                .name(name)
                .price(price)
                .build();
    }
}
