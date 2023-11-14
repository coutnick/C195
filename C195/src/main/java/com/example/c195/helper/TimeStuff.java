package com.example.c195.helper;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;

public class TimeStuff {
   public static LocalDateTime utcFormattedTime(LocalDateTime localDateTime) {
        ZoneId utcZoneID = ZoneId.of("UTC");
        ZoneId myZTD = ZoneId.systemDefault();
        ZonedDateTime myZDT = ZonedDateTime.of(localDateTime, myZTD);
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(myZDT.toInstant(), utcZoneID);
        return utcZDT.toLocalDateTime();
    }

   public static LocalDateTime localFormattedTime(LocalDateTime utcLocalDateTime) {
       ZoneId myZTD = ZoneId.systemDefault();
       ZoneId utcZoneID = ZoneId.of("UTC");
       ZonedDateTime convertedZDT = ZonedDateTime.of(utcLocalDateTime, utcZoneID);
       ZonedDateTime instantZDT = ZonedDateTime.ofInstant(convertedZDT.toInstant(), myZTD);
       return instantZDT.toLocalDateTime();

   }

   public static LocalDateTime estFormattedTime(LocalDateTime localDateTime) {
       ZoneId estZoneId = ZoneId.of("US/Eastern");
       ZoneId utcZoneId = ZoneId.of("UTC");
       LocalDateTime utcTime = utcFormattedTime(localDateTime);
       ZonedDateTime convertToZDT = ZonedDateTime.of(utcTime, utcZoneId);
       ZonedDateTime estTime = ZonedDateTime.ofInstant(convertToZDT.toInstant(), estZoneId);
       return estTime.toLocalDateTime();
   }

   public static Boolean isWeekend(LocalDateTime localDateTime) {
       DayOfWeek day = DayOfWeek.of(localDateTime.get(ChronoField.DAY_OF_WEEK));
       return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
   }

   public static Boolean isOverlapping(LocalDateTime startOne, LocalDateTime endOne, LocalDateTime startTwo, LocalDateTime endTwo) {
       System.out.println(startOne);
       System.out.println(startTwo);
       System.out.println(endOne);
       System.out.println(endTwo);
       Boolean overlaps = (startOne.isBefore(endTwo)) && (endOne.isAfter(startTwo));
       System.out.println(overlaps);
       return overlaps;
   }

   public static Boolean withinFifteen(LocalDateTime startTime) {
       LocalDateTime now = LocalDateTime.now();
       LocalDateTime utcTime = TimeStuff.utcFormattedTime(now);
       return startTime.isAfter(utcTime) && startTime.isBefore(utcTime.plusMinutes(15));
   }
}
