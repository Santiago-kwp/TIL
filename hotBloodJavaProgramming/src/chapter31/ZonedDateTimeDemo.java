package chapter31;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedDateTimeDemo {
    public static void main(String[] args) {
        ZonedDateTime here = ZonedDateTime.now();
        System.out.println(here);

        // 날짜와 시간 정보만 담아서 첫번째 인자로 활용
        ZonedDateTime paris = ZonedDateTime.of(
                here.toLocalDateTime(), ZoneId.of("Europe/Paris"));

        System.out.println(paris);

        // 2025-08-15T13:10:29.381045+09:00[Asia/Seoul]
        // 2025-08-15T13:10:29.381045+02:00[Europe/Paris]

        // 이곳과 파리의 시차
        Duration betweenHeretoParis = Duration.between(here, paris);
        System.out.println(betweenHeretoParis); // PT7H
    }
}
