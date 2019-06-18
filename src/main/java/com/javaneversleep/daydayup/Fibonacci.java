package com.javaneversleep.daydayup;

public class Fibonacci {

    private static int fib(int n) {
        return n <= 2 ? 1 : fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        for (int i = 1; i < 11; i++) {
            System.out.print(fib(i) + " ");
        }
    }
}
