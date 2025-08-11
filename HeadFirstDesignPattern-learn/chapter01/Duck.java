package chapter01;

public abstract class Duck {

    // 행동 인터페이스 형식의 레퍼런스 변수 2개를 선언
    // 같은 패키지에 속하는 서브 클래스에서 이 변수를 상속 받음.
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public Duck() {}
    public abstract void display();

    public void performFly() {
        flyBehavior.fly(); // 행동 클래스에 위임
    }

    public void performQuack() {
        quackBehavior.quack(); // 행동 클래스에 위임
    }

    public void swim() {
        System.out.println("모든 오리는 물에 뜹니다. 가짜 오리도 뜨죠.");
    }

    // 아래 두 메소드를 호출하면 언제든지 오리의 행동을 즉석에서 바꿀 수 있음
    public void setFlyBehavior(FlyBehavior fb) {
        flyBehavior = fb;
    }

    public void setQuackBehavior(QuackBehavior qb) {
        quackBehavior = qb;
    }

}
