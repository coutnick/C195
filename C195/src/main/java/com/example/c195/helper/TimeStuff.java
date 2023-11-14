package com.example.c195.helper;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;

/**
 * The TimeStuff helper classes are all functions for converting between time zones as well as functions that determine
 * overlapping appointment times and upcoming appointments.
 */
public class TimeStuff {
    /**
     * Converts the provided time to UTC time
     * @param localDateTime -any local date time
     * @return the LocalDateTime in UTC time
     */
   public static LocalDateTime utcFormattedTime(LocalDateTime localDateTime) {
        ZoneId utcZoneID = ZoneId.of("UTC");
        ZoneId myZTD = ZoneId.systemDefault();
        ZonedDateTime myZDT = ZonedDateTime.of(localDateTime, myZTD);
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(myZDT.toInstant(), utcZoneID);
        return utcZDT.toLocalDateTime();
    }

    /**
     * This function returns a UTC converted time into a local machines time
     * @param utcLocalDateTime a time in UTC
     * @return the utc time converted to the users local machine time
     */
   public static LocalDateTime localFormattedTime(LocalDateTime utcLocalDateTime) {
       ZoneId myZTD = ZoneId.systemDefault();
       ZoneId utcZoneID = ZoneId.of("UTC");
       ZonedDateTime convertedZDT = ZonedDateTime.of(utcLocalDateTime, utcZoneID);
       ZonedDateTime instantZDT = ZonedDateTime.ofInstant(convertedZDT.toInstant(), myZTD);
       return instantZDT.toLocalDateTime();

   }

    /**
     * This function returns a time in American/Eastern time
     * @param localDateTime any time from any timezone
     * @return the localDate time converted to est
     */
   public static LocalDateTime estFormattedTime(LocalDateTime localDateTime) {
       ZoneId estZoneId = ZoneId.of("US/Eastern");
       ZoneId utcZoneId = ZoneId.of("UTC");
       LocalDateTime utcTime = utcFormattedTime(localDateTime);
       ZonedDateTime convertToZDT = ZonedDateTime.of(utcTime, utcZoneId);
       ZonedDateTime estTime = ZonedDateTime.ofInstant(convertToZDT.toInstant(), estZoneId);
       return estTime.toLocalDateTime();
   }

    /**
     * This function returns true if the day is a weekend
     * @param localDateTime any time
     * @return if the localDateTime is a weekend
     */
   public static Boolean isWeekend(LocalDateTime localDateTime) {
       DayOfWeek day = DayOfWeek.of(localDateTime.get(ChronoField.DAY_OF_WEEK));
       return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
   }

    /**
     * This fucntion determines if two appointment times overlap
     * @param startOne -start time of the first appointment
     * @param endOne -end time of the first appointment
     * @param startTwo -start time of the second appointment
     * @param endTwo -end time of the second appointment
     * @return True if the appointments overlap. False if not.
     */
   public static Boolean isOverlapping(LocalDateTime startOne, LocalDateTime endOne, LocalDateTime startTwo, LocalDateTime endTwo) {
       System.out.println(startOne);
       System.out.println(startTwo);
       System.out.println(endOne);
       System.out.println(endTwo);
       Boolean overlaps = (startOne.isBefore(endTwo)) && (endOne.isAfter(startTwo));
       System.out.println(overlaps);
       return overlaps;
   }

    /**
     * This function returns true if an appointment is starting within 15 minutes a user logging in.
     * @param startTime start time of an appointment
     * @return true if the appointment starts within 15 minutes
     */
   public static Boolean withinFifteen(LocalDateTime startTime) {
       LocalDateTime now = LocalDateTime.now();
       LocalDateTime utcTime = TimeStuff.utcFormattedTime(now);
       return startTime.isAfter(utcTime) && startTime.isBefore(utcTime.plusMinutes(15));
   }
}
