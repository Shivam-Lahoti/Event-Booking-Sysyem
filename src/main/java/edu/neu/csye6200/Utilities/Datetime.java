package edu.neu.csye6200.Utilities;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Datetime {
    public static String localDateTime_toString(LocalDateTime localDateTimeInstance) {
        LocalDateTime epoch = LocalDateTime.of(1970, 1, 1, 0, 0, 0);
        Duration duration = Duration.between(epoch, localDateTimeInstance);
        long seconds = duration.get(ChronoUnit.SECONDS);
        return String.valueOf(seconds);
    }

    public static String currentTime_toString() {
        return localDateTime_toString(LocalDateTime.now());
    }

    public static LocalDateTime string_toLocalDateTime(String secondString) {
        Instant instant = Instant.ofEpochSecond(Long.parseLong(secondString));
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    }
    public static String turnLocalDateTimeIntoFriendlyString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static LocalDateTime turn_yyyyMMddHHmmss_into_LocalDateTime(String secondString) {
        return LocalDateTime.parse(secondString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // This method is used to check if a string is a valid datetime string, e.g. "2020-12-31 23:59:59"
    public static boolean isValidDatetime(String yyyyMMddHHmmssString) {
        try {
            turn_yyyyMMddHHmmss_into_LocalDateTime(yyyyMMddHHmmssString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
