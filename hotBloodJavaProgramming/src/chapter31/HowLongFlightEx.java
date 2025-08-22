package chapter31;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class HowLongFlightEx {
    public static void main(String[] args) {
        ZonedDateTime flightDepart = ZonedDateTime.of(LocalDateTime.of(2025,8,14,14,30),
                ZoneId.of("Asia/Seoul"));

        ZonedDateTime flightArrive = ZonedDateTime.of(LocalDateTime.of(2025, 8, 16, 17,30),
                ZoneId.of("Europe/Paris"));

        Duration flightTime = Duration.between(flightDepart,flightArrive);
        System.out.println("서울에서 파리까지 비행 시간 : " + flightTime); // PT58H
    }
}
