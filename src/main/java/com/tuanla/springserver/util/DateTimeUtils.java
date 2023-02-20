package com.tuanla.springserver.util;

import net.time4j.PlainDate;
import net.time4j.calendar.ChineseCalendar;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.client.utils.DateUtils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {
    public static Timestamp convertDateToTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }
    public static String[] DATE_PATTERN = {"yyyyMMddHHmmss", "dd/MM/yyyy HH:mm:ss", "dd/MM/yyyy", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss"};
    //public static String DATE_PATTERN = "yyyy-MM-dd";

    public static void main(String[] args) throws Exception {
        System.out.println(DateFormatUtils.formatUTC(DateUtils.parseDate("20221212121212", DATE_PATTERN), "yyyy/MM/dd hh:mm:ss"));
        getLunar();

//        Date cr = new Date();
//        Date someDate = DateUtils.addMonths(cr, -1);
//        System.out.println("NOW :: " + org.apache.http.client.utils.DateUtils.formatDate(cr, DATE_PATTERN));
//        System.out.println("SOME :: " + org.apache.http.client.utils.DateUtils.formatDate(someDate, DATE_PATTERN));
//
//        System.out.println("FOW :: " + org.apache.http.client.utils.DateUtils.formatDate(firstDoW(someDate), DATE_PATTERN));
//        System.out.println("LOW :: " + org.apache.http.client.utils.DateUtils.formatDate(lastDoW(someDate), DATE_PATTERN));
//        System.out.println("FOM :: " + org.apache.http.client.utils.DateUtils.formatDate(firstDoM(someDate), DATE_PATTERN));
//        System.out.println("LOW :: " + org.apache.http.client.utils.DateUtils.formatDate(lastDoM(someDate), DATE_PATTERN));
    }

    public static Date firstDoW(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        return calendar.getTime();
    }

    public static Date lastDoW(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, 7);
        return calendar.getTime();
    }

    public static Date firstDoM(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date lastDoM(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DATE));
        return calendar.getTime();
    }

    public static ChineseCalendar getLunarDate() {
        PlainDate gregorian = PlainDate.nowInSystemTime();
        ChineseCalendar cc = gregorian.transform(ChineseCalendar.axis());
        return cc;
    }

    public static String getLunar() {
        ChineseCalendar chineseCalendar = getLunarDate();
        String strOriginDate = chineseCalendar.toString();
        String strSubDate = strOriginDate.substring(strOriginDate.lastIndexOf("-") + 1).substring(0, 2);
        String month = chineseCalendar.getMonth().toString();
        int year = chineseCalendar.getYear().getNumber();
        System.out.println("Lunar calendar:\t" + strSubDate + "-" + month);
        return "Lunar calendar:\t" + strSubDate + "-" + month;
    }
}
