package chapter30;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class ReportCard {
    private int kor; // korean subject score
    private int eng; // eng subject score;
    private int math; // math subject score;

    public ReportCard(int k, int e, int m) {
        kor = k;
        eng = e;
        math = m;
    }

    public int getKor() {
        return kor;
    }

    public int getEng() {
        return eng;
    }

    public int getMath() {
        return math;
    }
}

public class GradeAverage {
    public static void main(String[] args) {
        ReportCard[] cards = {
                new ReportCard(93, 22, 40),
                new ReportCard(20, 55, 30),
                new ReportCard(50, 89, 99)
        };

        // ReportCard 인스턴스로 이뤄진 스트림 생성
        Stream<ReportCard> sr = Arrays.stream(cards);

        IntStream si = sr.flatMapToInt(
                r -> IntStream.of(r.getEng(), r.getKor(), r.getMath()));

        si.forEach(n -> System.out.print(n+"\t"));
        System.out.println();
        double avg = Arrays.stream(cards).flatMapToInt(
                r -> IntStream.of(r.getMath()))
                .average().getAsDouble();
        // OptionalDouble average() 이므로 그 안에 저장된 값은 getAsDouble() 메소드 호출을 통해 얻거나
        // .ifPresent(avg -> System.out.print("avg." + avg)); 으로 쓸 수 있음.
        System.out.println("avg. for Math " + avg);
    }
}
