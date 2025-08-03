package chapter28;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class prob28_1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("robot");
        list.add("Lambda");
        list.add("box");

        // 람다식 기반 메소드 호출
//        Collections.sort(list, (s1, s2) -> s1.compareToIgnoreCase(s2));

        // 메소드 참조 기반 호출
        Collections.sort(list,String::compareToIgnoreCase);
        System.out.println(list);


    }
}
