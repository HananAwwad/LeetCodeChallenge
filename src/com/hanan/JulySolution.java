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
        //System.out.println(new JulySolution().topKFrequent(new int[]{1, 1, 1, 1, 2, 2, 3}, 2));
        //System.out.println(new JulySolution().singleNumber(new int []{1,1,2,2,3,5}));
        System.out.println(new JulySolution().findMin(new int[]{2, 2, 2, 0, 1}));

    }

    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left != right) {
            int mid = left + ((right - left) / 2);
            if (nums[mid] == target)
                return true;
            else if (nums[mid] < nums[right]) {
                if (target > nums[left] && target < nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }

            } else {
                if (target > nums[left] && target < nums[right]) {
                    right = mid - 1;
                } else
                    left = mid + 1;


            }
        }
        return false;
    }


    public int findMin(int[] nums) {
        int start = 0, end = nums.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] < nums[start]) {
                start++;
                end = mid;
            } else if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else
                end--;
        }
        return nums[start];

//        if (nums.length == 2)
//            return nums[1];
//        int start = 0;
//        int end = nums.length - 1;
//        int mid = 0;
//        while (end != start) {
//            mid = start + ((end - start) / 2);
//            if (nums[mid] > nums[start]) {
//                start = mid - 1;
//            } else {
//               // end = mid + 1;
//                break;
//            }
//
//        }
//        return nums[mid];
    }

    public int[] singleNumber(int[] nums) {

        int xor = 0;

        for (int n : nums) {
            xor ^= n;
        }
        // generate the bit mask ....
        int mask = 0;
        mask = (xor & (xor - 1)) ^ xor;

        int num1 = 0;
        for (int n : nums) {
            // classify them in to two groups,, first group with i = 0 , then we generate the second group depending on num2 = xor ^ num1
            if ((mask & n) == 0)
                num1 ^= n;
        }
        return new int[]{num1, xor ^ num1};
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
