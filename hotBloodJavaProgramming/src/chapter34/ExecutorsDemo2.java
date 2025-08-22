package chapter34;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsDemo2 {
    public static void main(String[] args) {
        Runnable task1 = () -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + " : " + (5 + 7));
        };
        Runnable task2 = () -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + " : " + (7 - 5));
        };

        ExecutorService exr = Executors.newFixedThreadPool(2);
        exr.submit(task1);
        exr.submit(task2);
        exr.submit(() -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + " : " + (5 * 7));
        });

        exr.shutdown();
    }
}

/*
실행 결과
pool-1-thread-2 : 2
pool-1-thread-1 : 12
pool-1-thread-2 : 35
 */