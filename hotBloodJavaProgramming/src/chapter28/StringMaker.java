package chapter28;

import java.util.function.Function;

public class StringMaker {
    public static void main(String[] args) {
        /* 람다식 기반 Function 적용
        Function<char[], String> f = ar -> {
            return new String(ar);
        };
         */
        Function<char[], String> f = String::new;

        char[] src = {'R','o','b','o','t'};
        String str = f.apply(src);
        System.out.println(str);
    }
}
