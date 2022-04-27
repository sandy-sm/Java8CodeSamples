package sm.sandy.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Consumer1 {

    public static <T> void consumeList(List<T> list, Consumer<T> consumer) {
        for (T i: list) {
            consumer.accept(i);
        }
    }

    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("hello", "world", "2020");
        consumeList(stringList, s -> System.out.println("sending email to "+ s));

        Consumer<Integer> consumer = i -> System.out.println("sending email to "+ i);
        consumer.accept(2);
    }
}
