package com.hanan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution3 {
    public static void main(String[] a) {
        // Your LRUCache object will be instantiated and called as such:
//        LRUCache cache = new LRUCache(2);
//        int param_1 = cache.get(1);
//        cache.put(1, 1);
//        cache.put(2, 2);
//        cache.get(1);       // returns 1
//        cache.put(3, 3);    // evicts key 2
//        cache.get(2);       // returns -1 (not found)
//        cache.put(4, 4);    // evicts key 1
//        cache.get(1);       // returns -1 (not found)
//        cache.get(3);       // returns 3
//        cache.get(4);       // returns 4
        //System.out.println(new Solution3().canJump(new int[]{2, 3, 1, 1, 4}));
        //    System.out.println(new Solution3().longestCommonSubsequence("abcde","ace"));
        new Solution3().maximalSquare(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        });
    }

    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;

        int[] dp = new int[cols + 1];
        int maxsqlen = 0, prev = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return maxsqlen * maxsqlen;

    }

    public int longestCommonSubsequence(String text1, String text2) {


        char[] text1Array = text1.toCharArray();
        char[] text2Array = text2.toCharArray();
        return lcs(text1Array, text2Array, text1Array.length, text2Array.length);
    }

    int lcs(char[] X, char[] Y, int m, int n) {
        int D[][] = new int[m + 1][n + 1];

    /* Following steps build L[m+1][n+1] in bottom up fashion. Note
         that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1] */
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    D[i][j] = 0;
                else if (X[i - 1] == Y[j - 1])
                    D[i][j] = D[i - 1][j - 1] + 1;
                else
                    D[i][j] = max(D[i - 1][j], D[i][j - 1]);
            }
        }
        return D[m][n];
    }

    /* Utility function to get max of 2 integers */
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

}

class LRUCache {


    HashMap<Integer, Integer> map;

    List<Integer> list;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        list = new ArrayList<>(capacity);
        map = new HashMap<>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            int temp = list.indexOf(key);
            list.remove(temp);
            list.add(key);
            return map.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {

        if (list.contains(key)) {
            map.put(key, value);
            int temp = list.indexOf(key);
            list.remove(temp);
            list.add(key);
        } else {
            if (list.size() == capacity) {
                int temp = list.get(0);
                map.remove(temp);
                list.remove(0);
            }
            list.add(key);
            map.put(key, value);
        }
    }
}
