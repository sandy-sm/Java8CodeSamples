package sm.sandy.java8inaction.ch12;

import java.time.Instant;

public class Instant1 {

    public static void main(String[] args) {
        Instant instant = Instant.ofEpochSecond(1);
        System.out.println(instant.toString());  //1970-01-01T00:00:01Z

        instant = Instant.ofEpochSecond(-1);
        System.out.println(instant.toString()); //1969-12-31T23:59:59Z


    }
}
