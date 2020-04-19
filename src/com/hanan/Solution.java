package com.hanan;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        // int[] nums = {4, 1, 2, 1, 2};
        //int[] numsMax = {-2,1,-3,4,-1,2,1,-5,4};
        // int[] numsMax = {1,2,3};
        //int nums[] = {0,0,1};
        //moveZeroes(nums);
        int[] prices = {7, 6, 4, 3, 1};
        String[] strings = {"ray", "cod", "abe", "ned", "arc", "jar", "owl", "pop", "paw", "sky", "yup", "fed", "jul", "woo", "ado", "why", "ben", "mys", "den", "dem", "fat", "you", "eon", "sui", "oct", "asp", "ago", "lea", "sow", "hus", "fee", "yup", "eve", "red", "flo", "ids", "tic", "pup", "hag", "ito", "zoo"};
        //[["hag"],["pup"],["ids"],["ito"],["flo"],["red"],["hus"],["sow"],["asp"],["oct"],["sui"],["fee"],["eon"],["tic"],["sky"],["ago"],["paw"],["jul"],["pop"],["jar"],["den","ned"],["owl"],["eve"],["mys"],["abe"],["zoo"],["ado"],["ray"],["cod"],["lea"],["arc"],["dem"],["fat"],["yup","yup"],["woo"],["fed"],["why"],["ben"],["you"]]
        int[] arr = {1, 1, 2, 2};
        System.out.println("output ..... " + countElements(arr));

    }

    public static int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++)
            res ^= nums[i];

        return res;
    }


    private static boolean isHappy(int n) {
        List<Integer> numsSeen = new ArrayList<Integer>();
        int result = 0, digit = 0;
        boolean isHappy = false;
        int count = 0;
        int tmp = n;

        while (tmp > 0) {
            ++count;
            digit = tmp % 10;
            System.out.println("Digit  is: " + digit + " and its square is " + digit * digit);
            result += digit * digit;
            tmp = tmp / 10;

            if (tmp == 0) {
                if (result == 1) {
                    isHappy = true;
                    break;
                }
                tmp = result;
                System.out.println("finish " + count + " round with result " + result + " resetting the result and now we are going to split " + tmp);
                if (numsSeen.contains(result)) {
                    System.out.println(result + " found before in the seen list; then its not happy");
                    isHappy = false;
                    break;
                }
                numsSeen.add(result);
                result = 0;
            }
        }
        return isHappy;
    }

    private static int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n <= 0)
            return 0;
        int maxSum = nums[0];
        int sum = nums[0];
        for (int i = 1; i < n; ++i) {
            if (sum < 0) // no positive gain for following numbers
                sum = 0;
            sum += nums[i];
            if (sum > maxSum)
                maxSum = sum;
        }

        return maxSum;

    }

    private static void moveZeroes(int[] nums) {
        int i = 0;
        int j = 0;
        while (j < nums.length) {
            if (nums[j] != 0) {
                nums[i++] = nums[j];
            }
            j++;
        }
        while (i < nums.length) {
            nums[i++] = 0;
        }

//        int lastIndex = nums.length-1;
//        for (int i = 0; i < nums.length; i++) {
//
//            if (nums[i] == 0 && i <= lastIndex) {
//                for (int j=i ; j<lastIndex ; j++){
//                    if (nums[j+1] == 0){
//                        break;
//                    }
//                    nums[j] = nums[j+1];
//                }
//                nums[lastIndex] = 0;
//                lastIndex--;
//            }
//        }
//        System.out.println(nums);


    }

    private static int maxProfit(int[] prices) {

        int maxProfit = 0;

        for (int i = 0; i < prices.length && (i + 1 != prices.length); i++) {
            if (prices[i] < prices[i + 1]) {
                maxProfit += (prices[i + 1] - prices[i]);
            }
        }
        return maxProfit;

    }

    public static List<List<String>> groupAnagrams1(String[] strs) {

        List<List<String>> result = new ArrayList<>();

        List firstList = new ArrayList<String>();
        firstList.add(strs[0]);

        result.add(firstList);

        for (int i = 1; i < strs.length; i++) {

            String newStr = strs[i];

            List<String> listFound = null;

            for (List<String> list : result) {

                String strInList = list.get(0);

                boolean anagrams = isAnagrams(newStr, strInList);

                if (anagrams) {
                    listFound = list;
                    break;
                } else
                    continue;


            }

            if (listFound == null) {
                List newList = new ArrayList<String>();
                newList.add(strs[i]);
                result.add(newList);
            } else
                listFound.add(newStr);

        }
        return result;
    }

    public static boolean isAnagrams(String str1, String str2) {

        String s1 = str1.replaceAll("\\s", "");
        String s2 = str2.replaceAll("\\s", "");
        boolean status = true;
        if (s1.length() != s2.length()) {
            status = false;
        } else {
            char[] ArrayS1 = s1.toLowerCase().toCharArray();
            char[] ArrayS2 = s2.toLowerCase().toCharArray();
            Arrays.sort(ArrayS1);
            Arrays.sort(ArrayS2);
            status = Arrays.equals(ArrayS1, ArrayS2);
        }
        return status;

    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for (String str : strs) {
            char[] arr = new char[26];
            for (int i = 0; i < str.length(); i++) {
                arr[str.charAt(i) - 'a']++;
            }
            String ns = new String(arr);
            if (map.containsKey(ns)) {
                map.get(ns).add(str);
            } else {
                ArrayList<String> al = new ArrayList<String>();
                al.add(str);
                map.put(ns, al);
            }
        }
        result.addAll(map.values());
        return result;
    }

    public static int countElements(int[] arr) {
        int numOfCounting = 0;
        HashSet set = new HashSet();
        for (int i : arr) {
            set.add(i);
        }
        // set.addAll(Collections.singleton(arr));
        for (int i = 0; i < arr.length; i++) {
            if (set.contains(arr[i] + 1)) {
                numOfCounting++;
            }
        }
        return numOfCounting;
    }




}



