package chapter04.AbstractFactoryPattern;


public abstract class PizzaStore {

    public Pizza orderPizza(String type) {
        Pizza pizza;

        // 팩토리 객체가 아닌 pizzaStore에 있는 createPizza를 호출함.
        pizza = createPizza(type);

        /*
        이 부분은 바뀌지 않음.
         */
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }
    // 팩토리 객체 대신 이 메소드를 사용함
    // 이제 팩토리 메소드가 pizzaStore의 추상 메소드로 바뀜
    abstract Pizza createPizza(String type);

}
