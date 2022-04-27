package sm.sandy.java8inaction.ch12;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;

public class PeriodAndDuration {
    public static void main(String[] args) {
        Duration d1 = Duration.between(LocalTime.of(11, 22),
                LocalTime.of(12, 33));

        System.out.println(d1.get(ChronoUnit.SECONDS));

        /*d1 = Duration.between(LocalDate.of(2022, 03, 12),
                LocalDate.of(2022, 03, 8));*/
        //System.out.println(d1.get(ChronoUnit.DAYS));  //Unsupported unt: duration is for seconds/milliseconds

        Period p = Period.between(LocalDate.of(2022, 03, 12),
                LocalDate.of(2022, 03, 8));

        System.out.println(Math.abs(p.getDays()));
        System.out.println(p.getMonths());

        d1 = Duration.ofHours(1);
        p = Period.ofDays(10);

        //System.out.println(LocalDate.now().plus(d1)); //exception: UnsupportedTemporalType since LocalDate does not represent time
        System.out.println(LocalDateTime.now().plus(d1));
        System.out.println(LocalDate.now().plus(p));

        System.out.println(LocalDateTime.now().atZone(ZoneId.of("UTC")));

        System.out.println(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()));
        System.out.println(LocalDateTime.now().with(TemporalAdjusters.dayOfWeekInMonth(4, DayOfWeek.SUNDAY)));

    }
}
