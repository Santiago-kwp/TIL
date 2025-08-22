package chapter31;


import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class HowLongParallel {
    public static long fibonacci(long n) {
        if (n ==1 || n ==2 ) {
            return 1;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(40, 41, 42, 43, 44, 45);
        Instant start = Instant.now();
        nums.parallelStream()
                .map(n -> fibonacci(n))
                .forEach(System.out::println);

        Instant end = Instant.now();
        System.out.println("Parallel Processing :" + Duration.between(start, end).toMillis());
        // 3122ms


    }
}
