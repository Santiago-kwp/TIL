package chapter27;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class prob27_2 {
    public static <T> void show(Predicate<T> p, List<T> lst) {
        for (T t : lst) {
            if (p.test(t)) System.out.print(t+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<Integer> lst1 = Arrays.asList(1, 3, 8, 10, 11);
        show(n -> n%2 !=0, lst1);   // 홀수만 출력

        List<Double> lst2 = Arrays.asList(-1.2, 3.5, -2.4, 9.5);
        show(n -> n > 0.0, lst2);   // 0.0 보다 큰 수 출력
    }
}
