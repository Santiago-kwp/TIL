package chapter34;

public class MakeThreadDemo {
    public static void main(String[] args) {
        Runnable task = () -> { // 쓰레드가 실행하게 될 내용
            int n1 = 10;
            int n2 = 30;
            String name = Thread.currentThread().getName();
            System.out.println(name + " : " + (n1 + n2));

        };

        Thread t = new Thread(task);
        t.start(); // Thread 생성 및 실행
        System.out.println("End " + Thread.currentThread().getName());
    }
}

/* 실행 결과
End main
Thread-0 : 40
 */