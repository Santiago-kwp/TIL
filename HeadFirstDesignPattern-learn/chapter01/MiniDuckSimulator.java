package chapter01;

public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.performFly(); // 날개로 날고 있어요.
        mallard.performQuack(); // 꽥

        Duck model = new ModelDuck();
        model.performQuack(); // 조용
        model.performFly(); // 날지 못합니다.
        model.setFlyBehavior(new FlyRocketPowered()); // 모델 오리에 로켓 추진력으로 날 수 있는 능력이 생김
        model.performFly(); // Rocket 추진으로 날아갑니다.

        // Duck 클래스를 상속받지 않고 오리 호출기 구현해보기
        DuckCaller duckCaller = new DuckCaller();
        duckCaller.call(new Quack());


    }
}
