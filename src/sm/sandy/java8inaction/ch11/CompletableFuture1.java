package sm.sandy.java8inaction.ch11;

import sm.sandy.java8inaction.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class CompletableFuture1 {

    static double longTask(double x) {
        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return x*x;
    }

    static Future<Double> longTaskAsync(double x) {
        return CompletableFuture.supplyAsync(() -> longTask(x));
    }

    public static void main(String[] args) {
        List<Double> list= Arrays.asList(10.1, 22.11, 33.3, 11.1,
                11.2, 33.1, 11.3, 33.1,
                33.1,22.3, 11.4, 18.12,
                81.1, 91.1, 921.7);

        Utils.getInstance().start();
        list.stream()
                .map(x -> longTask(x))
                .collect(Collectors.toList());
        Utils.getInstance().stop(); //30101 millis, 2014 for each task sequentially

        System.out.println("Now parallel streaming");
        Utils.getInstance().start();
        list.parallelStream()
                .map(x -> longTask(x))
                .collect(Collectors.toList());
        Utils.getInstance().stop();  //4016 millis

        System.out.println("paralel task execution with completable future & cached thread pool");
        Executor executor = Executors.newCachedThreadPool();

        Utils.getInstance().start();
        List<CompletableFuture<Price>> cList = list.stream()
                        .map(a -> CompletableFuture.supplyAsync(() -> longTask(a), executor))
                        .map(c -> c.thenApply(Price::get)).collect(Collectors.toList());

        CompletableFuture[] futures = cList.stream().map(f -> f.thenAccept(c  -> System.out.println("completed")))
                .toArray(size -> new CompletableFuture[size]);

        CompletableFuture.allOf(futures).join();
        Utils.getInstance().stop();  // 2014 millis! Awesome!


    }
}
