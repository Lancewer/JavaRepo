package com.lancewer.usefulAlgorithm;

/**
 * Created with IntelliJ IDEA.
 * User: Lancewer
 * Date: 12-12-26
 * Time: 下午3:36
 */
public class BetterMultiply {
    public static void main(String[] args){
        int m = 1000;
        long n = 999;
        int nLen= String.valueOf(n).length();
        long sum = 0;
        for(int digit = nLen; digit > 0; digit --){
            long t = (long) Math.pow(10, (digit - 1));
            sum  += m * (n / t * t);
            n = n - (n / t * t);
        }

        System.out.println(sum);
        System.out.println(Math.ceil(-3.4));
    }
}
