package com.hanan.june;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JuneSolution {

    public static void main(String[] aa) {
//        int[] a = new int[]{4, 2, 7, 1, 3, 6, 9};
//        for (int i = 0; i < a.length; i++) {
//
//        }
//        char[] s = new char[]{'h','e','l','l','o'};
//        new JuneSolution().reverseString(s);
//        System.out.println(s);

        // System.out.println(new JuneSolution().twoCitySchedCost(new int[][]{{10, 20}, {30, 200}, {400, 50}, {30, 20}}));
        // System.out.println(new JuneSolution().reconstructQueue(new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}}));
        //System.out.println(new JuneSolution().change(5, new int[]{1, 2, 3}));
        ///System.out.println(new JuneSolution().isPowerOfTwo(128));
        // System.out.println(new JuneSolution().isSubsequence("axc", "ahbgdc"));
        // System.out.println(new JuneSolution().searchInsert(new int[]{1, 3, 5, 6}, 0));
        //  System.out.println(new JuneSolution().sortColors(new int[]{2,0,2,1,1,0}));

        String s = "afcbedg";

        System.out.print(longest_special_subseq(s,7, 2));
    }
    static int longest_special_subseq(String str , int n, int k){
        int[] dp = new int[n];
        int[] max_length = new int[26];
        for (int i = 0; i < n ; i++){

            int curr = str.charAt(i) - 'a';
            int lower = Math.max(0, curr - k );
            int uppper = Math.min(25, curr + k );

            for (int j = lower; j < uppper +1 ; j++){
                dp[i] = Math.max( dp[i] , max_length[j] + 1);
            }

            max_length[curr] = Math.max(dp[i], max_length[curr]);
        }
        int ans = 0;
        for(int i : dp) ans = Math.min(i,ans);
        return ans;
    }
    static int longest_special_subseq1(String str , int n, int k){
        // Creating a list with
        // all 0's of size
        // equal to the length of String
        int []dp = new int[n];

        // Supporting list with
        // all 0's of size 26 since
        // the given String consists
        // of only lower case alphabets
        int []max_length = new int[26];

        for (int i = 0; i < n; i++)
        {

            // Converting the ascii value to
            // list indices
            int curr = str.charAt(i) - 'a';

            // Determining the lower bound
            int lower = Math.max(0, curr - k);

            // Determining the upper bound
            int upper = Math.min(25, curr + k);

            // Filling the dp array with values
            for (int j = lower; j < upper + 1; j++)
            {
                dp[i] = Math.max(dp[i], max_length[j] + 1);
            }

            // Filling the max_length array with max
            // length of subsequence till now
            max_length[curr] = Math.max(dp[i], max_length[curr]);
        }

        int ans = 0;

        for(int i:dp) ans = Math.max(i, ans);

        // return the max length of subsequence
        return ans;
    }

    public void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, mid, low);
                mid++;
                low++;
            } else if (nums[mid] == 1) {
                mid++;
            } else if (nums[mid] == 2) {
                swap(nums, mid, high);
                high--;
            }
        }

    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public int[] sortColors1(int[] nums) {
        int zeros = 0;
        int ones = 0;
        int twos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                zeros++;
            else if (nums[i] == 1)
                ones++;
            else if (nums[i] == 2)
                twos++;
        }
        int i = 0;
        while (i < nums.length) {
            if (zeros != 0) {
                nums[i] = 0;
                zeros--;
            } else if (ones != 0) {
                nums[i] = 1;
                ones--;
            } else if (twos != 0) {
                nums[i] = 2;
                twos--;
            }
            i++;
        }
        return nums;
    }


    public int searchInsert(int[] nums, int target) {
        int i = 0;
        for (; i < nums.length; i++) {
            if (target <= nums[i])
                return i;
        }
        return i;
    }

    public boolean isSubsequence(String s, String t) {
        int i = 0;
        int j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }

        return i == s.length();
    }

    public boolean isPowerOfTwo(int n) {
        if (n < 0) return false;
        while (n % 2 == 0) n /= 2;
        return n == 1;
    }

    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 1;
        for (int j = 1; j <= coins.length; j++) {
            dp[j][0] = 1; // number ways of selecting for amount zero is 1
            for (int i = 1; i <= amount; i++) {
                dp[j][i] = dp[j - 1][i]; // exclude current coin
                if (i - coins[j - 1] >= 0) { // check if amount  >= to the current i`th coin
                    dp[j][i] += dp[j][i - coins[j - 1]]; // include current coin
                }
            }
        }
        return dp[coins.length][amount];
    }

    public int change1(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int[] p : dp) {
            Arrays.fill(p, -1);
        }
        return cc(coins, 0, amount, dp);
    }

    int cc(int[] coins, int i, int amount, int[][] dp) {
        if (amount == 0) return 1;
        if (amount < 0 || i == coins.length) return 0;
        if (dp[i][amount] != -1)
            return dp[i][amount];
        return dp[i][amount] = (cc(coins, i, amount - coins[i], dp) + cc(coins, i + 1, amount, dp));
    }

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]));
        List<int[]> res = new ArrayList<>();

        for (int[] p : people) {
            res.add(p[1], p);
        }
        int n = people.length;
        return res.toArray(new int[n][2]);
    }


    public int twoCitySchedCost(int[][] costs) {
        int N = costs.length / 2;
        int[] refund = new int[N * 2];
        int minCost = 0, index = 0;
        for (int[] cost : costs) {
            refund[index++] = cost[1] - cost[0];
            minCost += cost[0];
        }
        Arrays.sort(refund);
        for (int i = 0; i < N; i++) {
            minCost += refund[i];
        }
        return minCost;
    }

    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left++] = s[right];
            s[right--] = tmp;
        }

    }

    public void deleteNode(ListNode node) {

        node.val = node.next.val;
        node.next = node.next.next;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return root;
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.right = left;
        right.left = right;
        return root;
    }


}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
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

