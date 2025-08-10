package chapter29;

import java.util.ArrayList;
import java.util.List;

class ToyPriceInfo {
    private String model; // model name
    private int price; // model price

    public ToyPriceInfo(String m, int p) {
        model = m;
        price = p;
    }
    public int getPrice() {
        return price;
    }

    public String getModel() {return model;}
}

public class ToyStream {
    public static void main(String[] args) {
        List<ToyPriceInfo> ls = new ArrayList<>();
        ls.add(new ToyPriceInfo("GUN_LR_34", 200));
        ls.add(new ToyPriceInfo("TEDDY_BEAR_2",350));
        ls.add(new ToyPriceInfo("CAR_TRANSFORMER_354",550));

        int sum = ls.stream()
                .filter(s -> s.getPrice() < 500)
                .mapToInt(t -> t.getPrice())
                .sum();
        System.out.println(sum); // 200 + 350 = 550

        ls.stream()
                .filter(s -> s.getModel().length() > 10)
                .map(t -> t.getModel())
                .forEach(s -> System.out.print(s + "\t"));
    }
}
