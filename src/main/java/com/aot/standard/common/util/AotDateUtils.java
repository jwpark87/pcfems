package com.aot.standard.common.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AotDateUtils {
    protected AotDateUtils() {
        throw new UnsupportedOperationException();
    }

    public static final DateTimeZone TIME_ZONE = DateTimeZone.forID("Asia/Seoul");
    public static final Locale LOCALE = Locale.KOREAN;

    public static final String HH_MM_SS = "HH:mm:ss";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String HHMMSS = "HHmmss";
    public static final String YYYYMM = "yyyyMM";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYYMMDDHHMM = "yyyyMMddHHmm";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String getStringNow(final String pattern) {
        return new DateTime().toString(pattern);
    }

    public static String getString(final Long timestamp, final String pattern) {
        if (timestamp == null) {
            return null;
        }
        return new DateTime(timestamp).toString(pattern);
    }

    public static String getString(final Date date, final String pattern) {
        if (date == null) {
            return "";
        }
        return AotDateUtils.getString(date.getTime(), pattern);
    }

    // public static String getString(final DateTime dateTime, final String pattern) {
    // return dateTime.toString(pattern);
    // }

    public static String getString(final DateTime dateTime, final String pattern) {
        if (dateTime == null) {
            return "";
        }
        return dateTime.toString(pattern);
    }

    public static String getString(final String string, final String fromPattern, final String toPattern) {
        if (StringUtils.isEmpty(string)) {
            return "";
        }
        return getDateTime(string, fromPattern).toString(toPattern);
    }

    public static DateTime getDateTimeNow() {
        return DateTime.now();
    }

    public static DateTime getDateTime(final String text, final String pattern) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        return DateTime.parse(text, DateTimeFormat.forPattern(pattern));
    }

    public static DateTime getDateTime(final Long timestamp) {
        if (timestamp == null) {
            return null;
        }
        return new DateTime(timestamp);
    }

    public static DateTime getDateTime(final Date date) {
        if (date == null) {
            return null;
        }
        return new DateTime(date);
    }

    public static List<DateTime> getBeetwenAllDate(final String from, final String to, final String pattern) {
        DateTime fromDt = AotDateUtils.getDateTime(from, pattern);
        final DateTime toDt = AotDateUtils.getDateTime(to, pattern);

        if (fromDt == null || toDt == null) {
            return null;
        }

        final List<DateTime> res = new ArrayList<>();
        while (fromDt.compareTo(toDt) != 1) {
            res.add(fromDt);
            if (YYYYMMDD.equals(pattern)) {
                // 시작날짜 + 1 일
                fromDt = fromDt.plusDays(1);
            } else if (YYYYMM.equals(pattern)) {
                fromDt = fromDt.plusMonths(1);
            }
        }
        return res;
    }

}
