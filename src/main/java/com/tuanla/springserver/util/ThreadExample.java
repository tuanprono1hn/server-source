package com.tuanla.springserver.util;

public class ThreadExample extends Thread {
    int i = 0;

    ThreadExample(int j) {
        this.i = j;
    }

    public void run() {
        for (int j = 1; j <= this.i; j++)
            System.out.println("Hello " + this.i + "/" + j);
    }

    public static void main(String[] args) {
        ThreadExample obj = new ThreadExample(10);
        ThreadExample obj2 = new ThreadExample(200);
        ThreadExample obj3 = new ThreadExample(300);
        obj.start();
        obj2.start();
        obj3.start();
        obj.stop();
    }
}
