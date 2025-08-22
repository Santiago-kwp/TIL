package chapter34;

class Task extends Thread {
    @Override
    public void run() {
        int n1 = 10;
        int n2 = 100;
        String name = Thread.currentThread().getName();
        System.out.println(name + ": "+ (n1+n2));
    }
}
public class MakeThreadDemo2 {
    public static void main(String[] args) {
       Thread t1 = new Task();
       Thread t2 = new Task();

        t1.start(); // Thread 생성 및 실행
        t2.start(); // Thread 생성 및 실행

        System.out.println("End " + Thread.currentThread().getName());
    }
}

/* 실행 결과
End main
Thread-0: 110
Thread-1: 110
 */