package sm.sandy.java8inaction.ch11;

public class Price {

    private double d;

    public static Price get(double o) {
        Price p = new Price();
        p.d = o;
        return p;
    }
}
