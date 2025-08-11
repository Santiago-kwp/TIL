package chapter01;

public class FlyRocketPowered implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("Rocket 추진으로 날아갑니다.");
    }
}
