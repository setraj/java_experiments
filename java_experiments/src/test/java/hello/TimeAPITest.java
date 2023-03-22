package hello;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.Set;

public class TimeAPITest {
    //REF: https://www.baeldung.com/java-8-date-time-intro

    @Test
    public void locaDateTest() {
        /* LocalDate, LocalTime and LocalDateTime ---> As their names indicate, they represent the local date/time from the context of the observer.*/
        System.out.println(LocalDate.now());
        System.out.println(LocalDate.of(2015, 02, 20));
        System.out.println("2015-02-20");

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        System.out.println(tomorrow);

        LocalDate previousMonthSameDay = LocalDate.now().minus(1, ChronoUnit.MONTHS);
        System.out.println(previousMonthSameDay);

        DayOfWeek sunday = LocalDate.parse("2016-06-12").getDayOfWeek();
        System.out.println(sunday);
        int twelve = LocalDate.parse("2016-06-12").getDayOfMonth();
        System.out.println(twelve);

        //LocalDateTime beginningOfDay = LocalDate.parse("2016-06-12").atStartOfDay();
        LocalDateTime beginningOfDay = LocalDate.now().atStartOfDay();
        LocalDate firstDayOfMonth = LocalDate.now()
                .with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(beginningOfDay);
        System.out.println(firstDayOfMonth);
    }

    @Test
    public void locaTimeTest() {
        LocalTime now = LocalTime.now();
        System.out.println("local time: " + now);
        LocalTime sevenThirty = LocalTime.parse("06:30").plus(1, ChronoUnit.HOURS);
        System.out.println("sevenThirty: " +  sevenThirty );
        LocalTime maxTime = LocalTime.MAX;
        System.out.println("maxTime: " + maxTime);
        System.out.println("minTime: " + LocalTime.MIN);
    }
    @Test
    public void locaDateTimeTest() {
        System.out.println(LocalDateTime.now());
        //LocalDateTime.parse("2015-02-20T06:30:00");
        //LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30);
        //localDateTime.plusDays(1);
        //localDateTime.minusHours(2);
        //localDateTime.getMonth();
    }

    @Test
    public void zonedDateTimeTest() {
        //REF: REF: https://stackoverflow.com/questions/54992236/java8-zoneddatetime-doesnt-print-est-current-time-what-am-i-doing-wrong-here
        /*
        * 1. Java 8 provides ZonedDateTime when we need to deal with time-zone-specific date and time
        * 2. The ZoneId is an identifier used to represent different zones.
        * 3. There are about 40 different time zones
        * */
        /*ZoneId zoneId = ZoneId.of("Europe/Paris");
        System.out.println(zoneId);
        Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
        System.out.println(allZoneIds);*/


        ZoneId zoneId = ZoneId.of("US/Pacific");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("local system time: " + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")));
        /*
        This will not convert local date time to zone date time. because LocalDateTime has no time zone info.
        so even if we attach zoneId to it, it can't magically convert the current data time to mentioned zoneId date time
        because it doesn't know the FROM.
        */
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
        System.out.println("\nJust attaching zone id doesn't convert the local time to given zone time.[ZonedDateTime.of(localDateTime, zoneId)]");
        //this will print the local date time only
        System.out.println(zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")));

        /*
            CORRECT WAY OF PRINTING ZONED TIME
        *   REF: https://stackoverflow.com/questions/54992236/java8-zoneddatetime-doesnt-print-est-current-time-what-am-i-doing-wrong-here
        */
        System.out.println("\nCorrect ways: ");
        //The correct way is to use ZonedDateTime with the zoneId to print that zone's date time.
        System.out.println("US/Pacific: " + ZonedDateTime.now(zoneId).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")));


        ZonedDateTime zdtUTC = ZonedDateTime.now( ZoneId.of( "UTC" )) ; // UTC
        System.out.println("UTC: " +  zdtUTC.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")));

        //UTC to America/Chicago
        ZonedDateTime zdtPST = zdtUTC.withZoneSameInstant( ZoneId.of( "America/Chicago" ) ) ; //CST
        System.out.println("America/Chicago[CST]: " + zdtPST.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")));

        ZonedDateTime zdtUsPacific = ZonedDateTime.now( ZoneId.of( "US/Pacific" ) ) ; // PDT/PST
        System.out.println("US/Pacific[PDT/PST]: " + zdtUsPacific.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")));

        // US/Pacific TO America/New_York
        // Same moment, same point on the timeline, different wall-clock time.
        //Notice the phrase SameInstant that means you want the same moment, the same simultaneous point on the timeline
        ZonedDateTime zdtNewYork = zdtUsPacific.withZoneSameInstant(ZoneId.of( "America/New_York" ) ) ; //EDT
        System.out.println("US/Pacific[PST] to America/New_York[EDT]: " + zdtNewYork.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")));
    }
}
