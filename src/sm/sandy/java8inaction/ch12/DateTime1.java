package sm.sandy.java8inaction.ch12;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTime1 {
    public static void main(String[] args) {
        LocalDate ld = LocalDate.of(2022, 3, 12);
        LocalTime ln = LocalTime.of(11, 22);

        LocalDateTime ldt = LocalDateTime.of(ld, ln);
        System.out.println(ldt.toString());

        ldt = ld.atTime(ln);
        ldt = ln.atDate(ld);

        System.out.println(ldt);

    }
}
