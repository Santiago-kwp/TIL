package chapter34;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsDemo {
    public static void main(String[] args) {
        Runnable task = () -> {
            int n1 = 10;
            int n2 = 100;
            String name = Thread.currentThread().getName();
            System.out.println(name + ": " + (n1+n2)); //pool-1-thread-1: 110
        };

        ExecutorService exr = Executors.newSingleThreadExecutor();
        exr.submit(task); // 쓰레드 풀에 작업을 전달

        System.out.println("End " + Thread.currentThread().getName()); // End main
        exr.shutdown(); // 쓰레드 풀과 그 안에 있는 쓰레드의 소멸
    }
}
