package com.tuanla.springserver.util;

public class ConstantVariable {
    public static final String URl = "localhost:8080";
    public static final String PATTERN_ISDN = "^(?=.{9,10}$)([0-9])+$";
    public static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PATTERN_USERNAME = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){0,50}[a-zA-Z0-9]$";
    public static final String PATTERN_IP = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
}
