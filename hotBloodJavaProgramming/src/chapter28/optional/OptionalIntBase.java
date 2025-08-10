package chapter28.optional;

import java.sql.SQLOutput;
import java.util.OptionalInt;

public class OptionalIntBase {
    public static void main(String[] args) {
        OptionalInt oi1 = OptionalInt.of(3);
        OptionalInt oi2 = OptionalInt.empty();

        System.out.print("[Step 1.] : ");
        oi1.ifPresent(i -> System.out.print(i + "\t"));
        oi2.ifPresent(System.out::print);
        System.out.println();

        System.out.print("[Step 2.] : ");
        System.out.print(oi1.orElse(100) + "\t");
        System.out.print(oi2.orElse(100) + "\t");

    }
}
