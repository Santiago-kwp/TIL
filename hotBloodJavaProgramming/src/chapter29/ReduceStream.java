package chapter29;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

public class ReduceStream {
    public static void main(String[] args) {
        List<String> ls = Arrays.asList("Box","Simple","Complex","Robot");

        BinaryOperator<String> lc = (s1, s2) -> {
            if (s1.length() > s2.length())
                return s1;
            else
                return s2;
        };
        String str = ls.stream()
                .reduce("",lc);
        String strP = ls.parallelStream().reduce("",lc); // 병렬 처리를 위한 스트림 생성
        System.out.println(str); // complex
        System.out.println(strP);
    }
}
