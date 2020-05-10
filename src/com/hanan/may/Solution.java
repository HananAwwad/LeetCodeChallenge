package com.hanan.may;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

    public static void main(String s[]) {
        // System.out.println(new Solution().firstBadVersion(5));
        // System.out.println(new Solution().findComplement(2));
        // System.out.printf("unique char " + new Solution().firstUniqChar("loveleetcode"));
        // System.out.println(new Solution().majorityElementBetterSol(new int[]{3, 2, 3}));
       // System.out.println(new Solution().checkStraightLine(new int[][]{{-4, -3}, {1, 0}, {3, -1}, {0, -1}, {-5, 2}}));
        System.out.println(new Solution().isPerfectSquare(14));
    }

    public boolean isPerfectSquare(int num) {

        if (num < 2) {
            return true;
        }
        long si = 2, ei = num / 2;
        while (si <= ei) {
            long mid = si + ((ei - si) / 2);
            long guessedNumber = mid * mid;
            if (guessedNumber == num)
                return true;
            else if (guessedNumber > num)
                ei = mid - 1;
            else
                si = mid + 1;


        }
        return false;


    }

    public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates.length == 2)
            return true;
        double slobe = (double) (coordinates[1][1] - coordinates[0][1]) / (coordinates[1][0] - coordinates[0][1]);

        for (int i = 1; i <= coordinates.length - 1; i++) {
            double currentSlobe = (double) (coordinates[i + 1][1] - coordinates[i][1]) / (coordinates[i + 1][0] - coordinates[i][0]);

            if (currentSlobe != slobe)
                return false;
        }

        return true;
    }


    public boolean isCousins(TreeNode root, int x, int y) {

        Pair xPair = getPair(root, x, null, 0);
        Pair yPair = getPair(root, y, null, 0);
        if (xPair.level == yPair.level && xPair.parent == yPair.parent) {
            return true;
        }
        return false;
    }

    public Pair getPair(TreeNode root, int val, TreeNode parent, int level) {
        if (root == null)
            return null;
        if (root.val == val)
            return new Pair(parent, level);

        Pair leftPair = getPair(root.left, val, root, level + 1);
        Pair rightPair = getPair(root.right, val, root, level + 1);

        return leftPair == null ? rightPair : leftPair;

    }

    public int majorityElementBetterSol(int[] nums) {

        int count = 0;
        int candadiate = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                candadiate = nums[i];
            }
            count += (candadiate == nums[i] ? 1 : -1);
        }
        return candadiate;
    }

    public int majorityElement(int[] nums) {
        int majorityElement = -1;
        Map map = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], (int) map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        int largestCount = 0;
        for (Object c : map.keySet()) {
            if ((int) map.get(c) > (nums.length / 2)) {
                if (largestCount < (int) map.get(c)) {
                    majorityElement = (int) c;
                    largestCount = (int) map.get(c);
                }
            }

        }

        return majorityElement;


    }

    public int firstUniqChar(String s) {

        int[] map = new int[26];
        for (char c : s.toCharArray()) {
            map[c - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map[c - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    public int findComplement(int num) {

        int bitlength = ((int) (Math.log(num) / Math.log(2)) + 1);
        int bitMask = (1 << bitlength) - 1;

        return num ^ bitMask;
    }

    public int numJewelsInStones(String J, String S) {
        int res = 0;
        Set<Character> unique = new HashSet<Character>();
        for (char c : J.toCharArray()) {
            unique.add(c);
        }
        for (int i = 0; i < S.toCharArray().length; i++) {
            if (unique.contains(S.toCharArray()[i])) {
                res++;
            }
        }

        return res;
    }

    public int firstBadVersion(int n) {
        int res = -1;
        int left = 1;
        int right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (new VersionControl().isBadVersion(mid)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return res;
    }
}

class VersionControl {
    boolean isBadVersion(int version) {

        if (version == 4) {
            return true;
        }
        return false;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Pair {
    TreeNode parent;
    int level;

    Pair(TreeNode parent, int level) {
        this.parent = parent;
        this.level = level;
    }

}