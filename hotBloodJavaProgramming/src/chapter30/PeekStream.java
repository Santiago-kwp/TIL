package chapter30;

import java.util.stream.IntStream;

public class PeekStream {
    public static void main(String[] args) {
        IntStream.of(1, 2, 3, 4, 7, 9)
                .peek(d-> System.out.print(d+"\t"));
        System.out.println(); // 아무것도 출력 안됨

        IntStream.of(1, 2, 3, 4, 7, 9)
                .peek(d-> System.out.print(d+"\t"))
                .average()
                .ifPresent(s-> System.out.println("avg : "+s));
    }
}
