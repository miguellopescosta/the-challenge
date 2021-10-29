import basket.Item;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
@AllArgsConstructor
public class PriceBasket<T extends Item> {

    private Map<String, T> priceBasket = new HashMap<>();

    public PriceBasket() {

    }

    public void addBasketItem(T item) {
       priceBasket.put(item.getTypeParameterClass().getSimpleName(), item);
    }

    public T getItem(String item){
        return priceBasket.get(item);
    }

    public void updateItem(T item){
        priceBasket.put(item.getTypeParameterClass().getSimpleName(), item);
    }

}
