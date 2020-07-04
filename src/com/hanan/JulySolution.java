package com.hanan;

public class JulySolution {
    public static void main(String[] aa) {
        System.out.println(new JulySolution().arrangeCoins(5));
    }

    public int arrangeCoins(int n) {
        int numberOfStaircase = 0;
        int currentN = n;
        for (int i = 1; i <= n; i++) {
            numberOfStaircase++;
            if (currentN < i)
                return i - 1;
            currentN = currentN - i;

        }
        return numberOfStaircase;
    }
}
