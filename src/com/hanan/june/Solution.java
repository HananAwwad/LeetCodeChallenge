package com.hanan.june;

public class Solution {
    public static

    int[] w_sum;
    public Solution(int[] w) {
        w_sum = new int[w.length];
        int sum = 0 ;
        for (int i =0 ; i < w.length;i++){
            sum += w[i];
            w_sum[i] = sum;
        }
    }

    public int pickIndex() {

        int randomNo = (int)( w_sum[w_sum.length -1 ] * Math.random());
        for (int i =0 ; i < w_sum.length; i++){
            if (randomNo < w_sum[i])
                return i ;
        }
        return -1;
    }
}
