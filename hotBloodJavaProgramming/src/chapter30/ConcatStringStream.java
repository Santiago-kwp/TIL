package chapter30;

import java.util.stream.Stream;

public class ConcatStringStream {
    public static void main(String[] args) {
        Stream<String> str1 = Stream.of("Cake","Cheese");
        Stream<String> str2 = Stream.of("Chocolate","IceCream");

        Stream<String> str3 = Stream.concat(str1, str2);

        str3.forEach(s->System.out.print(s+"\t"));

    }
}
