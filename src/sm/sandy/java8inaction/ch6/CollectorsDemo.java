package sm.sandy.java8inaction.ch6;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

enum CalorificValue { DIET, NORMAL, FAT};

public class CollectorsDemo {

    public static void main(String[] args) {
        List<Dish> list =
                Arrays.asList(new Dish(800, "sweet dish", "veg"),
                        new Dish(500, "chapati", "veg"),
                        new Dish(1000, "dal rice", "veg"),
                        new Dish(1300, "fish", "non veg"),
                        new Dish(1200, "chicken curry", "non veg"));

        Optional<Dish> highCalorieDish = list.stream()
                .collect(maxBy(Comparator.comparing(Dish::getCalories)));

        System.out.println("Highes calorie dish is : "+ highCalorieDish.orElseGet(null));

        int totalCalories =
                list.stream().collect(summingInt(Dish::getCalories));
        System.out.println("total calories : "+ totalCalories);

        //reducing function; often reducing function is a generalized version of special functions like summingInt
        int totalCalories1 = list.stream().collect(reducing(0, Dish::getCalories, (a, b) -> a+b)); //3-argument
        System.out.println("total calories : "+ totalCalories1);

        Optional<Integer> totalCalories2 = list.stream().map(Dish::getCalories).collect(reducing((d1, d2) -> d1 + d2));
        totalCalories2.ifPresent(System.out::println);


        DoubleSummaryStatistics statistics =
                list.stream().collect(summarizingDouble(Dish::getCalories));
        System.out.println("statistics of calories: "+ statistics);

        String shortName = list.stream().map(Dish::getName)
                .collect(joining(","));
        System.out.println("dishes are: "+ shortName);


        //grouping by dish type
        Map<String, List<Dish>> typeDishes = list.stream()
                .collect(groupingBy(Dish::getType));
        System.out.println(typeDishes);

        //multi-level grouping
        Map<String, Map<CalorificValue, List<Dish>>> getByTypeAndThenByCalorificValue =
                list.stream()
                        .collect(groupingBy(Dish::getType, groupingBy(
                                dish -> getCalorificValue(dish.getCalories())
                        )));

        System.out.println(getByTypeAndThenByCalorificValue);

        //groupingBy dish type and count
        Map<String, Long> countByDishType = list.stream().collect(groupingBy(Dish::getType, counting()));
        System.out.println(countByDishType);

        //highest calorie dish in dish-type category
        Map<String, Optional<Dish>> highestCalorieDish = list.stream()
                .collect(groupingBy(Dish::getType, maxBy(Comparator.comparing(Dish::getCalories))));
        System.out.println(highestCalorieDish);

        //total calories for each dish type
        Map<String, Integer> caloriesPerDishType = list.stream()
                .collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        System.out.println("calories per dish type "+ caloriesPerDishType);

        //mapping
        Map<String, Set<CalorificValue>> caloricValuesInEachDishType =
                list.stream()
                        .collect(groupingBy(Dish::getType, mapping(dish -> getCalorificValue(dish.getCalories()),
                                toSet())));
        System.out.println("caloric categories per dish type: " + caloricValuesInEachDishType);

        //mapping with HashSet type
        Map<String, HashSet<CalorificValue>> caloricValues =
                list.stream()
                        .collect(groupingBy(Dish::getType, mapping(dish -> getCalorificValue(dish.getCalories()),
                                Collectors.toCollection(HashSet::new))));
        System.out.println("caloric ctegories per dish type: "+ caloricValues);


        //partitionBy
        Map<Boolean, List<Dish>> dishTypePartition =
                list.stream().collect(partitioningBy(dish -> dish.getType()=="veg", toList()));
        //veg=true, nonveg=false
        System.out.println(dishTypePartition);



    }

    private static CalorificValue getCalorificValue(int calories) {
        if (calories < 400)
            return CalorificValue.DIET;
        if (calories >= 400 && calories < 900)
            return CalorificValue.NORMAL;
        else
            return CalorificValue.FAT;
    }
}
