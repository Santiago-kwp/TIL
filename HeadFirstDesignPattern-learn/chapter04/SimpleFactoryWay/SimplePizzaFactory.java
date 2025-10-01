package chapter04.SimpleFactoryWay;

public class SimplePizzaFactory {

    // 클라이언트가 새로운 객체 인스턴스를 만들 때 호출하는 메소드
    public Pizza createPizza(String type) {
        Pizza pizza = null;
        /*
         피자 종류가 바뀔 때마다 코드를 계속 고쳐야 함 -> orderPizza() 메소드에서 뽑아낸 코드
         */
        if (type.equals("cheese")) {
            pizza = new CheesePizza();
        }
        else if (type.equals("pepperoni")) {
            pizza = new PepperoniPizza();
        }
        else if (type.equals("clam")) {
            pizza = new ClamPizza();
        }
        return pizza;
    }
}
