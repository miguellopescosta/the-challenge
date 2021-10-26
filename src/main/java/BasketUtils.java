import lombok.Data;

@Data
public class BasketUtils {

    public BasketUtils() {
    }

    public static void showItems() {
        System.out.println();
        System.out.println("Soup – 65p per tin");
        System.out.println("Bread – 80p per loaf");
        System.out.println("Milk £1.30 per bottle");
        System.out.println("Apples - £1.00 per bag");
        System.out.println();
        System.out.println("Current special offers are:");
        System.out.println("Apples have 10% off their normal price this week");
        System.out.println("Buy 2 tins of soup and get a loaf of bread for half price");
    }

}
