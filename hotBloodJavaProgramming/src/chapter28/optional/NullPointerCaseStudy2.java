package chapter28.optional;

import java.util.Optional;

class Friend {
    String name;
    Company cmp; // null possible

    public Friend(String n, Company c) {
        name = n;
        cmp = c;
    }
    public String getName() { return name; }
    public Company getCmp() { return cmp; }
}

class Company {
    String cName;
    ContInfo cInfo; // null possible

    public Company(String cn, ContInfo ci) {
        cName = cn;
        cInfo = ci;
    }

    public String getCName() {
        return cName;
    }

    public ContInfo getCInfo() {
        return cInfo;
    }
}

class ContInfo {
    String phone; // null possible
    String adrs; // null possible

    public ContInfo(String ph, String ad) {
        phone = ph;
        adrs = ad;
    }
    public String getPhone() { return phone; }
    public String getAdrs() { return adrs; }
}

public class NullPointerCaseStudy2 {
    public static void showCompAddr(Optional<Friend> f) {
        String addr = f.map(Friend::getCmp)
                .map(Company::getCInfo)
                .map(ContInfo::getAdrs)
                .orElse("There is no address information");
        System.out.println(addr);
    }
    public static void main(String[] args) {
        ContInfo ci = new ContInfo("321-444-577", "Republic of Korea");
        Company cp = new Company("YaHo co., Ltd.", ci);
        Friend frn = new Friend("LEE SU", cp);
        showCompAddr(Optional.of(frn));

    }
}
