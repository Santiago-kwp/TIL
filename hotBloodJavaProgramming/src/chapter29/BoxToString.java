package chapter29;

import java.util.Arrays;
import java.util.List;

class Box<T> {
    private T ob;
    public Box(T o) { ob = o; }
    public T get() { return ob; }
}

public class BoxToString {
    public static void main(String[] args) {
        List<Box<String>> ls = Arrays.asList(new Box<>("Robot"), new Box<>("Simple"));

        // 상자에 담긴 문자열을 출력하도록
        ls.stream().map(Box::get)
                .forEach(s -> System.out.print(s + "\t"));

        System.out.println();
        // 상자에 담긴 문자열의 길이를 출력하도록
        ls.stream().map(b -> b.get().length())
                .forEach(s -> System.out.print(s + "\t"));
    }
}
