package com.hanan.Challenge2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class July {

    public static void main(String[] aa) {

        //    System.out.println(new July().matrixReshape(new int[][]{{1, 2}, {3, 4}}, 1, 4));
        System.out.println(new July().minSetSize(new int[]{3, 3, 3, 3, 5, 5, 5, 2, 2, 7}));
    }

    public int minSetSize(int[] arr) {
        int n = arr.length;
        HashMap<Integer, Integer> cnt = new HashMap<>();
        for (int x : arr) cnt.put(x, cnt.getOrDefault(x, 0) + 1);

        int[] bucket = new int[n + 1];
        for (int freq : cnt.values()) ++bucket[freq];

        int ans = 0, removed = 0, half = n / 2, freq = n;
        while (removed < half) {
            ans += 1;
            while (bucket[freq] == 0) --freq;
            removed += freq;
            --bucket[freq];
        }
        return ans;
    }

    public int minSetSize1(int[] arr) {

        HashMap<Integer, Integer> map = new HashMap();
        for (int x : arr) map.put(x, (int) map.getOrDefault(x, 0) + 1);

        int[] frequencies = new int[map.values().size()];
        int i = 0;
        for (int freq : map.values()) frequencies[i++] = freq;
        Arrays.sort(frequencies);

        int ans = 0, removed = 0, half = arr.length / 2;
        i = frequencies.length - 1;
        while (removed < half) {
            ans += 1;
            removed += frequencies[i--];
        }
        return ans;

    }

    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int[][] result = new int[r][c];
        int matRow = 0;
        int matCol = 0;
        int originalRowNum = mat.length;
        int originalColNum = mat[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (matCol > originalColNum - 1) {
                    matCol = 0;
                    matRow = matRow + 1;
                }
                result[i][j] = mat[matRow][matCol];
                matCol++;
                if ((matRow > originalRowNum - 1) && (matCol > originalColNum - 1)) {
                    break;
                }
            }
            if ((matRow > originalRowNum - 1) && (matCol > originalColNum - 1)) {
                break;
            }
        }
        return result;
    }

    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        int min = 0;
        double max = Math.pow(2, n) - 1;
        return result;
    }
}
