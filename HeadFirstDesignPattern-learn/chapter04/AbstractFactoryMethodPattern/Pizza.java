package chapter04.AbstractFactoryMethodPattern;

import java.util.ArrayList;
import java.util.List;

public abstract class Pizza {
    String name;
    String dough;
    String sauce;
    List<String> toppings = new ArrayList<>();

    void prepare() {
        System.out.println("Preparing " + name);
        System.out.println("Dough: " + dough
        + "\nSauce: " + sauce);
        for (String topping : toppings) {
            System.out.println(" " + topping);
        }
    }

    public void bake() {
        System.out.println("Baking " + name);
    }

    public void cut() {
        System.out.println("Cutting " + name);
    }

    public void box() {
        System.out.println("Boxing " + name);
    }
    public String getName() {
        return name;
    }
}
