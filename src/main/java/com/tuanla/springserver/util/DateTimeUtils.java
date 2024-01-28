package com.tuanla.springserver.util;

import net.time4j.PlainDate;
import net.time4j.calendar.ChineseCalendar;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.client.utils.DateUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeUtils {
    public static String DATE_PATTERN = "yyyy-MM-dd";
    public static TimeZone time_zone = TimeZone.getTimeZone("Asia/Vietnam");

    public static Timestamp convertDateToTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(DateUtils.formatDate(convertTimeZone(firstDoW(new Date())), ConstantVariable.DATE_PATTERN[4]));
//        System.out.println(convertTimeZone(firstDoW(new Date())));

//        System.out.println(DateFormatUtils.formatUTC(DateUtils.parseDate("20221212121212", ConstantVariable.DATE_PATTERN), "yyyy/MM/dd hh:mm:ss"));
        getLunar(2024, 2, 10);

//        Date cr = new Date();
//        Date someDate = org.apache.commons.lang3.time.DateUtils.addMonths(cr, 0);
//        System.out.println("NOW :: " + DateUtils.formatDate(cr, ConstantVariable.DATE_PATTERN[4]));
//        System.out.println("SOME :: " + DateUtils.formatDate(someDate, ConstantVariable.DATE_PATTERN[4]));
//        System.out.println("FOW :: " + convertTimeZone(firstDoW(someDate)));
//        System.out.println("LOW :: " + DateUtils.formatDate(lastDoW(someDate), ConstantVariable.DATE_PATTERN[4]));
//        System.out.println("FOM :: " + DateUtils.formatDate(firstDoM(someDate), ConstantVariable.DATE_PATTERN[4]));
//        System.out.println("LOW :: " + DateUtils.formatDate(lastDoM(someDate), ConstantVariable.DATE_PATTERN[4]));
    }

    public static Date convertTimeZone(Date date) throws Exception {
        SimpleDateFormat isoFormat = new SimpleDateFormat(ConstantVariable.DATE_PATTERN[4]);
        isoFormat.setTimeZone(time_zone);
        return isoFormat.parse(isoFormat.format(date));
    }

    public static Date firstDoW(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(time_zone);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        return calendar.getTime();
    }

    public static Date lastDoW(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(time_zone);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, 7);
        return calendar.getTime();
    }

    public static Date firstDoM(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(time_zone);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date lastDoM(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(time_zone);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DATE));
        return calendar.getTime();
    }

    public static ChineseCalendar getLunarDateToday() {
        PlainDate gregorian = PlainDate.nowInSystemTime();
        ChineseCalendar cc = gregorian.transform(ChineseCalendar.axis());
        return cc;
    }

    public static ChineseCalendar getLunarDate(int year, int month, int dayOfMonth) {
        PlainDate gregorian = PlainDate.of(year, month, dayOfMonth);
        ChineseCalendar cc = gregorian.transform(ChineseCalendar.axis());
        return cc;
    }

    public static String getLunarToday() {
        ChineseCalendar chineseCalendar = getLunarDateToday();
        String strOriginDate = chineseCalendar.toString();
        String strSubDate = strOriginDate.substring(strOriginDate.lastIndexOf("-") + 1).substring(0, 2);
        String month = chineseCalendar.getMonth().toString();
        int year = chineseCalendar.getYear().getNumber();
        System.out.println("Lunar calendar:\t" + strSubDate.replaceFirst("^0+(?!$)", "") + "-" + month.replaceFirst("^0+(?!$)", ""));
        return "Lunar calendar:\t Date " + strSubDate.replaceFirst("^0+(?!$)", "") + "- Month " + month.replaceFirst("^0+(?!$)", "");
    }

    public static String getLunar(int y, int m, int d) {
        ChineseCalendar chineseCalendar = getLunarDate(y, m, d);
        String strOriginDate = chineseCalendar.toString();
        String strSubDate = strOriginDate.substring(strOriginDate.lastIndexOf("-") + 1).substring(0, 2);
        String month = chineseCalendar.getMonth().toString();
        int year = chineseCalendar.getYear().getNumber();
        System.out.println("Lunar calendar:\t" + strSubDate.replaceFirst("^0+(?!$)", "") + "-" + month.replaceFirst("^0+(?!$)", ""));
        return "Lunar calendar:\t Date " + strSubDate.replaceFirst("^0+(?!$)", "") + "- Month " + month.replaceFirst("^0+(?!$)", "");
    }
}
