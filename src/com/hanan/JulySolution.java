package com.hanan;

import java.util.*;

public class JulySolution {
    public static void main(String[] aa) {
        //System.out.println(new JulySolution().arrangeCoins(5));
        System.out.println(new JulySolution().prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 50));
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
