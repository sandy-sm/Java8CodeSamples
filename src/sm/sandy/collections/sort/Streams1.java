package sm.sandy.collections.sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams1 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("abc", "def", "ghi");

        list.stream().forEach(System.out::println);
        list.stream().forEach(System.out::println);

        Stream<String> stringStream = list.stream();

        try {
            stringStream.forEach(System.out::println);
            stringStream.forEach(System.out::println);
        } catch (IllegalStateException illegalStateException) {
            System.out.println(illegalStateException);
        }

        String[] arr1 = new String[] {"abc", "def", "ghi"};
        Stream<String> stream1 = Arrays.stream(arr1);

        List<String[]> str = stream1.map(s -> s.split(""))
                .distinct().collect(Collectors.toList());
        System.out.println(str);

        List<String> str2 = Arrays.asList(arr1).stream()
                .map(s -> s.split(""))    //converts each word into array of individual letters
                .flatMap(f -> Arrays.stream(f))  //convert each stream into a single stream
                .distinct()
                .collect(Collectors.toList());

        System.out.println(str2);

        list.stream()
                .findAny()
                .ifPresent(System.out::println);
    }
}
