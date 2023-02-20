package com.tuanla.springserver.util;

import net.time4j.PlainDate;
import net.time4j.calendar.ChineseCalendar;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

public class TimeUtil {
    public static Timestamp convertDateToTimestamp(Date date) {
        return new Timestamp(date.getTime());
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
        return "Lunar calendar:\t" + strSubDate + "-" + month + "-" + year;
    }

//    public static void main(String[] args) {
//        getLunar();
//    }


//    public static void main(String[] args) {
//        final String fromEmail = "tuanprono1hn@gmail.com"; //requires valid gmail id
//        final String password = "linkinpig8494"; // correct password for gmail id
//        final String toEmail = "a6quocoai@gmail.com"; // can be any email id
//
//        System.out.println("TLSEmail Start");
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
//        //props.put("mail.smtp.port", "587"); //TLS Port
//        props.put("mail.smtp.auth", "true"); //enable authentication
//        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
//
//        //create Authenticator object to pass in Session.getInstance argument
//        Authenticator auth = new Authenticator() {
//            //override the getPasswordAuthentication method
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(fromEmail, password);
//            }
//        };
//        Session session = Session.getInstance(props, auth);
//
//        EmailUtil.sendEmail(session, toEmail,"TLSEmail Testing Subject", "TLSEmail Testing Body");
//
//    }

    public static void main(String[] args) {

        final String username = "tuanprono1hn@gmail.com";
        final String password = "linkinpig8494";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("to_username_a@gmail.com, to_username_b@yahoo.com")
            );
            message.setSubject("Testing Gmail SSL");
            message.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
