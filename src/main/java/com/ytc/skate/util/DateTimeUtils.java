package com.ytc.skate.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * Created by xinglu02 on 2019-04-26
 */
public class DateTimeUtils {

    private static final ZoneId ZONE_ID = ZoneId.systemDefault();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter FORMATTER_MIN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter HOUR_MIN = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter HOUR = DateTimeFormatter.ofPattern("HH:mm");

    private DateTimeUtils() {
        throw new IllegalStateException("Utility class");
    }



    /**
     * LocalDate转为10:00:00
     *
     * @param dateTime
     * @return
     */
    public static String dateTime2MinSec(LocalDateTime dateTime) {
        return dateTime.format(HOUR_MIN);
    }

    public static LocalDateTime time2DateHHmm(String time) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String endTimeStr = String.format("%s %s", localDateTime.format(DATE_FORMATTER), time);
        if (time.length() > 5) {
            return LocalDateTime.parse(endTimeStr, FORMATTER);
        }
        return LocalDateTime.parse(endTimeStr, FORMATTER_MIN);
    }

    /**
     * LocalDate转为10:00
     *
     * @param dateTime
     * @return
     */
    public static String dateTime2Time(LocalDateTime dateTime) {
        return dateTime.format(HOUR);
    }

    public static LocalDateTime toDT(Date date) {
        if (date == null) {
            return null;
        }
        return LocalDateTime.ofInstant(date.toInstant(), ZONE_ID);
    }

    public static String dateTime2DateFormat(LocalDateTime dateTime) {
        return dateTime.format(DATE_FORMATTER);
    }

    public static String dateTime2DateFormatMMDD(LocalDateTime dateTime) {
        return dateTime.getMonthValue() + "月" + dateTime.getDayOfMonth() + "日";
    }

    public static boolean isSameDay(LocalDateTime time, LocalDateTime time2) {
        return time2.toLocalDate().equals(time.toLocalDate());
    }

    public static long dateTime2DayMilliSec(LocalDateTime time) {
        return time.toLocalDate().atStartOfDay().toInstant(ZONE_ID.getRules().getOffset(time)).toEpochMilli();
    }



    public static LocalDateTime getMinAftDay(LocalDateTime time, Integer passMinute) {
        LocalDateTime res = time;
        while (isSameDay(res, time)){
            res = res.plusMinutes(passMinute.shortValue());
        }
        return res;
    }

    public static long dateTime2MilliSec(LocalDateTime time) {
        return time.atZone(ZONE_ID).toInstant().toEpochMilli();
    }

    public static LocalDateTime long2LocalDateTime(Long time) {
        if (Objects.isNull(time)) {
            return null;
        }
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZONE_ID);

    }

    public static LocalDateTime time2DateTime(String time, Integer offSetDay) {
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(offSetDay);
        String endTimeStr = String.format("%s %s", localDateTime.format(DATE_FORMATTER), time);
        LocalDateTime dateTime = LocalDateTime.parse(endTimeStr, FORMATTER);
        return dateTime;
    }
}
