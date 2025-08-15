package chapter31;

import java.time.Duration;
import java.time.LocalTime;

public class LocalTimeDemo {
    public static void main(String[] args) {
        LocalTime now = LocalTime.now();
        System.out.println("지금 시각 " + now);

        // 2시간 10분 뒤 화상 미팅 예정
        LocalTime mt = now.plusHours(2);
        mt = mt.plusMinutes(10);

        // 화상 미팅 시작 시간
        System.out.println("화상 미팅 시작 시각: " + mt);

        LocalTime start = LocalTime.of(14,35,30);
        System.out.println("공부 시작 시각: " + start);

        LocalTime end = LocalTime.of(17,10,23);
        System.out.println("공부 종료 시각: " + end);

        // 공부 시간 계산
        Duration between = Duration.between(start, end);
        System.out.println("공부 지속 시간: " + between.getSeconds()/60 +"분");
        System.out.println("공부 지속 시간: " + between);
    }
}
