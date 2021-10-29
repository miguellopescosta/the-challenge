package basket;

import lombok.Data;

@Data
public abstract class Item<T> {

    final Class<T> typeParameterClass;

    private long quantity;

    public Item(Class<T> typeParameterClass, long quantity) {
        this.typeParameterClass = typeParameterClass;
        this.quantity = quantity;
    }

}
