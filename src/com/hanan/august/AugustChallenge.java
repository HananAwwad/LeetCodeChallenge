package com.hanan.august;


import com.hanan.common.TreeNode;
import com.hanan.june.JuneSolution;

import java.util.ArrayList;
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
        System.out.println( new JuneSolution().hIndex(new int[]{0,1,3,5,6}));
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
