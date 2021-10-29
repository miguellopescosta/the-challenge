import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MarketApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static float subTotal;
    private static float total;
    private static boolean init = false;
    private static final StoreUtils storeUtils = new StoreUtils();



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

                if (storeUtils.getAppleDiscount() == 0.00f && storeUtils.getSoupAndBreadDiscount() == 0.00f) {
                    System.out.println("(no offers available)");
                } else if (storeUtils.getAppleDiscount() != 0.00f && storeUtils.getSoupAndBreadDiscount() == 0.00f) {
                    System.out.printf(ItemEnum.APPLES.discount + verifyCurrencyUnit(storeUtils.getAppleDiscount()), storeUtils.getAppleDiscount());
                    System.out.println();
                } else if (storeUtils.getAppleDiscount() == 0.00f) {
                    System.out.printf(ItemEnum.SOUP.discount + verifyCurrencyUnit(storeUtils.getSoupAndBreadDiscount()), storeUtils.getSoupAndBreadDiscount());
                    System.out.println();
                } else {
                    System.out.printf(ItemEnum.APPLES.discount + verifyCurrencyUnit(storeUtils.getAppleDiscount()), storeUtils.getAppleDiscount());
                    System.out.println();
                    System.out.printf(ItemEnum.SOUP.discount + verifyCurrencyUnit(storeUtils.getSoupAndBreadDiscount()), storeUtils.getSoupAndBreadDiscount());
                    System.out.println();
                }

                System.out.printf("Total: £ %.2f", total);
                System.out.println();
                System.out.println();
                System.out.println("Write quit and press return to leave or just press return to repeat purchase!");
                init = false;
            }
            item = scanner.nextLine();

            List<String> list = Arrays.asList(item.split(" "));

            if (list.get(0).equals("PriceBasket")) checkList(list);

            if (list.contains("list")) {
                storeUtils.showItems();
                init = false;
            }

        } while (!item.equals("quit"));
    }

    private static String verifyCurrencyUnit(float value){

        if(value > 1) return " £-%.2f";

        else return " -%.2fp";

    }

    private static void checkList(List<String> list) {

        storeUtils.setItems(list);

        subTotal = storeUtils.getSubTotal();

        total = storeUtils.getTotal();

        init = true;
    }

}
