package sm.sandy.java8inaction.ch11;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Future2 {

    public static Future<Double> getPriceAsync(String productCode) {
        CompletableFuture<Double> completableFuture = new CompletableFuture<>();
        new Thread(() -> {
            double price = new FindPrice().getPrice(productCode);
            completableFuture.complete(price);
        }).start();

        return completableFuture;
    }

    public static void main(String[] args) {
        System.out.println("get price async started");
        getPriceAsync("abc123");
        System.out.println("get price async ended");
    }
}
