package chapter31;

import java.time.ZoneId;

public class zoneIdDemo {
    public static void main(String[] args) {
        ZoneId paris = ZoneId.of("Europe/Paris");
        System.out.println(paris); // Europe/Paris

        ZoneId.getAvailableZoneIds()
                .stream()
                .sorted()
                .forEach(System.out::println);

        ZoneId.getAvailableZoneIds()
                .stream()
                .filter(s->s.startsWith("Asia"))
                .sorted()
                .forEach(System.out::println);


    }
}
