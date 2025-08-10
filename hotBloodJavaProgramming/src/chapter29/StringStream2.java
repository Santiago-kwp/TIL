package chapter29;

import java.util.Arrays;

public class StringStream2 {
    public static void main(String[] args) {
        String[] names = {"Park", "Kim", "Kang", "Lee"};
        Arrays.stream(names).forEach(System.out::println);
    }
}
