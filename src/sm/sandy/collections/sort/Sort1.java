package sm.sandy.collections.sort;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Sort1 {

    public static void main(String[] args) {
        List<Cycle> cycleList = Arrays.asList(
                Cycle.of(21, "grey", "mtb"),
                Cycle.of(27, "white", "hybrid"),
                Cycle.of(23, "red", "road"),
                Cycle.of(21, "red", "hybrid"),
                Cycle.of(23, "white", "mtb"),
                Cycle.of(27, "grey", "road"),
                Cycle.of(21, "blue", "mtb"),
                Cycle.of(23, "black", "hybrid"),
                Cycle.of(21, "white", "road")
        );

        //get grey colored cycles
        List<Cycle> greyColored = cycleList.stream()
                .filter(c -> c.color.equalsIgnoreCase("grey"))
                .collect(Collectors.toList());
        assert greyColored.size() == 2;
        System.out.println("Grey colored cycles: ");
        printList(greyColored);

        //get average no of gears
        OptionalDouble avgGears = cycleList
                .stream()
                .map(cycle -> cycle.noOfGears)
                .mapToInt(i -> i)
                .average();

        System.out.println("average: ");
        avgGears.ifPresent(System.out::println);

        //get most used cycle type
        Map.Entry entry = cycleList
                .stream()
                .map(c -> c.type.toLowerCase(Locale.ROOT))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get();
        System.out.println("cycle type "+ entry.getKey() + " is most used. Count - "+ entry.getValue());

        cycleList.sort((Cycle c1, Cycle c2) -> c1.noOfGears <= c2.noOfGears ? -1 : 1);
        cycleList.sort((Cycle c1, Cycle c2) -> c1.color.compareTo(c2.color));
        printList(cycleList);

        cycleList.sort(Comparator.comparing(Cycle::getType).reversed());
        System.out.println("type asc");

        Hashtable<String, String> ht = new Hashtable<>();

        String s1 = "abc";
        System.out.println(s1.hashCode());

        printList(cycleList);

        Thread td = new Thread(() -> System.out.println("hello world"));
        // run method uses same thread as calling method
        td.run();

        //start uses currentThread and internally calls run method in separate thread.
        td.start();
    }

    static void printList(List<Cycle> cycleList) {
        cycleList.forEach(System.out::println);
    }

    static class Cycle {
        int noOfGears;
        String color;
        String type;

        Cycle(int noOfGears, String color, String type) {
            this.noOfGears = noOfGears;
            this.color = color;
            this.type = type;
        }

        static Cycle of(int noOfGears, String color, String type) {
            return new Cycle(noOfGears, color, type);
        }

        public int getNoOfGears() {
            return noOfGears;
        }

        public String getColor() {
            return color;
        }

        public String getType() {
            return type;
        }

        @Override
        public String toString() {
            return "Cycle{" +
                    "noOfGears=" + noOfGears +
                    ", color='" + color + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }
}
