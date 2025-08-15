package chapter31;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public class LocalDateTimeDemo2 {
    public static void main(String[] args) {
        LocalDateTime today = LocalDateTime.of(2020, 4, 25, 11, 20);

        // flight 1 departure time
        LocalDateTime flight1 = LocalDateTime.of(2020,5,14,11,15);

        // flight 2 departure time
        LocalDateTime flight2 = LocalDateTime.of(2020, 5, 13, 15, 30);

        // 빠른 항공편을 선택하기
        LocalDateTime myflight;
        if (flight1.isBefore(flight2))
            myflight = flight1;
        else
            myflight = flight2;

        // 빠른 항공편의 비행 탑승까지 남은 날짜 계산
        Period day = Period.between(today.toLocalDate(), myflight.toLocalDate());

        // 비행 탑승까지 남은 시간 계산
        Duration time = Duration.between(today.toLocalTime(), myflight.toLocalTime());

        // 비행 탑승까지 남은 날짜와 시간 출력
        System.out.println(day);
        System.out.println(time);
    }
}
