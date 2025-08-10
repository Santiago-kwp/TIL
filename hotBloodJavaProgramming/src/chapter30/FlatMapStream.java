package chapter30;

import java.util.Arrays;
import java.util.stream.Stream;

public class FlatMapStream {
    public static void main(String[] args) {
        Stream<String> str = Stream.of("Cheese_cake","Lemon_Pie");
        str.flatMap(s-> Arrays.stream(s.split("_")))
                .forEach(s -> System.out.print(s+"\t"));
    }
}
