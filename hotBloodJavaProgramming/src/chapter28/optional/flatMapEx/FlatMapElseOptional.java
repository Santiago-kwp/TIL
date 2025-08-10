package chapter28.optional.flatMapEx;

import java.util.Optional;
import java.util.OptionalInt;

class ContInfo {
    Optional<String> phone; // nullable
    Optional<String> adrs; // nullable

    public ContInfo(Optional<String> ph, Optional<String> ad) {
        phone = ph;
        adrs = ad;
    }
    public Optional<String> getPhone() { return phone; }
    public Optional<String> getAdrs() { return adrs; }
}
public class FlatMapElseOptional {
    public static void main(String[] args) {
        Optional<ContInfo> ci = Optional.of(
                new ContInfo(Optional.ofNullable(null), Optional.of("Republic of Korea"))
        );

        String phone = ci.flatMap(c -> c.getPhone())
                .orElse("There is no phone number.");
        String addr = ci.flatMap(c -> c.getAdrs())
                .orElse("There is no address");
        System.out.println(phone); // There is no phone number
        System.out.println(addr); // Republic of Korea
    }

}
