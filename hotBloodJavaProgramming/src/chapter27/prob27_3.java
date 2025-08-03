package chapter27;

import java.util.function.BiPredicate;

public class prob27_3 {
    public static void main(String[] args) {
        BiPredicate<String, Integer> conv = (s1, n1) -> s1.length() > n1; // 완성되지 않은 문장 완성해보기

        // test 호출 결과 문자열 "Robot"의 길이가 3을 넘으면 true 반환
        if(conv.test("Robot",3))
            System.out.println("문자열 길이 3 초과");
        else
            System.out.println("문자열 길이 3 이하");

        // test 호출 결과 문자열 "Box"의 길이가 5를 넘으면 true 반환
        if(conv.test("Box",5))
            System.out.println("문자열 길이 5 초과");
        else
            System.out.println("문자열 길이 5 이하");
    }
}
