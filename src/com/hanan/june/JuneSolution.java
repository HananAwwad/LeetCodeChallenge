package com.hanan.june;

import java.util.*;

public class JuneSolution {

    public static void main(String[] aa) {
//        int[] a = new int[]{4, 2, 7, 1, 3, 6, 9};
//        for (int i = 0; i < a.length; i++) {
//
//        }
//        char[] s = new char[]{'h','e','l','l','o'};
//        new JuneSolution().reverseString(s);
//        System.out.println(s);

        // System.out.println(new JuneSolution().twoCitySchedCost(new int[][]{{10, 20}, {30, 200}, {400, 50}, {30, 20}}));
        // System.out.println(new JuneSolution().reconstructQueue(new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}}));
        //System.out.println(new JuneSolution().change(5, new int[]{1, 2, 3}));
        ///System.out.println(new JuneSolution().isPowerOfTwo(128));
        // System.out.println(new JuneSolution().isSubsequence("axc", "ahbgdc"));
        // System.out.println(new JuneSolution().searchInsert(new int[]{1, 3, 5, 6}, 0));
        //  System.out.println(new JuneSolution().sortColors(new int[]{2,0,2,1,1,0}));


        //System.out.print(longest_special_subseq("afcbedg",7, 2));
//        RandomizedSet obj = new RandomizedSet();
//        boolean param_1 = obj.insert(2);
//        boolean param_2 = obj.remove(1);
//        int param_3 = obj.getRandom();

        //System.out.println(new JuneSolution().findCheapestPrice(3,new int[][]{{0,1,100},{1,2,100},{0,2,500}},0,2,1));
//        TreeNode root = null;
//        int arr[] = {4, 2, 7, 1, 3};
//        root = new JuneSolution().insertLevelOrder(arr, root, 0);
//        new JuneSolution().inOrder(root);

    }

    public void solve(char[][] board) {

        if (board == null || board.length == 0)
            return;

        int rows = board.length;
        int col = board[0].length;
        for (int i =0 ; i < board.length ; i++){
            if (board[i][0] == 'O') DFS(board, i , 0);
            if (board[i][col-1] == 'O') DFS(board,i, col -1);
        }

        for (int j =0 ; j < col; j ++){
            if (board[0][j] == 'O') DFS(board, 0 , j);
            if (board[rows-1][j] =='O') DFS(board,rows-1, j);
        }
        for (int i = 0 ; i < rows ; i++){
            for (int j =0 ; j< col; j++){
                if (board[i][j] == 'E') board[i][j] = 'O';
                else if (board[i][j] == 'O') board[i][j] = 'X';
            }
        }
    }

    public void DFS(char[][] board, int i , int j ){
        if (i < 0 || j < 0 || i>= board.length || j >=board[0].length || board[i][j] != 'O')return;
        board[i][j] = 'E';
        DFS(board, i + 1, j);
        DFS(board,i , j +1);
        DFS(board, i-1 , j);
        DFS(board,i, j-1);
    }
    public String validIPAddress(String IP) {
        return "";
    }

    public TreeNode searchBST(TreeNode root, int val) {
        if (root.val == val || root == null)
            return root;
        if (root.val > val)
            return searchBST(root.left, val);
        return searchBST(root.right, val);

    }

    // Function to insert nodes in level order
    public TreeNode insertLevelOrder(int[] arr, TreeNode root,
                                     int i) {
        // Base case for recursion
        if (i < arr.length) {
            TreeNode temp = new TreeNode(arr[i]);
            root = temp;

            // insert left child
            root.left = insertLevelOrder(arr, root.left,
                    2 * i + 1);

            // insert right child
            root.right = insertLevelOrder(arr, root.right,
                    2 * i + 2);
        }
        return root;
    }

    public void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            inOrder(root.right);
            System.out.print(root.val + " ");

        }
    }

    static int longest_special_subseq(String str, int n, int k) {
        int[] dp = new int[n];
        int[] max_length = new int[26];
        for (int i = 0; i < n; i++) {

            int curr = str.charAt(i) - 'a';
            int lower = Math.max(0, curr - k);
            int uppper = Math.min(25, curr + k);

            for (int j = lower; j < uppper + 1; j++) {
                dp[i] = Math.max(dp[i], max_length[j] + 1);
            }

            max_length[curr] = Math.max(dp[i], max_length[curr]);
        }
        int ans = 0;
        for (int i : dp) ans = Math.min(i, ans);
        return ans;
    }

    static int longest_special_subseq1(String str, int n, int k) {
        // Creating a list with
        // all 0's of size
        // equal to the length of String
        int[] dp = new int[n];

        // Supporting list with
        // all 0's of size 26 since
        // the given String consists
        // of only lower case alphabets
        int[] max_length = new int[26];

        for (int i = 0; i < n; i++) {

            // Converting the ascii value to
            // list indices
            int curr = str.charAt(i) - 'a';

            // Determining the lower bound
            int lower = Math.max(0, curr - k);

            // Determining the upper bound
            int upper = Math.min(25, curr + k);

            // Filling the dp array with values
            for (int j = lower; j < upper + 1; j++) {
                dp[i] = Math.max(dp[i], max_length[j] + 1);
            }

            // Filling the max_length array with max
            // length of subsequence till now
            max_length[curr] = Math.max(dp[i], max_length[curr]);
        }

        int ans = 0;

        for (int i : dp) ans = Math.max(i, ans);

        // return the max length of subsequence
        return ans;
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if (flights.length == 0)
            return -1;
        HashMap<Integer, ArrayList<int[]>> graph = new HashMap<>();
        for (int[] flight : flights) {
            if (!graph.containsKey(flight[0])) {
                graph.put(flight[0], new ArrayList<>());
            }
            graph.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }
        PriorityQueue<Node> q = new PriorityQueue<Node>((a, b) -> (a.cost - b.cost));

        q.add(new Node(src, 0, -1));

        while (!q.isEmpty()) {
            Node curr = q.poll();

            if (curr.city == dst)
                return curr.cost;

            if (curr.stop < K) {
                List<int[]> nexts = graph.get(curr.city);
                for (int[] next : nexts) {
                    q.add(new Node(next[0], curr.cost + next[1], curr.stop + 1));
                }
            }

        }
        return -1;
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {

        }
        return res;
    }

    public void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, mid, low);
                mid++;
                low++;
            } else if (nums[mid] == 1) {
                mid++;
            } else if (nums[mid] == 2) {
                swap(nums, mid, high);
                high--;
            }
        }

    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public int[] sortColors1(int[] nums) {
        int zeros = 0;
        int ones = 0;
        int twos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                zeros++;
            else if (nums[i] == 1)
                ones++;
            else if (nums[i] == 2)
                twos++;
        }
        int i = 0;
        while (i < nums.length) {
            if (zeros != 0) {
                nums[i] = 0;
                zeros--;
            } else if (ones != 0) {
                nums[i] = 1;
                ones--;
            } else if (twos != 0) {
                nums[i] = 2;
                twos--;
            }
            i++;
        }
        return nums;
    }


    public int searchInsert(int[] nums, int target) {
        int i = 0;
        for (; i < nums.length; i++) {
            if (target <= nums[i])
                return i;
        }
        return i;
    }

    public boolean isSubsequence(String s, String t) {
        int i = 0;
        int j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }

        return i == s.length();
    }

    public boolean isPowerOfTwo(int n) {
        if (n < 0) return false;
        while (n % 2 == 0) n /= 2;
        return n == 1;
    }

    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 1;
        for (int j = 1; j <= coins.length; j++) {
            dp[j][0] = 1; // number ways of selecting for amount zero is 1
            for (int i = 1; i <= amount; i++) {
                dp[j][i] = dp[j - 1][i]; // exclude current coin
                if (i - coins[j - 1] >= 0) { // check if amount  >= to the current i`th coin
                    dp[j][i] += dp[j][i - coins[j - 1]]; // include current coin
                }
            }
        }
        return dp[coins.length][amount];
    }

    public int change1(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int[] p : dp) {
            Arrays.fill(p, -1);
        }
        return cc(coins, 0, amount, dp);
    }

    int cc(int[] coins, int i, int amount, int[][] dp) {
        if (amount == 0) return 1;
        if (amount < 0 || i == coins.length) return 0;
        if (dp[i][amount] != -1)
            return dp[i][amount];
        return dp[i][amount] = (cc(coins, i, amount - coins[i], dp) + cc(coins, i + 1, amount, dp));
    }

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]));
        List<int[]> res = new ArrayList<>();

        for (int[] p : people) {
            res.add(p[1], p);
        }
        int n = people.length;
        return res.toArray(new int[n][2]);
    }


    public int twoCitySchedCost(int[][] costs) {
        int N = costs.length / 2;
        int[] refund = new int[N * 2];
        int minCost = 0, index = 0;
        for (int[] cost : costs) {
            refund[index++] = cost[1] - cost[0];
            minCost += cost[0];
        }
        Arrays.sort(refund);
        for (int i = 0; i < N; i++) {
            minCost += refund[i];
        }
        return minCost;
    }

    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left++] = s[right];
            s[right--] = tmp;
        }

    }

    public void deleteNode(ListNode node) {

        node.val = node.next.val;
        node.next = node.next.next;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return root;
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.right = left;
        right.left = right;
        return root;
    }


}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class RandomizedSet {

    List<Integer> list;
    Map<Integer, Integer> map;
    Random rand;

    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int index = map.get(val);
        int lastElement = list.get(list.size() - 1);
        list.set(index, lastElement);
        map.put(lastElement, index);
        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}

class Node {
    int city;
    int cost;
    int stop;

    public Node(int city, int cost, int stop) {
        this.city = city;
        this.cost = cost;
        this.stop = stop;
    }
}