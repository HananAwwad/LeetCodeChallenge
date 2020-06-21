package com.hanan;

import java.util.*;
import java.util.stream.Collectors;

public class Interview {


//    Given an array A, where A[i] represents the height of the balloon from the ground.
//    You are shooting arrows from left to right direction, once you shoot an arrow, if it hits the balloon at height x,
//    the trajectory of the arrow decreases by 1.
//    Find the minimum number of arrows required to burst all the balloons?
//        For example
//        A = [6, 5, 1, 4, 5], [6,5,4,3,2,1]... output .. 1
//        Output = 3

    // A= [5,4,4]....[,3,3], []
/// first shot  ... at index 0, 1, 3
// sec shot ..... at index 2
// third shot   at index 4

// int arrowHight = Arrays.sort(A);
//int arrowHight = A[0];
// int ballonWithsameArrowHeight =  A[0];
// while (arrowHight != 0 && )


    public static void main(String[] aa) {
        //System.out.println(new Interview().twoSum(new int[]{2, 7, 11, 15}, 9));
        //System.out.println(new Interview().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        // System.out.println(new Interview().isValidParentheses("([)]"));
        // System.out.println(new Interview().threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        //System.out.println(new Interview().burstBallons(new int[]{0, 4, 4}));
        // System.out.println(new Interview().permute(new int[]{1, 2, 3}));
        //System.out.println(new Interview().permute1(new int[]{1,2,3}));
        System.out.println(new Interview().maxProduct(new int[]{2, -5, -2, -4, 3}));
    }

    public boolean isValidBST(TreeNode root) {

        if (root != null) {
            if (root.left != null && root.val < root.left.val)
                return false;

            if (root.right != null && root.val > root.right.val)
                return false;
        }
        if (root.left != null)
            isValidBST(root.left);
        if (root.right != null)
            isValidBST(root.right);

        return true;
    }

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int current_max = nums[0];
        int current_min = nums[0];
        int final_product = nums[0];
        for (int i = 1; i < nums.length; i++) {

            int tmp = current_max;
            current_max = Math.max(Math.max(current_max * nums[i], current_min * nums[i]), nums[i]);
            current_min = Math.min(Math.min(tmp * nums[i], current_min * nums[i]), nums[i]);
            if (current_max > final_product)
                final_product = current_max;
        }

        return final_product;
    }

    List<List<Integer>> ans;

    public List<List<Integer>> permute1(int[] nums) {

        ans = new ArrayList<>();

        // by it we will easily indentifies that we have take this value or not
        boolean[] b = new boolean[nums.length];

        get(nums, new ArrayList<Integer>(), b);

        return ans;
    }

    public void get(int[] nums, ArrayList set, boolean[] b) {
//we get new permutation

        if (set.size() == nums.length) {

            List<Integer> list2 = new ArrayList<>();
            list2 = (ArrayList) set.clone();
            System.out.println("adding new set now " + list2);
            ans.add(list2);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.println("############# start loop with " + i);
            if (!b[i]) {
                System.out.println("##############start processing " + i + " set " + set);
                b[i] = true;
                System.out.println("adding " + nums[i] + " to the set with i " + i);
                set.add(nums[i]);
                get(nums, set, b);
                System.out.println("removing " + nums[set.size() - 1] + " to the set with i = " + i);

                set.remove(set.size() - 1);
                System.out.println(" now we have all combinations from it so put false and take another" + set);
                // now we have all combinations from it so put false and take another
                b[i] = false;
                System.out.println("##############done processing " + i);

            }
            System.out.println("##############end loop with index " + i);
        }
    }


    public int burstBallons(int[] ballonHeights) {
        int minShots = 0;

        if (ballonHeights.length == 0)
            return 0;

        Map<Integer, Integer> map = new HashMap();

        for (int i = 0; i < ballonHeights.length; i++) {
            map.put(i, ballonHeights[i]);
        }

        while (!map.isEmpty()) {
            minShots++;

            Map.Entry<Integer, Integer> entry = map.entrySet().iterator().next();

            int arrowHeight = entry.getValue();

            Iterator it = map.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if (arrowHeight == (int) pair.getValue()) {
                    System.out.println("Deleting the following pairs from the map " + pair.getKey() + " = " + pair.getValue());
                    arrowHeight--;
                    it.remove();
                }
            }


        }
        return minShots;
    }


    public List<List<Integer>> permute(int[] nums) {
        Integer[] list = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            list[i] = nums[i];
        }
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        findPermutation(list, 0, result);
        return result;
    }

    void findPermutation(Integer nums[], int index, ArrayList<List<Integer>> result) {
        if (index == nums.length) {
            result.add(new ArrayList<Integer>(Arrays.asList(nums)));
        }
        for (int i = index; i < nums.length; i++) {
            int temp = nums[i];
            nums[i] = nums[index];
            nums[index] = temp;
            findPermutation(nums, index + 1, result);
            temp = nums[i];
            nums[i] = nums[index];
            nums[index] = temp;
        }
    }

    public int maxCoins(int[] nums) {
        if (nums.length == 0) return 0;

        int n = nums.length;

        // dp[i,j] holds the solution for max conins between indices and i and j in nums
        int[][] dp = new int[nums.length][nums.length];

        // k - sliding window size, i.e. distance between i and j. we try all sizes
        for (int k = 0; k < n; k++) {
            // i - statring index of sliding window in nums
            // j - endind index of sliding window in nums
            for (int i = 0; i < n - k; i++) {
                int j = i + k;
                int l = i == 0 ? 1 : nums[i - 1];
                int r = j == n - 1 ? 1 : nums[j + 1];

                dp[i][j] = 0;
                // q - which element of the sliding window will burst last, we try them all to find best/max
                for (int q = i; q <= j; q++) {
                    int lh = q == i ? 0 : dp[i][q - 1];
                    int rh = q == j ? 0 : dp[q + 1][j];
                    dp[i][j] = Math.max(dp[i][j], lh + l * nums[q] * r + rh);
                }
            }
        }

        return dp[0][nums.length - 1];
    }


    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (nums.length < 3 || nums[0] > 0)
            return result;

        boolean same = true;
        for (int i = 1; i < nums.length; i++)
            if (nums[i] != nums[0])
                same = false;

        if (same) {
            List<Integer> t = new ArrayList<Integer>();
            t.add(0);
            t.add(0);
            t.add(0);
            result.add(t);
            return result;
        }


        for (int i = 0; i < nums.length - 1; i++) {
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                List<Integer> tl = new ArrayList<Integer>();
                if (nums[i] + nums[l] + nums[r] == 0) {
                    tl.add(nums[i]);
                    tl.add(nums[l]);
                    tl.add(nums[r]);
                    result.add(tl);
                }
                if (nums[i] + nums[l] + nums[r] < 0)
                    l++;
                else
                    r--;
            }
        }
        result = result.stream().distinct().collect(Collectors.toList());
        return result;
    }

    public boolean isValidParentheses(String s) {
        Map<Character, Character> mappins = new HashMap<>();
        mappins.put('[', ']');
        mappins.put('{', '}');
        mappins.put('(', ')');
        Stack stack = new Stack<Character>();
        for (int i = 0; i < s.toCharArray().length; i++) {
            if (mappins.containsKey(s.charAt(i))) {
                stack.push(s.charAt(i));
            } else if (mappins.containsValue(s.charAt(i))) {
                if (stack.isEmpty() || (char) stack.pop() != s.charAt(i)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] > prices[i]) {
                    maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
                }
            }
        }
        return maxProfit;
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(new Integer(nums[i]));
        }
        if (set.size() != nums.length)
            return true;
        else
            return false;
    }

    public int[] twoSum(int[] nums, int target) {

        int[] aa = new int[2];

        END:
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j) {
                    if ((nums[i] + nums[j]) == target) {
                        aa[0] = i;
                        aa[1] = j;

                        break END;
                    }

                }
            }
        }
        return aa;
    }

    public void permutation(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            swap(arr, i, arr.length - 1);
            permutation(arr);
        }
    }

    public void swap(int[] aa, int index, int destIndex) {
        int tmp = aa[destIndex];
        aa[destIndex] = aa[index];
        aa[index] = tmp;
    }

}