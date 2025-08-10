package chapter28.optional;

import java.util.Optional;

public class OptionalOrElse {
    public static void main(String[] args) {
        Optional<String> os1 = Optional.empty();
        Optional<String> os2 = Optional.of("So Basic");

        String s1 = os1.map(s -> s.toString()).orElse("Empty");
        String s2 = os2.map(s -> s.toString()).orElse("Empty");

        System.out.println(s1); // Empty
        System.out.println(s2); // So Basic
    }
}
