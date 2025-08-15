package chapter31;

import java.time.LocalDate;
import java.time.Period;

public class LocalDateDemo1 {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("Today: " + today);

        // this year Christmas
        LocalDate xmas = LocalDate.of(today.getYear(), 12, 25);
        System.out.println("Xmas of this Year : " + xmas);

        LocalDate eve = xmas.minusDays(1);
        System.out.println("Xmas Eve: " + eve);

        Period left = Period.between(today,xmas);
        System.out.println("Left days for Christmas " + left.getMonths()+ "월, " + "몇 일 : " + left.getDays());
    }
}
