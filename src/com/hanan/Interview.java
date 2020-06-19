package com.hanan;

import java.util.*;

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
        System.out.println(new Interview().isValidParentheses("([)]"));
    }

    public boolean isValidParentheses(String s) {
        Map<Character , Character> mappins = new HashMap<>();
        mappins.put('[', ']');
        mappins.put('{','}');
        mappins.put('(',')');
        Stack stack = new Stack<Character>();
        for (int i =0 ; i < s.toCharArray().length; i++){
            if (mappins.containsKey(s.charAt(i))){
                stack.push(s.charAt(i));
            }else if (mappins.containsValue(s.charAt(i))){
                if (stack.isEmpty() || (char)stack.pop() != s.charAt(i)){
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