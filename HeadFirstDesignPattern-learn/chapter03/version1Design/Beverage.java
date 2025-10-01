package chapter03.version1Design;

public class Beverage {
    Boolean milk, soy, mocha, whip;
    double milkCost = 0.9;
    double mochaCost = 0.8;
    double whipCost = 0.7;
    double soyCost = 0.4;

    public double cost() {
        double condimentCost = 0.0; // condiment : 조미료, 양념
        if (getMilk()) {condimentCost += condimentCost+milkCost;}
        if (getSoy()) {condimentCost += condimentCost+soyCost;}
        if (getMocha()) {condimentCost += condimentCost+mochaCost;}
        if (getWhip()) {condimentCost += condimentCost+whipCost;}
        return condimentCost;
    }

    public Boolean getMilk() {
        return milk;
    }

    public void setMilk(Boolean milk) {
        this.milk = milk;
    }

    public Boolean getSoy() {
        return soy;
    }

    public void setSoy(Boolean soy) {
        this.soy = soy;
    }

    public Boolean getMocha() {
        return mocha;
    }

    public void setMocha(Boolean mocha) {
        this.mocha = mocha;
    }

    public Boolean getWhip() {
        return whip;
    }

    public void setWhip(Boolean whip) {
        this.whip = whip;
    }
}
