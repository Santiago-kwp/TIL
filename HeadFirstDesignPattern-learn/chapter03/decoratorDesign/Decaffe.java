package chapter03.decoratorDesign;

public class Decaffe extends Beverage {
    public Decaffe(){
        description = "Decaffe";
    }

    @Override
    public double cost() {
        return 1.55;
    }
}
