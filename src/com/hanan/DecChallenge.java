package com.hanan;

import java.util.ArrayList;
import java.util.List;

public class DecChallenge {

    public static void main(String[] aa) {
        //  System.out.println(new DecChallenge().partition(""));
        System.out.println(new DecChallenge().sortedSquares(new int[]{-4, -1, 0, 3, 10}));
    }

    public int[] sortedSquares(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        int k = len - 1;
        int start = 0;
        int end = len - 1;
        while (!(start > end)) {
            if (Math.abs(nums[start]) > Math.abs(nums[end])) {
                ans[k] = nums[start] * nums[start];
                start++;
                k--;
            } else {
                ans[k] = nums[end] * nums[end];
                end--;
                k--;
            }
        }
        return ans;
    }

    public List<List<String>> partition(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        List<List<String>> result = new ArrayList<>();
        dfs(result, s, 0, new ArrayList<>(), dp);
        return result;

    }

    void dfs(List<List<String>> result, String s, int start, List<String> currentList, boolean[][] dp) {
        if (start >= s.length())
            result.add(new ArrayList<>(currentList));

        for (int end = start; end < s.length(); end++) {
            if (s.charAt(start) == s.charAt(end) && (end - start <= 2 || dp[start + 1][end - 1])) {
                dp[start][end] = true;
                currentList.add(s.substring(start, end + 1));
                dfs(result, s, end + 1, currentList, dp);
                currentList.remove(currentList.size() - 1);
            }
        }
    }

}
