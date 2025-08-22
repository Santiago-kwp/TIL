package chapter30;

import java.util.stream.IntStream;

public class OpIntStream {
    public static void main(String[] args) {
        // int sum : ifPresent chaining is impossible
        int sum = IntStream.of(1,3,55,39,30,20)
                .sum();
        System.out.println("sum : " + sum);

        long cnt = IntStream.of(1,2,2,3,34,4,5,0)
                .count();
        System.out.println("count : " + cnt);

        IntStream.of(1,3,4,3,2,99,100)
                .average()
                .ifPresent(avg -> System.out.println("average: "+ avg));

        IntStream.of(1,3,2,4,3,1,100)
                .max()
                .ifPresent(mx -> System.out.println("max: "+ mx));

        IntStream.of(new int[]{1,2,43,233,222,11,0})
                .min()
                .ifPresent(mn -> System.out.println("min: " + mn));


    }
}
