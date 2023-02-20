package com.tuanla.springserver.util;

import org.apache.commons.lang3.StringUtils;

public class IsdnUtils {
    public static String removePrefix(String phoneNumber) throws Exception {
        String first0 = "";
        String first84 = "";
        if (phoneNumber.length() == 9) return phoneNumber;
        first0 = StringUtils.substring(phoneNumber, 0, 1);
        first84 = StringUtils.substring(phoneNumber, 0, 2);
        if (first0.equals("0")) {
            return StringUtils.substring(phoneNumber, 1);
        } else if (first84.equals("84")) {
            return StringUtils.substring(phoneNumber, 2);
        } else {
            return phoneNumber;
        }
    }

    public static String addPrefix84(String phoneNumber) {
        String first0 = "";
        String first84 = "";
        first0 = StringUtils.substring(phoneNumber, 0, 1);
        first84 = StringUtils.substring(phoneNumber, 0, 2);
        if (first0.equals("0")) {
            return "84" + StringUtils.substring(phoneNumber, 1);
        } else if (first84.equals("84")) {
            if (phoneNumber.length() == 9) return "84" + phoneNumber;
            return phoneNumber;
        } else {
            return "84" + phoneNumber;
        }
    }
}
