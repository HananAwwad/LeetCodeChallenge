package com.hanan;

import java.util.*;

public class JulySolution {
    public static void main(String[] aa) {
        //System.out.println(new JulySolution().arrangeCoins(5));
        //   System.out.println(new JulySolution().prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 50));
        // System.out.println(new JulySolution().subsets(new int[]{1, 2, 3}));
        //System.out.println(Integer.parseInt("00000010100101000001111010011100"));
        // System.out.println(new JulySolution().reverseBits(Integer.parseInt("00000010100101000001111010011100")));
        // System.out.println(new JulySolution().ArrayChallenge(new int[]{1, 1, 2, 10, 3, 1, 12}));
        System.out.println(new JulySolution().topKFrequent(new int[]{1, 1, 1, 1, 2, 2, 3}, 2));

    }


    public int[] topKFrequent(int[] nums, int k) {
        // O(1) time
        if (k == nums.length) {
            return nums;
        }

        // 1. build hash map : character and how often it appears
        // O(N) time
        Map<Integer, Integer> count = new HashMap();
        for (int n : nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // init heap 'the less frequent element first'
        Queue<Integer> heap = new PriorityQueue<>(
                (n1, n2) -> count.get(n1) - count.get(n2));

        // 2. keep k top frequent elements in the heap
        // O(N log k) < O(N log N) time
        for (int n : count.keySet()) {
            heap.add(n);
            if (heap.size() > k) heap.poll();
        }

        // 3. build an output array
        // O(k log k) time
        int[] top = new int[k];
        for (int i = k - 1; i >= 0; --i) {
            top[i] = heap.poll();
        }
        return top;

    }

    public boolean check(TreeNode p, TreeNode q) {
        // p and q are null
        if (p == null && q == null) return true;
        // one of p and q is null
        if (q == null || p == null) return false;
        if (p.val != q.val) return false;
        return true;
    }

    public boolean isSameTree1(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (!check(p, q)) return false;

        // init deques
        ArrayDeque<TreeNode> deqP = new ArrayDeque<TreeNode>();
        ArrayDeque<TreeNode> deqQ = new ArrayDeque<TreeNode>();
        deqP.addLast(p);
        deqQ.addLast(q);

        while (!deqP.isEmpty()) {
            p = deqP.removeFirst();
            q = deqQ.removeFirst();

            if (!check(p, q)) return false;

            if (p != null) {

                // in Java nulls are not allowed in Deque

                if (!check(p.left, q.left)) return false;

                if (p.left != null) {
                    deqP.addLast(p.left);
                    deqQ.addLast(q.left);
                }

                if (!check(p.right, q.right)) return false;

                if (p.right != null) {
                    deqP.addLast(p.right);
                    deqQ.addLast(q.right);
                }
            }
        }
        return true;
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

        int sum = (Arrays.stream(arr).sum()) * 2;
        Arrays.sort(arr);
        //Arrays.stream(arr).asDoubleStream()
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
