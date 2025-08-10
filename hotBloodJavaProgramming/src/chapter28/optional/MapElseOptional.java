package chapter28.optional;

import java.util.Optional;

class ContInfo1 {
    String phone; // null 일 수 있음
    String adrs; // null 일 수 있음

    public ContInfo1(String ph, String ad) {
        phone = ph;
        adrs = ad;
    }
    public String getPhone() { return phone; }
    public String getAdrs() { return adrs; }

}
public class MapElseOptional {
    public static void main(String[] args) {
        Optional<ContInfo1> ci = Optional.of(new ContInfo1(null, "Republic of Korea"));

        String phone = ci.map(c -> c.getPhone()).orElse("There is no phone number");
        String addr = ci.map(c -> c.getAdrs()).orElse("There is no address.");

        System.out.println(phone);
        System.out.println(addr);
    }

}
