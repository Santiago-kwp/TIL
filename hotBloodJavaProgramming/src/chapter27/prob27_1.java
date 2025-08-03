package chapter27;

interface Calculator<T> {
    T cal(T a, T b);
}

public class prob27_1 {
    public static <T> void calAndShow(Calculator<T> op, T n1, T n2) {
        T r = op.cal(n1, n2);
        System.out.println(r);
    }
    public static void main(String[] args) {
//        Calculator<Integer> add = (a, b) -> a + b;
//        calAndShow(add, 3, 4);
        calAndShow((a,b) -> a+b, 3, 4);
        calAndShow((a, b) -> a+b, 2.5, 7.1);
        calAndShow((a,b)->a-b,4,2);
        calAndShow((a,b)->a-b,4.9,3.2);

    }
}
