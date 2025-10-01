package chapter03.version1Design;

public class DarkRoast extends Beverage {
    double darkRoastCost = 1.9;
    public DarkRoast() {
        String description = "Best Dark Roast Coffee";

    }
    @Override
    public double cost() {
        return super.cost()+darkRoastCost;
    }
}
