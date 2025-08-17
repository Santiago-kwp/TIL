package chapter34;

import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<Integer> task = () -> {
            int sum = 0;
            for(int i = 0; i < 10; i++)
                sum +=i;
            return sum;
        };

        ExecutorService exr = Executors.newSingleThreadExecutor();
        Future<Integer> fur = exr.submit(task);

        Integer r = fur.get(); // 쓰레드의 반환 값 획득
        System.out.println("result: " + r);
        exr.shutdown();

    }
}
