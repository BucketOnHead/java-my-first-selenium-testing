package utils.holder;

import lombok.*;

@Data
public abstract class Holder<T> {
    private T value;
}
