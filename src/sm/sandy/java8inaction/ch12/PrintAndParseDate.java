package sm.sandy.java8inaction.ch12;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalUnit;

public class PrintAndParseDate {
    static TemporalAdjuster nextWorkingDay = t -> {
        DayOfWeek d = DayOfWeek.from(t);
        int noOfDays = 1;
        if (d == DayOfWeek.FRIDAY)
            noOfDays += 3;
        else if (d == DayOfWeek.SATURDAY)
            noOfDays += 2;

        return t.plus(noOfDays, ChronoUnit.DAYS);
    };

    public static void main(String[] args) {
        LocalDate d = LocalDate.now();
        System.out.println(d.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(d.format(DateTimeFormatter.ISO_LOCAL_DATE));

        System.out.println(d.format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));

        //Friday, 2022, 03, 18
        System.out.println(LocalDate.of(2022, 03, 18).with(nextWorkingDay)); //2022-03-22
    }
}
