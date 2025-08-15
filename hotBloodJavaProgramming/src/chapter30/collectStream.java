package chapter30;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class collectStream {
    public static void main(String[] args) {
        List<String> ls = Stream.of("Toy","Box","Length","Pasta")
                .filter(s -> s.length() < 5)
                .collect(()->new ArrayList<>(),
                        (c, s) -> c.add(s),
                        (lst1, lst2) -> lst1.addAll(lst2));
        System.out.println(ls);

    }
}
