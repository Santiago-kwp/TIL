package chapter03.decoratorDesign;

public abstract class Beverage {
    // 사이즈 개념 도입 버전
    public enum Size {TALL, GRANDE, VENTI};
    Size size = Size.TALL;

    String description = "Unknown Beverage";
    public String getDescription() {
        return description;
    }

    public void setSize(Size size) {
        this.size = size;
    }
    public Size getSize() {
        return this.size;
    }

    public abstract double cost();
}
