package com.tuanla.springserver.util;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.telnet.TelnetClient;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoteUtils {
    public static String SERVER_IP = "10.54.144.195";
    public static String USER_NAME = "app";
    public static String USER_PWD = "app12345";
    public static Integer CLIENT_CONNECT_RETRY_LIMIT = 1;
    public static String IPADDRESS_PATTERN =
            "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
//    private static final String IPADDRESS_PATTERN = "([01]?\\d\\d?|2[0-4]\\d|25[0-5])"
//            + "\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])"
//            + "\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])"
//            + "\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])";

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String cmd = "cat /etc/hosts";
        Integer port = 8222;
        executeSSHCommand(USER_NAME, USER_PWD, SERVER_IP, port, cmd);

//        String strHosts = "10.38.31.97 10.38.31.98 10.38.31.99 10.38.31.100";
//        String strPorts = "20, 21, 8020, 749, 88  6667, 2181,8001-8010";
//        System.out.println(multipleTelnetCheck("app", "app12345", strHosts, strPorts));
    }

    public static String multipleTelnetCheck(String user, String password, String strHosts, String strPorts) throws Exception {
        String response = "";
        List<String> lstHost = new ArrayList<>();
        List<Integer> lstPort = new ArrayList<>();

        Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
        Matcher matcher = pattern.matcher(strHosts);
        while (matcher.find()) {
            lstHost.add(matcher.group());
        }

        String[] arrPort = org.apache.commons.lang3.StringUtils.split(strPorts, ";: ,");
//        String[] arrPort = strPorts.split("[;: ,]");
        for (String strPort : arrPort) {
            if (strPort.contains("-")) {
                int start = Integer.parseInt(strPort.substring(0, strPort.indexOf("-")));
                int stop = Integer.parseInt(strPort.substring(strPort.indexOf("-") + 1));
                if (start > stop) {
                    int trans = stop;
                    start = stop;
                    stop = trans;
                }
                for (int port = start; port <= stop; port++) {
                    lstPort.add(port);
                }
            } else {
                lstPort.add(Integer.parseInt(strPort));
            }
        }
        for (String host : lstHost) {
            for (Integer port : lstPort) {
                try {
                    response = response + "\ntelnet " + host + " " + port;
                    response = response + " " + telnetClient(host, port);
                } catch (Exception e) {
                    response = response + " " + e.getMessage();
                }
            }
        }
        return response;
    }

    public static String telnetCheck(String user, String password, String host, int port) {
//        Channel channel = null;
        Session session = null;
        String command1 = "telnet " + host + " " + port;
        String command2 = "how are you";
        String log = "";
        try {
            ByteArrayInputStream stream = new ByteArrayInputStream(command2.getBytes(StandardCharsets.UTF_8));
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            log = "Connected";

//            channel = session.openChannel("exec");
//            ((ChannelExec) channel).setCommand(command1);
//
//            channel.setInputStream(null);
//            ((ChannelExec) channel).setErrStream(System.err);
//            channel.connect();
//            channel.setInputStream(stream);
//            InputStream in = channel.getInputStream();
//
//            byte[] tmp = new byte[1024];
//            while (true) {
//                while (in.available() > 0) {
//                    int i = in.read(tmp, 0, 1);
//                    if (i < 0) break;
//                    System.out.print(new String(tmp, 0, i));
//                }
//
//                if (channel.isClosed()) {
//                    System.out.println("exit-status: " + channel.getExitStatus());
//                    break;
//                }
//                try {
//                    Thread.sleep(1000);
//                } catch (Exception ee) {
//                }
//            }
        } catch (Exception e) {
            if (e.getMessage().toLowerCase(Locale.ROOT).contains("connection refused")) {
                log = "Connection refused";
            }
            if (e.getMessage().toLowerCase(Locale.ROOT).contains("timeout")) {
                log = "Connection timeout";
            } else if (e.getMessage().toLowerCase(Locale.ROOT).contains("connection")) {
                log = e.getMessage().substring(e.getMessage().toLowerCase(Locale.ROOT).indexOf("connection"));
            } else {
                log = e.getMessage();
            }
        } finally {
//            channel.disconnect();
            session.disconnect();
            return StringUtils.toSentenceCase(log);
        }
    }

    public static void executeSSHCommand(String username, String password, String host, int port, String command) throws Exception {

        Scanner sc = new Scanner(System.in);
        Session session = null;
        ChannelExec channel = null;
        String command2 = "how are you today";

        try {
            System.out.println(new Date() + "\tConnecting... " + host);
            session = new JSch().getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            System.out.println("Connected!");

            while (session.isConnected()) {
                System.out.println(new Date() + "\tExecuting command: " + command);
                channel = (ChannelExec) session.openChannel("exec");
                channel.setCommand(command);
                ByteArrayInputStream byteArrayInputStreams = new ByteArrayInputStream(command2.getBytes(StandardCharsets.UTF_8));
                channel.setInputStream(byteArrayInputStreams);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                channel.setOutputStream(byteArrayOutputStream);
                channel.connect();

                InputStream in = channel.getInputStream();
                byte[] tmp = new byte[1024];
                while (true) {
                    while (in.available() > 0) {
                        int i = in.read(tmp, 0, 1);
                        if (i < 0) break;
                        System.out.print(new String(tmp, 0, i));
                    }

                    if (channel.isClosed()) {
                        System.out.println("exit-status: " + channel.getExitStatus());
                        break;
                    }
                    String responseString = new String(byteArrayOutputStream.toByteArray());
                    System.out.println(responseString);
                    try {
                        Thread.sleep(1000);
                    } catch (Exception ee) {
                        ee.printStackTrace();
                    }
                }

//            while (channel.isConnected()) {
//                Thread.sleep(100);
//            }

                String responseString = new String(byteArrayOutputStream.toByteArray());
                System.out.println(responseString);

                System.out.println("Enter command: ");
                command = sc.nextLine();
            }
        } finally {
            if (session != null) {
                session.disconnect();
            }
            if (channel != null) {
                channel.disconnect();
            }
        }
    }

    private static String telnetSocket(String serverIp, int port) throws Exception {
        String response = "";
        Socket pingSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            pingSocket = new Socket(serverIp, port);
            out = new PrintWriter(pingSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
        } catch (IOException e) {
            return e.getMessage();
        }

        out.println("telnet");
        response = in.readLine();
        out.close();
        in.close();
        pingSocket.close();

        if (response.toLowerCase(Locale.ROOT).contains("connection refused")) {
            return "Connection refused ";
        } else if (response.toLowerCase(Locale.ROOT).contains("http")) {
            return "Connection closed ";
        } else if (response.toLowerCase(Locale.ROOT).contains("ssh")) {
            return "Connected ";
        } else {
            return "Connection error " + response;
        }
    }

    private static String telnetClient(String ip, Integer port) throws Exception {
        TelnetClient client = null;
        try {
            client = new TelnetClient();
            client.setConnectTimeout(15);
            client.connect(ip, port);
            if (client.isAvailable()) {
                if (client.isConnected()) {
                    return "Connected";
                } else return "Not connected";
            } else return "Not available";
        } catch (Exception e) {
            //throw new RuntimeException("Unable connect to : " + ip + ":" + port);
            return StringUtils.toSentenceCase(e.getMessage());
        } finally {
            try {
                if (client != null) {
                    client.disconnect();
                }
            } catch (Exception ignore) {
            }
        }
    }
}
