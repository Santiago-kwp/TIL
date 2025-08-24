package chapter03.decoratorDesign;

public abstract class CondimentDecorator extends Beverage {
    // 각 데코레이터가 감쌀 음료를 나타내는 Beverage 객체를 여기에서 지정합니다.
    // 음료를 지정할 때는 어떤 음료든 감쌀 수 있도록 Beverage 슈퍼클래스 유형을 사용
    Beverage beverage;

    // 모든 첨가물 구상 데코레이터 클래스에 getDescription 메소드를 새로 구현하도록 만들 계획.
    public abstract String getDescription();

    public Size getSize() {
        return beverage.getSize();
    }
}
