package chapter04.AbstractFactoryPattern;

import java.util.ArrayList;
import java.util.List;

public abstract class Pizza {
    String name;

    // 피자마다 준비 과정에서 사용하는 원재료들이 있음.
    Dough dough;
    Sauce sauce;
    Veggies veggies;
    Cheese cheese;
    Pepperoni pepperoni;
    Clams clam;

    /*
    이제 prepare 메소드를 추상 메소드로 만들음. 이 부분에서 피자를 만드는 데 필요한
    재료들을 가져옴. 물론 모든 원재료는 원재료 팩토리에서 가져옴
     */
    abstract void prepare();

//    void prepare() {
//        System.out.println("Preparing " + name);
//        System.out.println("Dough: " + dough
//        + "\nSauce: " + sauce);
//        for (String topping : toppings) {
//            System.out.println(" " + topping);
//        }
//    }

    // 다른 메소드들은 그대로

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

    public void setName(String name) {
        this.name = name;
    }
}
