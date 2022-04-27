package sm.sandy.lambda;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Lambda1 {

    public static void main(String[] args) {
        FindValue findValue = (text, search) -> text.contains(search) ? search : null;
        String find = findValue.findValue("hello world", "hello");
        System.out.println(find);

        FindDuplicates findDuplicates = list -> {
            Set<String> duplicates = new HashSet<>();
            return list.stream()
                    // set.add(e) returns false if already exists
                    .filter(l -> !duplicates.add(l))
                    //collect duplicate elements
                    .collect(Collectors.toList());
        };

        System.out.println("Duplicates in list are "+ findDuplicates.duplicates(
                Arrays.asList("a", "a", "c", "b")));
        FindOccurances f = (list, s) ->
            (int) list.parallelStream()
                    .filter(l -> s.equalsIgnoreCase(l))
                    .count();

        System.out.println("Count of a: " + f.count(Arrays.asList("a", "a", "b", "c", "d"), "a"));
    }
}

@FunctionalInterface
interface FindValue {
    String findValue(String text, String search);
}

@FunctionalInterface
interface FindDuplicates {
    List<String> duplicates(List<String> list);
}

@FunctionalInterface
interface  FindOccurances {
    int count(List<String> list, String s);
}
