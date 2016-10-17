package edu.itu.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TimeZone;

/**
 * Created by xuxu on 10/17/16.
 */
public class TimeUtils {
    public static LocalDateTime getDateTimeFromTimestamp(long timestamp) {
        if (timestamp == 0)
            return null;
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), TimeZone
                .getDefault().toZoneId());
    }

    public static LocalDate getDateFromTimestamp(long timestamp) {
        LocalDateTime date = getDateTimeFromTimestamp(timestamp);
        return date == null ? null : date.toLocalDate();
    }
}
