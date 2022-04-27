package sm.sandy.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Predicate1 {

    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("abc", "", "v", "", null);

        Predicate<String> isNotBlank = (String s) -> (s != null && s.length() > 0);
        Predicate<String> isEmpty = (String::isEmpty);


        List<String> nonEmptyList = stringList
                .stream().filter(isEmpty.negate())
                .collect(Collectors.toList());


        IntPredicate intPredicate = i -> i%2==0;
        boolean b =intPredicate.test(101);
        System.out.println(b);


    }
}

interface IntPredicate {
    boolean test(int i);
}
