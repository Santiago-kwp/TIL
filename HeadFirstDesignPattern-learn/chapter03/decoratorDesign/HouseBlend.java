package chapter03.decoratorDesign;

public class HouseBlend extends Beverage {
    public HouseBlend(){
        description = "House Blend";
    }

    @Override
    public double cost() {
        return 0.95;
    }
}
