package chapter04.SimpleFactoryWay;

public class PizzaStore {
    // pizzaStore에 SimplePizzaFactory의 레퍼런스를 저장합니다.
    SimplePizzaFactory factory;

    // pizza의 생성자에 팩토리 객체가 전달됩니다.
    public PizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }

    Pizza orderPizza(String type) {

        Pizza pizza;
        pizza = factory.createPizza(type);

        /*
        이 부분은 바뀌지 않음.
         */
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
