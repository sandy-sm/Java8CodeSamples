package sm.sandy.java8inaction.ch11;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ExecutorThreadDemo {

    public static void main(String[] args) {
        Executor executor = Executors.newFixedThreadPool(10, r -> {
            Thread t = new Thread();
            t.setDaemon(true);  //when set to true, it does not prevent termination of program
            return t;
        });

    }
}
