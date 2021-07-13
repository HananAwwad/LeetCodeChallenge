package com.hanan.Challenge2021;

import java.util.*;

public class July {

    public static void main(String[] aa) {

        //    System.out.println(new July().matrixReshape(new int[][]{{1, 2}, {3, 4}}, 1, 4));
        //System.out.println(new July().minSetSize(new int[]{3, 3, 3, 3, 5, 5, 5, 2, 2, 7}));
        //System.out.println(new July().lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println(new July().isIsomorphic("paper", "title"));
    }

    private String transformString(String s) {
        Map<Character, Integer> indexMapping = new HashMap<>();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < s.length(); ++i) {
            char c1 = s.charAt(i);

            if (!indexMapping.containsKey(c1)) {
                indexMapping.put(c1, i);
            }

            builder.append(Integer.toString(indexMapping.get(c1)));
        }
        return builder.toString();
    }

    public boolean isIsomorphic(String s, String t) {
        return transformString(s).equals(transformString(t));
    }

    public boolean isIsomorphic1(String s, String t) {

        int n = s.length(), i = 0;
        if (n != t.length())
            return false;

        Map<Character, Character> sMap = new HashMap();
        Map<Character, Character> tMap = new HashMap();

        while (i < n) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if (sMap.containsKey(sChar) != tMap.containsKey(tChar))
                return false;

            if (sMap.containsKey(sChar)) {
                if (sMap.get(sChar) != tChar || tMap.get(tChar) != sChar)
                    return false;
            } else {
                sMap.put(sChar, tChar);
                tMap.put(tChar, sChar);
            }

            i++;
        }

        return i == n;
    }

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int omax = 1; //overall max

        for (int i = 1; i < dp.length; i++) {
            int lmax = 0; // loop max
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[j] > lmax) {
                    lmax = dp[j];
                }
            }

            dp[i] = lmax + 1;

            if (dp[i] > omax) {
                omax = dp[i];
            }
        }

        return omax;
    }

    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue priorityQueue = new PriorityQueue();

        for (int i = 0; i < matrix.length; i++) {
            //    for (n)
        }
        return 0;

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
