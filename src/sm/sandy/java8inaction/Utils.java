package sm.sandy.java8inaction;

public class Utils {

    private static long ts;

    private Utils() {}

    public static Utils getInstance() {
        return new Utils();
    }

    public static long elapsed(long startMillis) {
        return System.currentTimeMillis() - startMillis;
    }

    public void start() {
        ts = System.currentTimeMillis();
    }

    public void stop() {
        System.out.println("Elapsed time is "+ elapsed(ts));
    }

}
