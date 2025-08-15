package chapter30;

import java.util.stream.Stream;

public class InstSortedStream {
    public static void main(String[] args) {
        Stream.of("Box","Apple","Lambda","Photo","User")
                .sorted()
                .forEach(s -> System.out.print(s+ "\t"));
        // Apple Box Lambda Photo User
        System.out.println();
        Stream.of("Box","Apple","Lambda","Photo","User")
                .sorted((s1, s2) -> s1.length() - s2.length())
                .forEach(s -> System.out.print(s+ "\t"));
        //Box User Apple Photo Lambda


    }
}
