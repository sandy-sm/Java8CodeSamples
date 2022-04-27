package sm.sandy.java8inaction.ch7;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStream1 {

    public static void main(String[] args) {
        LongStream stream = LongStream.rangeClosed(1, 100_000l);

        long start = System.currentTimeMillis();
        long sum1 = stream.sum();
        long elapsed = System.currentTimeMillis() - start;

        System.out.println("sequential stream took "+ elapsed + " ms");
        stream = LongStream.rangeClosed(1, 100_000l);
        //sequential stream took ~30-40 ms

        start = System.currentTimeMillis();
        long sum2 = stream.parallel()
                .sum();
        elapsed = System.currentTimeMillis() - start;

        System.out.println("parallel stream took "+ elapsed + " ms");
        //parallel stream took ~2-4 ms!



    }
}
