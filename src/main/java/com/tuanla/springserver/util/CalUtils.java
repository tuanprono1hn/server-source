package com.tuanla.springserver.util;

public class CalUtils {
    public static Float removeZero(Long longValue1, Long longValue2) {
        if (longValue2 == null || longValue2 == 0) {
            return 0f;
        }
        return (float) longValue1 * 100 / longValue2;
    }
}
