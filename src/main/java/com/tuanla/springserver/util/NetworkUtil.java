package com.tuanla.springserver.util;

import java.net.NetworkInterface;
import java.util.Enumeration;

public class NetworkUtil {
    public static void main(String[] args) throws Exception {
        System.out.println(getMacAddress());
    }

    public static String getMacAddress() throws Exception {
        String macAddress = "";
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface ni = networkInterfaces.nextElement();
                byte[] hardwareAddress = ni.getHardwareAddress();
                if (hardwareAddress != null) {
                    String[] hexadecimalFormat = new String[hardwareAddress.length];
                    for (int i = 0; i < hardwareAddress.length; i++) {
                        hexadecimalFormat[i] = String.format("%02X", hardwareAddress[i]);
                    }
                    return String.join(":", hexadecimalFormat);
                }
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return macAddress;
    }
}
