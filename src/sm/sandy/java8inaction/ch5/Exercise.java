package sm.sandy.java8inaction.ch5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Exercise {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //find all transactions in the year 2011 and sort them by value (small to high)
        List<Transaction> transactions1 = transactions
                .stream()
                .filter(t -> t.getYear() == 2011)
                //.sorted((t1, t2) -> t1.getValue() < t2.getValue() ? -1 : 1)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println("(1) -> " + transactions1);

        //what are all the unique cities where the traders work
        List<String> uniqueCities = transactions.stream().map(transaction -> transaction.getTrader())
                .map(t -> t.getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("(2) Unique cities -> "+ uniqueCities);

        //find all traders from Cambridge and sort them by name
        List<Trader> cambridgeTraders = transactions.stream()
                .map(transaction -> transaction.getTrader())
                .filter(trader -> trader.getCity().equalsIgnoreCase("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        System.out.println("(3) "+ cambridgeTraders);

        //return a string of all traders name sorted alphabetically
        String traders = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());
                //.reduce("", (a, b) -> a + " " + b);  creates string at every iteration, not efficient
        System.out.println("(4) Traders are "+ traders);

        //are any traders based in milan
        boolean anyTradersInMilan = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equalsIgnoreCase("milan"));

        System.out.println("(5) Are any trader in milan "+ anyTradersInMilan);

        //print all transaction values from the traders in cambridge
        System.out.println("(6) Cambridge transactions value " );
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equalsIgnoreCase("cambridge"))
                .map(Transaction::getValue)
                        .forEach(System.out::println);

        //what is the highest value of all transactions
        int highestValueOfTransaction = transactions.stream()
                .map(t -> t.getValue())
                .reduce(Integer.MIN_VALUE, Integer::max);
        System.out.println("(7) highest value of transaction is "+ highestValueOfTransaction);
        Optional<Transaction> high =
                transactions.stream().max(Comparator.comparing(Transaction::getValue));
        System.out.println("(7) highest value of transaction is "+ (high.isPresent() ? high.get().getValue() : "not found"));



        //find the transaction with smallest value
        int smallestValueTransaction = transactions.stream()
                .map(t -> t.getValue())
                .reduce(Integer.MAX_VALUE, Integer::min);
        System.out.println("(8) smallest value of transation is "+ smallestValueTransaction);


        int smallest = transactions.stream()
                .mapToInt(t -> t.getValue())
                .min().getAsInt();
        System.out.println("(8) smallest value by specialized primitive: "+ smallest);
    }

}
