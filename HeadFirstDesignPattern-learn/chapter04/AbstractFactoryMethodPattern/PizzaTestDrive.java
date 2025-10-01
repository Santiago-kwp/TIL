package chapter04.AbstractFactoryMethodPattern;

public class PizzaTestDrive {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYStylePizzaStore();
        PizzaStore chicagoStore = new ChicagoStylePizzaStore();

        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.println("ethan ordered " + pizza.getName());
    }
}

/* 실행 결과
Preparing NY Style Cheese Pizza
Dough: Thin Crust Dough
Sauce: Marinara Sauce
 Sliced resinous cheese
Baking NY Style Cheese Pizza
Cutting NY Style Cheese Pizza
Boxing NY Style Cheese Pizza
ethan ordered NY Style Cheese Pizza

 */
