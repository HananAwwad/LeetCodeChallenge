package com.hanan;

import java.util.ArrayList;
import java.util.List;

public class DecChallenge {

    public static void main(String[] aa) {
        //  System.out.println(new DecChallenge().partition(""));
        //  System.out.println(new DecChallenge().sortedSquares(new int[]{-4, -1, 0, 3, 10}));
        //  System.out.println(new DecChallenge().isValidBST(new TreeNode(5)));
        //System.out.println(new DecChallenge().decodeAtIndex("leet2code3", 15));
        System.out.println(new DecChallenge().nextGreaterElement(21));
    }

    public int nextGreaterElement(int n) {
        char[] str = ("" + n).toCharArray();
        int deflectionPoint = str.length - 1;

        while (deflectionPoint > 0) {
            if (str[deflectionPoint] > str[deflectionPoint - 1]) {
                break;
            }
            deflectionPoint--;
        }

        if (deflectionPoint == 0) {
            return -1;
        }
        int firstSwappingIndex = deflectionPoint - 1;

        int secondSwappingIndex = str.length - 1;
        while(secondSwappingIndex>=firstSwappingIndex){
            if(str[firstSwappingIndex] < str[secondSwappingIndex]) {
                break;
            }
            secondSwappingIndex--;
        }

        //swap
        char temp = str[firstSwappingIndex];
        str[firstSwappingIndex] = str[secondSwappingIndex];
        str[secondSwappingIndex] = temp;

        // swapping at the point of deflection
        reverse(str, deflectionPoint);
        Long no = Long.parseLong(new String(str));

        if (no <= Integer.MAX_VALUE)
            return no.intValue();
        else
            return -1;
    }

    private void reverse(char[] str, int i) {
        int start = i;
        int end = str.length - 1;
        while (end >= start) {
            char temp = str[start];
            str[start] = str[end];
            str[end] = temp;
            start++;
            end--;

        }
    }

    public String decodeAtIndex(String S, int K) {

        long size = 0;
        int N = S.length();

        // Find size = length of decoded string
        for (int i = 0; i < N; ++i) {
            char c = S.charAt(i);
            if (Character.isDigit(c))
                size *= c - '0';
            else
                size++;
        }
        for (int i = N - 1; i >= 0; --i) {
            char c = S.charAt(i);
            K %= size;
            if (K == 0 && Character.isLetter(c))
                return Character.toString(c);

            if (Character.isDigit(c))
                size /= c - '0';
            else
                size--;
        }

        throw null;
    }

    public boolean isValidBST(TreeNode root) {

        return validate(root, null, null);
    }

    public boolean validate(TreeNode node, Integer low, Integer high) {

        if (node == null)
            return true;

        if ((low != null && node.val < low) || (high != null && node.val > high))
            return false;
        return validate(node.left, low, node.val) && validate(node.right, node.val, high);

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
