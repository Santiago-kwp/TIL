package chapter01;

public class DuckCaller {
    QuackBehavior quackBehavior;

    public void call(QuackBehavior qb) {
        quackBehavior = qb;
        qb.quack();
    }

}
