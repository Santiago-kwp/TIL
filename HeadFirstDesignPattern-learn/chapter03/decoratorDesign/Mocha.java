package chapter03.decoratorDesign;

public class Mocha extends CondimentDecorator {
    // 감싸고자 하는 음료를 저장하는 인스턴스 변수
    // 인스턴스 변수를 감싸고자 하는 객체로 설정하는 생성자(데코레이터의 생성자에 감싸고자 하는 음료 객체를 전달하는 방식을 사용)
    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }
    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    @Override
    public double cost() {
        double cost = beverage.cost()+0.20;
        switch(beverage.getSize()) {
            case GRANDE -> {
                return cost+0.20;
            }
            case VENTI -> {
                return cost+0.30;
            }
        }
        return cost;
    }
}
