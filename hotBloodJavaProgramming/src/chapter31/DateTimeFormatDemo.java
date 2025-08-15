package chapter31;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatDemo {
    public static void main(String[] args) {
        ZonedDateTime date = ZonedDateTime.of(
                LocalDateTime.of(2025, 8, 15, 20, 33), ZoneId.of("Asia/Seoul"));

        // 출력의 포맷 정보는 java.time.format.DateTimeFormatter 인스턴스에 담는다.
        DateTimeFormatter fm1 = DateTimeFormatter.ofPattern("yy-MM-d");
        DateTimeFormatter fm2 = DateTimeFormatter.ofPattern("yyyy-MM-d, H:m:s");
        DateTimeFormatter fm3 = DateTimeFormatter.ofPattern("yyyy-MM-d, HH:mm:ss, VV");

        System.out.println(date.format(fm1));
        System.out.println(date.format(fm2));
        System.out.println(date.format(fm3));

//        25-08-15
//        2025-08-15, 20:33:0
//        2025-08-15, 20:33:00, Asia/Seoul

    }
}
