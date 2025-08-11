package chapter01;

public class FlyNoWay implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("저는 날개가 있지만 못 날아요.");
    }
}
