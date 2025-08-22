package chapter30;

import java.util.stream.IntStream;

public class MatchStream {
    public static void main(String[] args) {
        boolean pred1 = IntStream.of(1, 2, 3, 4, 5, 9)
                .anyMatch(s->s%2 == 0);
        System.out.println(pred1);

        boolean pred2 = IntStream.of(1, 2, 3, 4, 5, 9)
                .allMatch(s->s%2 == 0);
        System.out.println(pred2);

        boolean pred3 = IntStream.of(1, 2, 3, 4, 5, 9)
                .noneMatch(s->s%2 == 0);
        System.out.println(pred3);


    }
}
