package sm.sandy.java8inaction.ch11;

import java.util.Random;

public class FindPrice {

    public double getPrice(String productCode) {
        return calculatePrice(productCode);
    }

    public double calculatePrice(String productCode)  {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Random().nextDouble();
    }
}
