package swaglabs.entity;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    String name;
    String description;
    String price;

    public double getPriceAsDouble() {
        return Double.parseDouble(price.replace("$", ""));
    }
}
