package com.dagemen.helper;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil extends DateUtils {
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT_A = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT_B = "yyyyMMddHHmmss";
    public static final String TIME_FORMAT_C = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String TIME_FORMAT_D = "yyyyMMdd";
    public static final String TIME_FORMAT_E = "yyyy年MM月dd日";
    public static final String TIME_FORMAT_F = "yyyyMMddHHmm";
    public static final String TIME_FORMAT_G = "yyyy年MM月dd日HH时mm分ss秒";
    public static final String TIME_FORMAT_H = "yyyy-MM-dd HH:mm";
    public static final String TIME_FORMAT_I = "HH:mm:ss";
    public static final String TIME_FORMAT_J = "yyyyMMddHHmmssSSS";
    public static final String TIME_FORMAT_K = "yy-M-d";
    public static final String TIME_FORMAT_L = "HH:mm";
    public static final String TIME_FORMAT_M = "MM月dd日 HH:mm";
    public static final String DATE_FORMAT_B = "yyyy.MM.dd";
    public static final String YEAR_FORMAT = "yyyy";
    public static final String YEAR_MONTH_FORMAT = "yyyy-MM";
    public static final String MONTH_DAY_FORMAT = "MM-dd";
    public static final String FORMAT_1 = ",##0.00";
    public static final String FORMAT_2 = "0.00";
    public static final String FORMAT_3 = ",###";
    public static final String FIRST_TIME = "1970-01-01 00:00:00";

    public DateUtil() {
    }

    public static String today() {
        return getDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String time() {
        return getDateFormat("HH:mm:ss").format(new Date());
    }

    public static String todayTime() {
        return getDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static String date2String(Date date, String format) {
        String dateStr = null;

        try {
            if (date != null) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
                dateStr = simpleDateFormat.format(date);
            }
        } catch (Exception var4) {
            logger.error("date to string failure. The detail information is: ", var4);
        }

        return dateStr;
    }

    public static Date string2Date(String dateStr, String format) {
        if (!StringUtils.isBlank(dateStr) && !StringUtils.isBlank(format)) {
            Date date = null;

            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
                date = simpleDateFormat.parse(dateStr);
            } catch (ParseException var4) {
                logger.error("string to date failure. The detail information is ", var4);
            }

            return date;
        } else {
            return null;
        }
    }

    public static Date getBeginOfTheDay(Date date) {
        return null == date ? null : string2Date(date2String(date, "yyyy-MM-dd"), "yyyy-MM-dd");
    }

    public static Date getEndOfTheDay(Date date) {
        if (null == date) {
            return null;
        } else {
            Date beginningOfTheDay = getBeginOfTheDay(date);
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            calendar.setTimeInMillis(beginningOfTheDay.getTime() + 86400000L - 1L);
            return calendar.getTime();
        }
    }

    public static int minusWithDay(Date firstDate, Date anotherDate) {
        long first = truncate(firstDate, 5).getTime();
        long another = truncate(anotherDate, 5).getTime();
        return (int) ((first - another) / 86400000L);
    }

    public static boolean hasBetweenDate(String begin, String end, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date beginDate = sdf.parse(begin);
            Date endDate = sdf.parse(end);
            Date currentDate = new Date();
            Date current = sdf.parse(sdf.format(currentDate));
            return current.before(endDate) && current.after(beginDate);
        } catch (ParseException var8) {
            var8.printStackTrace();
            return false;
        }
    }

    public static Date setMonthFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        date = calendar.getTime();
        return date;
    }

    public static Date setMonthLastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, 1);
        calendar.set(5, 1);
        calendar.add(5, -1);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        date = calendar.getTime();
        return date;
    }

    public static Date setMonth(Date date, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, month.intValue());
        date = calendar.getTime();
        return date;
    }

    public static Date setYear(Date date, Integer year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(1, year.intValue());
        date = calendar.getTime();
        return date;
    }

    public static Date setDate(Date date, Integer num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, num.intValue());
        date = calendar.getTime();
        return date;
    }

    public static Date setHour(Date date, Integer num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(11, num.intValue());
        date = calendar.getTime();
        return date;
    }

    public static Date setMinute(Date date, Integer num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(12, num.intValue());
        date = calendar.getTime();
        return date;
    }

    public static int getMonthDiff(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        int y1 = c1.get(1);
        int m1 = c1.get(2);
        int y2 = c2.get(1);
        int m2 = c2.get(2);
        return Math.abs((y1 - y2) * 12 + (m1 - m2));
    }

    public static int getDaysDiff(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        Long between_days = (c2.getTimeInMillis() - c1.getTimeInMillis()) / 86400000L;
        return Math.abs(between_days.intValue());
    }

    private static SimpleDateFormat getDateFormat(String format) {
        return new SimpleDateFormat(format);
    }
}
