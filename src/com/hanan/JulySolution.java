package com.hanan;

import java.util.*;

public class JulySolution {
    public static void main(String[] aa) {
        //System.out.println(new JulySolution().arrangeCoins(5));
        //   System.out.println(new JulySolution().prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 50));
        // System.out.println(new JulySolution().subsets(new int[]{1, 2, 3}));
        //System.out.println(Integer.parseInt("00000010100101000001111010011100"));
        // System.out.println(new JulySolution().reverseBits(Integer.parseInt("00000010100101000001111010011100")));
        System.out.println(new JulySolution().ArrayChallenge(new int[]{1, 1, 2, 10, 3, 1, 12}));
    }


    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val != q.val)
            return false;
        return isSameTree(p.left, q.left) && isSameTree(p.left, q.right);
    }


    public static int ArrayChallenge(int[] arr) {
        Arrays.sort(arr);
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int doubleSum = sum * sum;
        int prevelement = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int product = prevelement * arr[i];
            if (product > doubleSum) {
                System.out.println("true");
                return 1;
            }

            prevelement = arr[i];

        }


        return 0;
    }

    public static String StringChallenge(String str) {
        return "" + str.split(" ").length;
    }

    public int reverseBits(int n) {

        int a = 60;
        int b = -60;
        int c = 0;
        System.out.println("60  = " + Integer.toBinaryString(a));
        System.out.println("-60 = " + Integer.toBinaryString(b));
        //signed shift
        c = a >> 1;
        System.out.println("60 >> 1  = " + Integer.toBinaryString(c));

        //unsigned shift
        c = a >>> 1;
        System.out.println("60 >>> 1 = " + Integer.toBinaryString(c));

        c = b >> 1;
        System.out.println("-60 >> 1  = " + Integer.toBinaryString(c));

        c = b >>> 1;
        System.out.println("-60 >>> 1 = " + Integer.toBinaryString(c));


        int ans = 0;

        for (int i = 0; i < 32; i++) {
            ans += (n & 1);
            if (i == 31) {
                break;
            }
            ans <<= 1;
            n >>= 1;
        }

        return ans;
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList();
        output.add(new ArrayList<Integer>());

        for (int num : nums) {
            List<List<Integer>> newSubsets = new ArrayList();
            for (List<Integer> curr : output) {
                newSubsets.add(new ArrayList<Integer>(curr) {{
                    add(num);
                }});
            }
            for (List<Integer> curr : newSubsets) {
                output.add(curr);
            }
        }
        return output;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        if (root == null) return new ArrayList();
        List<List<Integer>> result = new ArrayList();
        Queue<TreeNode> queue = new LinkedList();
        Stack<List<Integer>> stack = new Stack();

        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList();
            while (size-- > 0) {
                root = queue.poll();
                level.add(root.val);
                if (root.left != null)
                    queue.add(root.left);
                if (root.right != null)
                    queue.add(root.right);
            }
            stack.add(level);
        }

        while (!stack.isEmpty())
            result.add(stack.pop());

        return result;
    }

    public int[] prisonAfterNDays(int[] cells, int N) {
        N = (N - 1) % 14 + 1;
        for (int i = 0; i < N; i++)
            cells = nextDayState(cells);
        return cells;
    }

    private int[] nextDayState(int[] cells) {
        int[] next = new int[cells.length];
        for (int i = 1; i < cells.length - 1; i++)
            next[i] = (cells[i - 1] == cells[i + 1]) ? 1 : 0;
        return next;
    }

    public int arrangeCoins(int n) {
        int numberOfStaircase = 0;
        int currentN = n;
        for (int i = 1; i <= n; i++) {
            numberOfStaircase++;
            if (currentN < i)
                return i - 1;
            currentN = currentN - i;

        }
        return numberOfStaircase;
    }

}
