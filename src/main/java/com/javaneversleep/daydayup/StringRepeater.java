package com.javaneversleep.daydayup;

/**
 * YouTube Video Link: https://www.youtube.com/watch?v=urKOqWyKVWs
 * Java Never Sleep Channel: https://www.youtube.com/JavaNeverSleep
 */
public class StringRepeater {

    private static String repeat(String s, int times) {
        String ret = "";
        for (int i = 0; i < times; i++) {
            ret += s;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(repeat("Hello World ", 2));
        System.out.println("Hello World ".repeat(2));
        System.out.println(repeat("Hello", 3));
    }
}
