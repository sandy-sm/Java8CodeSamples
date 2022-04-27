package sm.sandy.java8inaction.ch11;

import java.util.concurrent.*;

public class Future1 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        //executor submit with callable interface impl.
        Future<String> future = executor.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Very long operation");
            return "callable return response";
        });

        System.out.println("calling operation");

        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
