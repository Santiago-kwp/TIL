package chapter29;

import java.util.Arrays;
import java.util.List;

public class FilterStream {
    public static void main(String[] args) {
        int[] ar = {12, 4, 3, 29, 100, 200};
        Arrays.stream(ar)
                .filter(n -> n%3 == 0) // 3의 배수만 통과시킨다
                .forEach(n -> System.out.print(n + "\t")); // 12 3
        System.out.println();

        List<String> sl = Arrays.asList("Toy","Example","Nespresso");
        sl.stream()
                .filter(s -> s.length() > 3)
                .forEach(s -> System.out.print(s + "\t")); // Example Nespresso
    }
}
