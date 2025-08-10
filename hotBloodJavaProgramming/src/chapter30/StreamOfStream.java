package chapter30;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamOfStream {
    public static void main(String[] args) {
        Stream.of(11, 22, 33, 44, 55)
                .forEach(n -> System.out.print(n + "\t")); // 11 22 33 44 55
        System.out.println();

        Stream.of("So Simple")
                .forEach(s -> System.out.print(s + "\t")); // So Simple
        System.out.println();

        List<String> sl = Arrays.asList("Toy","Robot","Box"); // [Toy, Robot, Box]
        Stream.of(sl)
                .forEach(w -> System.out.print(w + "\t"));
        System.out.println();
    }
}
