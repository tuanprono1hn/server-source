package com.tuanla.springserver.util;

public class Test {
    public static boolean checkConditionSomething(int input) {
        return input > 10 ? true : false;
    }

    public static void main(String[] args) {
        System.out.println(checkConditionSomething(5));
    }
}
