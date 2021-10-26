import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MarketApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static float subTotal = 0.00f;
    private static float total = 0.00f;
    private static float applesDiscount = 0.00f;
    private static float soupsDiscount = 0.00f;
    private static boolean init = false;
    private static int discount = 0;


    public static void main(String[] args) {
        String item;

        do {
            if (!init) {
                System.out.println();
                System.out.println("Please, insert an item or several items you would like to buy. Write \"list\" to see all items. Please, don't forget to first write PriceBasket when proceeding with the order. For example: PriceBasket Apples Milk Bread");
            } else {
                System.out.println();
                System.out.printf("Subtotal: £ %.2f", subTotal);
                System.out.println();

                if (discount == 0) {
                    System.out.println("(no offers available)");
                } else if (discount == 1) {
                    System.out.printf(ItemEnum.APPLES.discount + verifyCurrencyUnit(applesDiscount), applesDiscount);
                    System.out.println();
                } else if (discount == 2) {
                    System.out.printf(ItemEnum.SOUP.discount + verifyCurrencyUnit(soupsDiscount), soupsDiscount);
                    System.out.println();
                } else {
                    System.out.printf(ItemEnum.APPLES.discount + verifyCurrencyUnit(applesDiscount), applesDiscount);
                    System.out.println();
                    System.out.printf(ItemEnum.SOUP.discount + verifyCurrencyUnit(soupsDiscount), soupsDiscount);
                    System.out.println();
                }

                System.out.printf("Total: £ %.2f", total);
                System.out.println();
                System.out.println();
                System.out.println("Write quit and press return to leave or just press return to repeat purchase!");
                applesDiscount = 0.00f;
                soupsDiscount = 0.00f;
                discount = 0;
                init = false;
            }
            item = scanner.nextLine();

            List<String> list = Arrays.asList(item.split(" "));

            if (list.get(0).equals("PriceBasket")) checkList(list);

            if (list.contains("list")) {
                BasketUtils.showItems();
                init = false;
            }

        } while (!item.equals("quit"));
    }

    private static String verifyCurrencyUnit(float value){

        if(value > 1) return " £-%.2f";

        else return " -%.2fp";

    }

    private static void checkList(List<String> list) {

        long soups = list.stream().filter(o -> o.equals(ItemEnum.SOUP.description)).count();
        long breads = list.stream().filter(o -> o.equals(ItemEnum.BREAD.description)).count();
        long milks = list.stream().filter(o -> o.equals(ItemEnum.MILK.description)).count();
        long apples = list.stream().filter(o -> o.equals(ItemEnum.APPLES.description)).count();

        long tempSoups = soups;
        long tempBreads = breads;
        float totalDiscounted = 0.00f;

        System.out.println("Soups: " + soups + " Breads: " + breads);

        subTotal = soups * ItemEnum.SOUP.value + breads * ItemEnum.BREAD.value + milks * ItemEnum.MILK.value + apples * ItemEnum.APPLES.value;

        if (apples > 0) discount = 1;
        if (soups > 1 && breads > 0) {
            discount = 2;
            do {
                tempSoups -= 2;
                tempBreads -= 1;
                totalDiscounted += (ItemEnum.BREAD.value + 2 * ItemEnum.SOUP.value) / 2;
            } while (tempSoups > 1 && tempBreads > 0);
        }
        if (apples > 0 && soups > 1 && breads > 0) discount = 3;

        total = tempSoups * ItemEnum.SOUP.value + tempBreads * ItemEnum.BREAD.value + milks * ItemEnum.MILK.value + apples * ItemEnum.APPLES.value * .9f + totalDiscounted;

        soupsDiscount = totalDiscounted;
        applesDiscount = apples * ItemEnum.APPLES.value * .1f;

        init = true;
    }

}
