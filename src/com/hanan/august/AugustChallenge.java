package com.hanan.august;


import com.hanan.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AugustChallenge {

    public static void main(String[] aa) {
//        System.out.println("USA......" + new AugustChallenge().detectCapitalUse("USA"));
//        System.out.println("Capital.." + new AugustChallenge().detectCapitalUse("Capital"));
//        System.out.println("small...." + new AugustChallenge().detectCapitalUse("small"));
// Your WordDictionary object will be instantiated and called as such:
//        WordDictionary obj = new WordDictionary();
//        obj.addWord("bad");
//        obj.addWord("dad");
//        obj.addWord("mad");
//
//        boolean param_2 = obj.search("b..");
//        System.out.println(param_2);
        //System.out.println(new AugustChallenge().titleToNumber("AAA"));
        //  System.out.println(new JuneSolution().hIndex(new int[]{0, 1, 3, 5, 6}));
        //System.out.println(new AugustChallenge().generate(5).size());
        //System.out.println(new AugustChallenge().getRow(3));
        //System.out.println("max profit " + new AugustChallenge().maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));

        // System.out.println(new AugustChallenge().distributeCandies(7, 4));
        StreamChecker streamChecker = new StreamChecker(new String[]{"cd", "f", "kl"}); // init the dictionary.
        streamChecker.query('a');          // return false
        streamChecker.query('b');          // return false
        streamChecker.query('c');          // return false
        streamChecker.query('d');          // return true, because 'cd' is in the wordlist
        streamChecker.query('e');          // return false
        streamChecker.query('f');          // return true, because 'f' is in the wordlist
        streamChecker.query('g');          // return false
        streamChecker.query('h');          // return false
        streamChecker.query('i');          // return false
        streamChecker.query('j');          // return false
        streamChecker.query('k');          // return false
        streamChecker.query('l');          // return true, because 'kl' is in the wordlist

    }

    public int[] distributeCandies(int candies, int num_people) {
        int[] result = new int[num_people];
        int index = 0;
        int i = 1;
        int count = candies;
        while (count != 0) {
            result[i - 1] += i;
            count -= i;

            if (i == num_people) {

            }
            i++;
        }

        return result;
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;

        if (prices.length < 2)
            return 0;

        int pMas = prices[n - 1];
        int pMin = prices[0];
        int[] profit1 = new int[n];
        int[] profit2 = new int[n];
        for (int i = 1; i < n; i++) {
            profit1[i] = Math.max(profit1[i - 1], prices[i] - pMin);
            pMin = Math.min(pMin, prices[i]);

            int j = n - 1 - i;

            profit2[j] = Math.max(profit2[j + 1], pMas - prices[j]);
            pMas = Math.max(pMas, prices[j]);

        }

        int profit = 0;
        for (int i = 0; i < n; i++) {
            profit = Math.max(profit, profit1[i] + profit2[i]);
        }
        return profit;
    }

    public List<Integer> getRow(int rowIndex) {
        Integer[] result = new Integer[rowIndex + 1];
        Arrays.fill(result, 0);
        result[0] = 1;
        for (int i = 1; i <= rowIndex; i++)
            for (int j = i; j > 0; j--)
                result[j] = result[j] + result[j - 1];

        return Arrays.asList(result);


//        List<List<Integer>> triangle = new ArrayList<>();
//        List<Integer> row = new ArrayList<>();
//        if (rowIndex == 0) {
//            return row;
//        }
//        triangle.add(row);
//        triangle.get(0).add(1);
//
//
//        for (int i = 1; i <= rowIndex; i++) {
//            row = new ArrayList<>();
//            List<Integer> previousRow = triangle.get(i - 1);
//            // The last row element is always 1.
//            row.add(1);
//
//            for (int j = 1; j < i; j++) {
//                row.add(previousRow.get(j) + previousRow.get(j - 1));
//            }
//            // The last row element is always 1.
//            row.add(1);
//            triangle.clear ();
//            triangle.add(row);
//
//        }
//
//
//        return row;


//        List<Integer> result = new ArrayList<>();
//        for (int i=0; i <=rowIndex ; i++){
//            int j =0;
//            while (result.size()-1 > j){
//                result.add(result.get(0) + result.get(1));
//                result.remove(0);
//                j++;
//            }
//            result.add(1);
//        }
//        return  result;
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        // First base case; if user requests zero rows, they get zero rows.
        if (numRows == 0) {
            return triangle;
        }

        // Second base case; first row is always [1].
        triangle.add(new ArrayList<>());
        triangle.get(0).add(1);


        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> previousRow = triangle.get(i - 1);
            // The last row element is always 1.
            row.add(1);

            for (int j = 1; j < i; j++) {
                row.add(previousRow.get(j) + previousRow.get(j - 1));
            }
            // The last row element is always 1.
            row.add(1);

            triangle.add(row);
        }
        return triangle;
    }

    public int titleToNumber(String s) {
        int result = 0;
        int factor = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            result += factor * (s.charAt(i) - 'A' + 1);
            factor *= 26;
        }
        return result;
    }

    public String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();
        while (n > 0) {
            if (n % 26 == 0)
                result.insert(0, 'Z');
            else {
                result.insert(0, (char) ('A' + n % 26 - 1));
            }
            n--;
            n /= 26;
        }
        return result.toString();
    }

    public int pathSumIII(TreeNode root, int sum) {
        if (root == null)
            return 0;

        return helper(root, sum) + pathSumIII(root.left, sum) + pathSumIII(root.right, sum);
    }

    private int helper(TreeNode node, int sum) {
        if (node == null)
            return 0;
        else
            return (node.val == sum ? 1 : 0)
                    + helper(node.left, sum - node.val)
                    + helper(node.right, sum - node.val);
    }


    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> pathSumII(TreeNode root, int sum) {
        fillPaths(root, sum, sum, new ArrayList());
        return ans;
    }

    public void fillPaths(TreeNode root, int currentSum, int sum, List path) {
        if (root == null)
            return;
        currentSum -= root.val;
        path.add(root.val);
        if (currentSum == 0 && root.left == null && root.right == null) {
            ans.add(new LinkedList<>(path));
            path.remove(path.size() - 1);
            return;
        }
        fillPaths(root.left, currentSum, sum, path);
        fillPaths(root.right, currentSum, sum, path);
        path.remove(path.size() - 1);

    }


    public boolean hasPathSumI(TreeNode root, int sum) {
        return hasPathSumRecursive(root, sum);
    }

    public boolean hasPathSumRecursive(TreeNode root, int sum) {
        if (root == null)
            return false;
        sum -= root.val;
        if (sum == 0 && root.left == null && root.right == null)
            return true;
        return hasPathSumRecursive(root.left, sum) || hasPathSumRecursive(root.right, sum);
    }

    public boolean detectCapitalUse(String word) {
        return word.matches("([A-Z]*|).[a-z]*");


        //return word.matches("[A−Z]∗∣[a−z]∗∣[A−Z][a−z]∗");
    }
}
