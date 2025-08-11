package chapter01;

public class SQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("삑");
    }
}
