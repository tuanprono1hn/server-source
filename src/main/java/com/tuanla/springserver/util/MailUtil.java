package com.tuanla.springserver.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MailUtil {
    private static String host = "smtp.gmail.com";
    private static String port = "465";
    private static String username = "tuanprono1hn@gmail.com";
    private static String appPwd = "stwnwpufafjphfwo";
    private static String toMail = "a6quocoai@gmail.com";
    private static String imgPath = "C:\\Users\\tuanp\\OneDrive\\Máy tính\\sc\\a.jpg";


    public static void main(String[] args) throws Exception {
        //send("a6quocoai@gmail.com");
        send(toMail, "E-mail with inline images", "ok", imgPath);
    }

    public static void send(String toMail) throws Exception {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", port);
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, appPwd);
                    }
                });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(toMail)
        );
        message.setSubject("Testing Gmail SSL");
        message.setText("Dear Mail Crawler,"
                + "\n\n Please do not spam my email!");

        Transport.send(message);
    }

    public static void send(String toMail, String subject, String strMsg) throws Exception {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", port);
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, appPwd);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(toMail));
        message.setSubject(subject);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(strMsg, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }

    public static void send(String toMail, String subject, String strMsg, String imgPath) throws Exception {
        // message info
        StringBuffer body
                = new StringBuffer("<html>This message contains two inline images.<br>");
        body.append("Hello world:<br>");
        body.append("<img src=\"cid:image1\" width=\"30%\" height=\"30%\" /><br>");
        body.append("End of message.");
        body.append("</html>");

        // inline images
        Map<String, String> inlineImages = new HashMap<String, String>();
        inlineImages.put("image1", imgPath);

        EmbeddedImageEmailUtil.send(host, port, username, appPwd, toMail, subject, strMsg, body.toString(), inlineImages);
    }
}
