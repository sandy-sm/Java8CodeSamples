package sm.sandy.java8inaction.ch6;

public class Dish {

    private int calories;
    private String name;
    private String type;

    Dish(int calories, String name, String type) {
        this.calories = calories;
        this.name = name;
        this.type = type;
    }

    public int getCalories() {
        return calories;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "calories=" + calories +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
