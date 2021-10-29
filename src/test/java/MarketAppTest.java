import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class MarketAppTest {

    Logger log = Logger.getLogger(MarketApp.class.getName());

    @Test
    void priceBasketSpecialExample() throws Exception {

        try{
        ByteArrayInputStream in = new ByteArrayInputStream("PriceBasket Apples Milk Bread".getBytes());
        System.setIn(in);
        MarketApp.main(new String[] {""});
        } catch(Exception e) {
            System.out.println("Thank you for testing");
        }

    }

    @Test
    void priceBasketNoSpecialExample() throws Exception {

        try{
            ByteArrayInputStream in = new ByteArrayInputStream("PriceBasket Soup Milk Bread".getBytes());
            System.setIn(in);
            MarketApp.main(new String[] {""});
        } catch(Exception e) {
            System.out.println("Thank you for testing");
        }

    }

    @Test
    void showItems() {
        try{
            ByteArrayInputStream in = new ByteArrayInputStream("list".getBytes());
            System.setIn(in);
            MarketApp.main(new String[] {""});
        } catch(Exception e) {
            System.out.println("Thank you for testing");
        }

    }
}