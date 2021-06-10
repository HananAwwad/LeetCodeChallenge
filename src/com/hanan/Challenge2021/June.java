package com.hanan.Challenge2021;

import com.hanan.common.TreeNode;
import javafx.util.Pair;

import java.util.*;

public class June {


    public static void main(String[] aa) {
        //System.out.println(new June().maxAreaOfIsland(new int[][]{{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}}));
        //System.out.println(new June().isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        //   System.out.println(new June().openLock(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202"));
        //  System.out.println(new June().maxPerformance(6, new int[]{2,10,3,1,5,8}, new int[]{5,4,3,9,7,2}, 2));
        //  System.out.println(new June().longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        //System.out.println(new June().minCostClimbingStairs(new int[]{10, 15, 20}));
        //System.out.println(new June().buildTree(new int[]{3,9,20,15,7}, new int []{9,3,15,20,7}));
        System.out.println(new June().maxResult(new int[]{}, 1));
    }
    public int maxResult(int[] nums, int k) {
        Deque<Pair<Integer, Integer>> deque = new LinkedList() {{
            offer(new Pair<>(0, nums[0]));
        }};
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            while (!deque.isEmpty() && deque.peekFirst().getKey() < i - k) {
                deque.pollFirst();
            }

            max = nums[i] + (deque.isEmpty() ? 0 : deque.peekFirst().getValue());

            while (!deque.isEmpty() && deque.peekLast().getValue() <= max) {
                deque.pollLast();
            }

            deque.offerLast(new Pair<>(i, max));
        }

        return max;
    }
    int preorderIndex;
    Map<Integer, Integer> inorderIndexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        // build a hashmap to store value -> its index relations
        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return arrayToTree(preorder, 0, preorder.length - 1);
    }

    private TreeNode arrayToTree(int[] preorder, int left, int right) {
        // if there are no elements to construct the tree
        if (left > right) return null;

        // select the preorder_index element as the root and increment it
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        // build left and right subtree
        // excluding inorderIndexMap[rootValue] element because it's the root
        root.left = arrayToTree(preorder, left, inorderIndexMap.get(rootValue) - 1);
        root.right = arrayToTree(preorder, inorderIndexMap.get(rootValue) + 1, right);
        return root;
    }

    public int minCostClimbingStairs(int[] cost) {
        int downOne = 0;
        int downTwo = 0;
        for (int i = 2; i < cost.length + 1; i++) {
            int temp = downOne;
            downOne = Math.min(downOne + cost[i - 1], downTwo + cost[i - 2]);
            downTwo = temp;
        }

        return downOne;
    }


    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;

    }

    private class Engineer {
        private int speed;
        private int efficiency;

        public Engineer(int speed, int efficiency) {
            this.speed = speed;
            this.efficiency = efficiency;
        }
    }

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        //TC: O(nlong)
        //SC : O(n)
        List<Engineer> engineerList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            engineerList.add(new Engineer(speed[i], efficiency[i]));
        }
        engineerList.sort((a, b) -> b.efficiency - a.efficiency);
        PriorityQueue<Engineer> currentTeam = new PriorityQueue<>((a, b) -> a.speed - b.speed);
        long teamSpeed = 0;
        long maxPerformance = 0;
        for (Engineer engineer : engineerList) {
            if (currentTeam.size() == k) {
                Engineer slowGuy = currentTeam.poll();
                teamSpeed -= slowGuy.speed;
            }
            currentTeam.add(engineer);
            teamSpeed += engineer.speed;
            long profermanceWithNewGuy = teamSpeed * (long) engineer.efficiency;
            maxPerformance = Math.max(maxPerformance, profermanceWithNewGuy);
        }
        return (int) (maxPerformance % 1000000007);
    }

    public String plusOne(String str, int index) {
        char[] c = str.toCharArray();
        if (c[index] == '9') {
            c[index] = '0';
        } else {
            c[index] += 1;
        }
        return new String(c);
    }

    public String minusOne(String str, int index) {
        char[] c = str.toCharArray();
        if (c[index] == '0') {
            c[index] = '9';
        } else {
            c[index] -= 1;
        }
        return new String(c);
    }

    public int openLock(String[] deadends, String target) {
        int minimumTurns = -1;
        Set<String> set = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        set.add("0000");
        queue.offer("0000");
        for (String deadend : deadends) {
            if (deadend.equals("0000")) {
                return -1;
            }
            set.add(deadend);
        }
        while (!queue.isEmpty()) {
            ++minimumTurns;
            int size = queue.size();
            while (size-- != 0) {
                String str = queue.poll();
                if (str.equals(target)) {
                    return minimumTurns;
                }
                for (int index = 0; index < 4; index++) {
                    String plus = plusOne(str, index);
                    String minus = minusOne(str, index);
                    if (!set.contains(plus)) {
                        set.add(plus);
                        queue.offer(plus);
                    }
                    if (!set.contains(minus)) {
                        set.add(minus);
                        queue.offer(minus);
                    }
                }
            }
        }
        return -1;
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        int[][] mem = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                mem[i][j] = -1;
            }
        }
        return is_Interleave(s1, 0, s2, 0, s3, 0, mem);
    }

    public boolean is_Interleave(String s1, int i, String s2, int j, String s3, int k, int[][] mem) {
        if (i == s1.length())
            return s2.substring(j).equals(s3.substring(k));
        if (j == s2.length())
            return s1.substring(i).equals(s3.substring(k));

        if (mem[i][j] > -1)
            return mem[i][j] == 1 ? true : false;
        boolean ans = false;
        if ((s3.charAt(k) == s1.charAt(i) && is_Interleave(s1, i + 1, s2, j, s3, k + 1, mem)) ||
                (s3.charAt(k) == s2.charAt(j) && is_Interleave(s1, i, s2, j + 1, s3, k + 1, mem)))
            ans = true;
        mem[i][j] = ans ? 1 : 0;
        return ans;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int maxArea = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, getCurrentArea(i, j, grid));
                }
            }
        }
        return maxArea;
    }

    private int getCurrentArea(int i, int j, int[][] grid) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] <= 0)
            return 0;
        grid[i][j] = -1;
        int leftArea = getCurrentArea(i, j - 1, grid);
        int rightArea = getCurrentArea(i, j + 1, grid);
        int upArea = getCurrentArea(i - 1, j, grid);
        int downArea = getCurrentArea(i + 1, j, grid);
        int totalArea = 1 + leftArea + rightArea + upArea + downArea;
        return totalArea;
    }


}
