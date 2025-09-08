package chapter04.AbstractFactoryPattern;


public class NYPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza = null;
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();

        if (type.equals("cheese")) {
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("뉴욕 스타일 치즈 피자");

        } else if (type.equals("pepperoni")) {
            pizza = new PepperoniPizza(ingredientFactory);
            pizza.setName("뉴욕 스타일 페퍼로니 피자");
        } else if (type.equals("veggies")) {
            pizza = new VeggiePizza(ingredientFactory);
            pizza.setName("뉴욕 스타일 야채 피자");
        } else if (type.equals("clam")) {
            pizza = new ClamPizza(ingredientFactory);
            pizza.setName("뉴욕 스타일 조개 피자");
        }
        return pizza;
    }
}
