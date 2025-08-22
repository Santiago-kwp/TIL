package chapter31;

import java.time.LocalDateTime;

public class LocalDateTimeDemo {
    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();

        System.out.println(now);

        // 영국 바이어와 22시간 35분 뒤에 미팅
        LocalDateTime meeting = now.plusHours(22);
        meeting = meeting.plusMinutes(35);
        System.out.println(meeting);

    }
}
