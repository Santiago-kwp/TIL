package chapter28.optional.flatMapEx;

import java.util.Optional;

class Friend {
    String name;
    Optional<Company> cmp; // nullable

    public Friend(String n, Optional<Company> c) {
        name = n;
        cmp = c;
    }
    public String getName() { return name; }
    public Optional<Company> getCmp() { return cmp; }
}

class Company {
    String cName;
    Optional<ContInfo> cInfo; // nullable

    public Company(String cn, Optional<ContInfo> ci) {
        cName = cn;
        cInfo = ci;
    }
    public String getCName() { return cName; }
    public Optional<ContInfo> getCInfo() { return cInfo; }

}

public class NullPointerCaseStudy3 {
    public static void showCompAddr(Optional<Friend> f) {
        String addr = f.flatMap(Friend::getCmp)
                .flatMap(Company::getCInfo)
                .flatMap(ContInfo::getAdrs)
                .orElse("There is no Address information");

        System.out.println(addr);
    }
    public static void main(String[] args) {
        Optional<ContInfo> ci = Optional.of(
                new ContInfo(Optional.ofNullable(null),Optional.of("Republic of Korea"))
        );
        Optional<Company> cp = Optional.of(new Company("Yaho co. Ltd.", ci));
        Optional<Friend> frn = Optional.of(new Friend("Lee SU", cp));
        showCompAddr(frn);

    }

}
