import basket.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StoreUtils {

    PriceBasket<Item> priceBasket = new PriceBasket<>();
    float soupAndBreadDiscount;
    float appleDiscount;

    List<String> items;

    public void showItems() {
        System.out.println();
        System.out.println("basket.Soup – 65p per tin");
        System.out.println("basket.Bread – 80p per loaf");
        System.out.println("basket.Milk £1.30 per bottle");
        System.out.println("basket.Apples - £1.00 per bag");
        System.out.println();
        System.out.println("Current special offers are:");
        System.out.println("basket.Apples have 10% off their normal price this week");
        System.out.println("Buy 2 tins of soup and get a loaf of bread for half price");
    }

    private void addItem() {

        clear();

        if (items.stream().anyMatch(o -> o.equals(Bread.class.getSimpleName()))) {
            priceBasket.addBasketItem(new Bread(countItem(Bread.class.getSimpleName())));
        }
        if (items.stream().anyMatch(o -> o.equals(Soup.class.getSimpleName()))) {
            priceBasket.addBasketItem(new Soup(countItem(Soup.class.getSimpleName())));
        }
        if (items.stream().anyMatch(o -> o.equals(Milk.class.getSimpleName()))) {
            priceBasket.addBasketItem(new Milk(countItem(Milk.class.getSimpleName())));
        }
        if (items.stream().anyMatch(o -> o.equals(Apples.class.getSimpleName()))) {
            priceBasket.addBasketItem(new Apples(countItem(Apples.class.getSimpleName())));
        }

    }

    private long countItem(String item) {
        return items.stream().filter(o -> o.equals(item)).count();
    }

    public float getSubTotal() {

        return getValue(Soup.class.getSimpleName()) * ItemEnum.SOUP.value
                + getValue(Bread.class.getSimpleName()) * ItemEnum.BREAD.value
                + getValue(Milk.class.getSimpleName()) * ItemEnum.MILK.value
                + getValue(Apples.class.getSimpleName()) * ItemEnum.APPLES.value;
    }

    private float getValue(String className) {
        try {
            return priceBasket.getItem(className).getQuantity();
        } catch (Exception e) {
            return 0.00f;
        }
    }

    public float getTotal() {
        setSoupAndBreadDiscount(getValue(Soup.class.getSimpleName()),
                getValue(Bread.class.getSimpleName()));

        setApplesDiscount(getValue(Apples.class.getSimpleName()));

        return getValue(Soup.class.getSimpleName()) * ItemEnum.SOUP.value
                + getValue(Bread.class.getSimpleName()) * ItemEnum.BREAD.value
                + getValue(Milk.class.getSimpleName()) * ItemEnum.MILK.value
                + getValue(Apples.class.getSimpleName()) * ItemEnum.APPLES.value * .9f
                + soupAndBreadDiscount;
    }

    public void setSoupAndBreadDiscount(float soups, float breads) {

        if (soups > 1 && breads > 0) {
            do {
                soups -= 2;
                breads -= 1;
                soupAndBreadDiscount += (ItemEnum.BREAD.value + 2 * ItemEnum.SOUP.value) / 2;
            } while (soups > 1 && breads > 0);
        }

        priceBasket.updateItem(new Soup((long) soups));
        priceBasket.updateItem(new Bread((long) breads));
    }

    public void setApplesDiscount(float apples) {
        appleDiscount = apples * .1f;
    }

    public void setItems(List<String> list) {
        this.items = list;
        addItem();
    }

    public void clear(){
        soupAndBreadDiscount = 0.00f;
        appleDiscount = 0.00f;
        priceBasket.getPriceBasket().clear();
    }

}
