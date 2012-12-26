package com.lancewer.recursive;

/**
 * Created with IntelliJ IDEA.
 * User: Lancewer
 * Date: 12-12-26
 * Time: 下午10:54
 * Description: This example demonstrate 2 methods to handle x exponential n with recursive.
 * comment: this 2 examples purpose is to show the recursive method not the calculate the exponential,
 * in java you can easily use Math.pow method to calculate
 */
public class XexpN {
    /**
     * Basic thoughts: x^n = x * x ^ (n -1)
     * This method's time efficiency is O(n)
     */
    public static long method1(int x, int n) {
        if (n == 0) {
            return 1;
        } else {
            return x * method1(x, n - 1);
        }
    }

    /**
     * Basic thoughts:
     * x^n = (x ^ (n/2)) ^ 2 when x is even
     * x^n = x(x ^ ((n-1)/2)) ^ 2 when x is odd
     * x ^ 0 = 1
     * This method's time efficiency is O(log n)
     * so it's better than method 1
     */
    public static double method2(int x, int n) {
        if (n == 0) {
            return 1;
        }

        /*whether n is even or odd the result of n/2 is int
        so, you don't have to check the n is even or odd
         */
        double result = Math.pow(method2(x, n / 2), 2);

        /*
        If n id odd, you need multi x at the end
         */
        if (n % 2 == 1) {
            result *= x;
        }

        return result;
    }


    public static void main(String[] args) {
        int x = 10;
        int n = 5;

        System.out.println(method1(x, n));
        System.out.println(method2(x, n));
        System.out.println(Math.pow(x, n));
    }
}
