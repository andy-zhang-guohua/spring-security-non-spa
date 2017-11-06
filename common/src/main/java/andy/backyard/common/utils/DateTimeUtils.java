package andy.backyard.common.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by ZhangGuohua on 2016/11/14.
 */
public class DateTimeUtils {

    public static LocalDate toLocalDate(Date date) {
        if (date == null)
            return null;

        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }


    public static LocalDateTime toLocalDateTime(Date date) {
        if (date == null)
            return null;

        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }


    public static LocalDateTime toLocalDateTime(LocalDate date) {
        if (date == null)
            return null;

        return LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), 0, 0);
    }

    public static Date toDate(LocalDate localDate) {
        if (localDate == null)
            return null;

        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date result = Date.from(instant);
        return result;
    }

    public static Date toDate(LocalDateTime localDateTime) {
        if (localDateTime == null)
            return null;

        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date result = Date.from(instant);
        return result;
    }

    public static LocalDateTime toLocalDateTime(long timeMillis) {
        if (timeMillis == 0)
            return null;
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timeMillis), TimeZone.getDefault().toZoneId());
    }

    public static LocalDate toLocalDate(long timeMillis) {
        LocalDateTime date = toLocalDateTime(timeMillis);
        return date == null ? null : date.toLocalDate();
    }
}
