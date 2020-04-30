package com.hanan;

import java.util.*;

public class Solution2 {


    //  static final int ROW = 5, COL = 5;

    public static void main(String[] aa) {

//        ListNode listNodeObj = new ListNode(6);
//        ListNode head = null;
//
//        int[] arr = {1,2,3,4,5,6};
//        for (int i = arr.length-1; i >= 0 ; i--) {
//            head = listNodeObj.push(arr[i], head);
//        }
//        listNodeObj.printList(head);//

//        new Solution2().backspaceCompare("a##c", "#a#c");
//        MinStack minStack = new MinStack();
//        minStack.push(-2);
//        minStack.push(0);
//        minStack.push(-3);
//        minStack.getMin();  // --> Returns -3.
//        minStack.pop();
//        minStack.top();      //--> Returns 0.
//        minStack.getMin();   //--> Returns -2.


//        TreeNode root = null;
//        int arr[] = {1, 2, 3, 4, 5};
//        root = new Solution2().insertLevelOrder(arr, root, 0);
//        new Solution2().inOrder(root);
//        System.out.println("diameter " + new Solution2().diameterOfBinaryTree(root));
        //      int[] stones = {0, 1, 0, 0, 1, 1, 0};
        //  System.out.println(new Solution2().findMaxLength(stones));

//        String s = "abcdefg";
//        int[][] shift = {{1,1}, {1, 1},{0,2},{1,3}};
//        int[] nums = {1,2,3,4};
        //System.out.println("solution ...." + new Solution2().productExceptSelf(nums));

//        System.out.println(new Solution2().checkValidString("(*)"));
//
//        char M[][] = new char[][]{{'1', '1', '0', '0', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '1', '0', '0'},
//                {'0', '0', '0', '1', '1'}};
//
//        int[][] s = new int[][]{
//                {1, 3, 1},
//                {1, 5, 1},
//                {4, 2, 1}
//        };
//        int[] a = new int[]{8, 5, 1, 7, 10, 12};
//        TreeNode node = new Solution2().bstFromPreorder(a);
//        node.printPreorder(node);
        //    new Solution2().leftMostColumnWithOne(new BinaryMatrix(new int[][]{{0,0},{1,1}}));
        //   new Solution2().minPurchases(new int[]{1, 3, 6}, 15);
        // new Solution2().subarraySumMap(new int[]{1, 1, 1}, 2);
        System.out.println(new Solution2().rangeBitwiseAnd(5, 12));
    }

    public int rangeBitwiseAnd(int m, int n) {
        System.out.println("Binary is " + Integer.toBinaryString(m));

        System.out.println("Binary is " + Integer.toBinaryString(n));

        while (n > m){
            n = n & n - 1;
            System.out.println(n);
        }
        return m & n;

    }

    public int subarraySumMap(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public int subarraySum(int[] nums, int k) {

        int counter = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;

            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k) {
                    counter++;
                }

            }
        }
        return counter;
    }

    public int[][] distance_path(int src, int dest, int[][] wizards) {
        int result[][] = new int[2][];
        int minCost = 0;
        int[] minPath = null;
        // Put your code here to calculate minCost and minPath

        // Return the result, do not change the structure
        result[0] = new int[]{minCost};
        if (minPath == null) {
            minPath = new int[]{};
        }
        result[1] = minPath;
        return result;
    }

    public int minPurchases(int[] trip_durations, int total_hours) {
        int min_purchases = 0;
        // Put your code here to calculate min_purchases

        Arrays.sort(trip_durations);
        int tmp = total_hours;
        for (int i = trip_durations.length - 1; i >= 0; i--) {
            while (trip_durations[i] <= tmp) {
                tmp = tmp - trip_durations[i];
                min_purchases++;
            }
            if (tmp == 0)
                break;
        }
        // Return the result, do not change the structure
        return min_purchases;
    }


    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int result = -1;

        int row = binaryMatrix.dimensions().get(0);
        int col = binaryMatrix.dimensions().get(1);

        int i = 0;
        int j = col - 1;
        while (true) {
            int currentVal = binaryMatrix.get(i, j);
            //move left
            if (currentVal == 1) {
                result = j;
                j--;
            } else if (currentVal == 0)
                i++;
            if (i > row - 1)
                break;
            if (j < 0)
                break;
        }

        return result;
    }

    TreeNode root;

    private void insert(int val) {

        TreeNode node = new TreeNode(val);
        if (root == null) root = node;
        else {
            TreeNode temp = root;
            while (true) {
                if (temp.val > val) {
                    if (temp.left == null) {
                        temp.left = node;
                        break;
                    } else temp = temp.left;
                } else {
                    if (temp.right == null) {
                        temp.right = node;
                        break;
                    } else temp = temp.right;
                }
            }
        }
    }

    public TreeNode bstFromPreorder(int[] preorder) {

        for (int i = 0; i < preorder.length; i++)
            insert(preorder[i]);

        return root;
    }
//    public TreeNode bstFromPreorder(int[] preorder){
//
//        return helper(preorder,0,preorder.length-1);
//}

    private TreeNode helper(int[] preorder, int start, int end) {
        if (start > end)
            return null;

        TreeNode root = new TreeNode(preorder[start]);

        if (start == end)
            return root;


        //get the range for left sub tree
        int leftTreeEndIndex = start + 1;
        while (leftTreeEndIndex <= end && preorder[leftTreeEndIndex] < preorder[start])
            leftTreeEndIndex++;

        root.left = helper(preorder, start + 1, leftTreeEndIndex - 1);
        root.right = helper(preorder, leftTreeEndIndex, end);

        return root;


    }

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid;

        while (left <= right) {
            mid = left + (right - left) / 2;
            if (target == nums[mid])
                return mid;
            else if (nums[mid] <= nums[right]) {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else
                    right = mid - 1;
            } else {
                if (target < nums[mid] && target >= nums[left]) {
                    right = mid - 1;
                } else
                    left = mid + 1;
            }
        }

        return -1;

    }

    public int minPathSum(int[][] grid) {

        if (grid == null || grid.length == 0)
            return 0;

        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        // initialize top row
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        // initialize left column
        for (int j = 1; j < m; j++) {
            dp[j][0] = dp[j - 1][0] + grid[j][0];
        }

        // fill up the dp table
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                }
            }
        }

        return dp[m - 1][n - 1];

    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int row = grid.length;
        int col = grid[0].length;

        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    merge(grid, i, j);
                }
            }
        }

        return count;
    }

    public void merge(char[][] grid, int i, int j) {
        int row = grid.length;
        int col = grid[0].length;

        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] != '1')
            return;

        grid[i][j] = 'X';

        merge(grid, i - 1, j);
        merge(grid, i + 1, j);
        merge(grid, i, j - 1);
        merge(grid, i, j + 1);
    }


    public boolean checkValidString(String s) {
        int lo = 0, hi = 0;
        for (char c : s.toCharArray()) {
            lo += c == '(' ? 1 : -1;
            hi += c != ')' ? 1 : -1;
            if (hi < 0) break;
            lo = Math.max(lo, 0);
        }
        return lo == 0;
    }

    public int[] productExceptSelf(int[] nums) {
        int[] prod = new int[nums.length];

        int n = nums.length;
        int i = 0, temp = 1;
        /* Initialize the product array as 1 */
        for (int j = 0; j < n; j++)
            prod[j] = 1;

        /* In this loop, temp variable contains product of
           elements on left side excluding arr[i] */
        for (i = 0; i < n; i++) {
            prod[i] = temp;
            temp *= nums[i];
        }

        /* Initialize temp to 1 for product on right side */
        temp = 1;

        /* In this loop, temp variable contains product of
           elements on right side excluding arr[i] */
        for (i = n - 1; i >= 0; i--) {
            prod[i] *= temp;
            temp *= nums[i];
        }

        /* print the constructed prod array */
        for (i = 0; i < n; i++)
            System.out.print(prod[i] + " ");

        return prod;
    }

    public String stringShift(String s, int[][] shift) {
        for (int i = 0; i < shift.length; i++) {
            boolean isLeft = shift[i][0] == 0 ? true : false;
            boolean isRight = shift[i][0] == 1 ? true : false;
            int strLength = s.length();

            int numberOfshifts = shift[i][1];
            if (isLeft) {
                for (int move = 0; move < numberOfshifts; move++) {
                    char charToMove = s.charAt(0);
                    s = s.substring(1, strLength);
                    s = s + charToMove;
                    System.out.println(s);
                }
            } else if (isRight) {
                for (int move = 0; move < numberOfshifts; move++) {
                    char charToMove = s.charAt(strLength - 1);
                    s = s.substring(0, strLength - 1);
                    s = charToMove + s;
                    System.out.println(s);
                }

            }

            System.out.println(s);
        }
        return s;
    }

    public int findMaxLength(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int maxlen = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 1 ? 1 : -1);
            if (map.containsKey(count)) {
                maxlen = Math.max(maxlen, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return maxlen;
    }

    public int lastStoneWeight(int[] stones) {
        while (stones.length > 1) {
            Arrays.sort(stones);
            int len = stones.length;
            int x = stones[len - 2];
            int y = stones[len - 1];
            if (x == y) {
                stones = Arrays.copyOfRange(stones, 0, len - 2);
            } else {
                stones[len - 2] = y - x;
                stones = Arrays.copyOfRange(stones, 0, len - 1);
            }
        }
        if (stones.length == 0)
            return 0;
        else
            return stones[0];
    }

    /*The function Compute the "height" of a tree. Height is the
       number f nodes along the longest path from the root node
       down to the farthest leaf node.*/
    static int height(TreeNode node) {
        /* base case tree is empty */
        if (node == null)
            return 0;

        /* If tree is not empty then height = 1 + max of left
           height and right heights */
        return (1 + Math.max(height(node.left), height(node.right)));
    }

    public int diameterOfBinaryTree(TreeNode root) {

        /* base case if tree is empty */
        if (root == null)
            return 0;

        /* get the height of left and right sub trees */
        int lheight = height(root.left);
        int rheight = height(root.right);

        /* get the diameter of left and right subtrees */
        int ldiameter = diameterOfBinaryTree(root.left);
        int rdiameter = diameterOfBinaryTree(root.right);
        System.out.println(" root Value..." + root.val);
        System.out.println(" right-height.." + rheight);
        System.out.println(" left-height.." + lheight);
        System.out.println(" left-diameter.." + ldiameter);
        System.out.println(" right-height.." + rdiameter);
        /* Return max of following three
          1) Diameter of left subtree
         2) Diameter of right subtree
         3) Height of left subtree + height of right subtree + 1 */
        return Math.max(lheight + rheight,
                Math.max(ldiameter, rdiameter));


    }

    public int handleLeftNode(TreeNode leftNode) {
        int leftLevel = 0;
        if (leftNode != null && leftNode.left == null)
            return 1;
        while (leftNode.left != null) {
            leftLevel++;
            leftNode = leftNode.left;
        }
        return leftLevel;
    }

    public int handleRightNode(TreeNode rightNode) {
        int rightLevel = 0;
        if (rightNode != null && rightNode.right == null)
            return 1;
        while (rightNode.right != null) {
            rightLevel++;
            rightNode = rightNode.right;
        }
        return rightLevel;
    }

    public boolean backspaceCompare(String S, String T) {

        int i = S.length() - 1;
        int j = T.length() - 1;

        while (i >= 0 || j >= 0) {
            int c1 = 0;
            while (i >= 0 && (c1 > 0 || S.charAt(i) == '#')) {
                if (S.charAt(i) == '#') {
                    c1++;
                } else {
                    c1--;
                }

                i--;
            }

            int c2 = 0;
            while (j >= 0 && (c2 > 0 || T.charAt(j) == '#')) {
                if (T.charAt(j) == '#') {
                    c2++;
                } else {
                    c2--;
                }

                j--;
            }

            if (i >= 0 && j >= 0) {
                if (S.charAt(i) != T.charAt(j)) {
                    return false;
                } else {
                    i--;
                    j--;
                }
            } else {
                if (i >= 0 || j >= 0) {
                    return false;
                }
            }
        }

        return i < 0 && j < 0;

    }

    public ListNode middleNode(ListNode head) {
//        ListNode slow_ptr = head;
//        ListNode fast_ptr = head;
//        if (head != null) {
//            while (fast_ptr != null && fast_ptr.next != null) {
//                fast_ptr = fast_ptr.next.next;
//                slow_ptr = slow_ptr.next;
//            }
//            System.out.println("The middle element is [" +
//                    slow_ptr.val + "] \n");
//        }
//        return slow_ptr;

        ListNode[] A = new ListNode[100];
        int t = 0;
        while (head.next != null) {
            A[t++] = head;
            head = head.next;
        }
        System.out.println(" t / 2  A[t/2] " + A[t / 2]);
        return A[t / 2];
    }

    // Function to insert nodes in level order
    public TreeNode insertLevelOrder(String[] arr, TreeNode root,
                                     int i) {
        // Base case for recursion
        if (i < arr.length && arr[i] != null) {
            TreeNode temp = new TreeNode(Integer.parseInt(arr[i]));
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
}

//Definition for singly-linked list.
class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public ListNode push(int new_data, ListNode head) {
        /* 1 & 2: Allocate the Node &
                  Put in the data*/
        ListNode new_node = new ListNode(new_data);

        /* 3. Make next of new Node as head */
        new_node.next = head;

        /* 4. Move the head to point to new Node */
        head = new_node;
        return head;
    }

    public void printList(ListNode head) {
        ListNode tnode = head;
        while (tnode != null) {
            System.out.print(tnode.val + "->");
            tnode = tnode.next;
        }
        System.out.println("NULL");
    }


}

class Elem {
    public int value;
    public int min;
    public Elem next;

    public Elem(int value, int min) {
        this.value = value;
        this.min = min;
    }
}

class MinStack {
    public Elem top;

    /**
     * initialize your data structure here.
     */
    public MinStack() {

    }

    public void push(int x) {
        if (top == null) {
            top = new Elem(x, x);
        } else {
            Elem e = new Elem(x, Math.min(x, top.min));
            e.next = top;
            top = e;
        }

    }

    public void pop() {
        if (top == null)
            return;
        Elem temp = top.next;
        top.next = null;
        top = temp;

    }

    public int top() {
        if (top == null)
            return -1;
        return top.value;
    }

    public int getMin() {
        if (top == null)
            return -1;
        return top.min;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    public void printPreorder(TreeNode node) {
        if (node == null)
            return;
        /* first print data of node */
        System.out.print(node.val + " ");
        /* then recur on left sutree */
        printPreorder(node.left);
        /* now recur on right subtree */
        printPreorder(node.right);
    }

}

class BinaryMatrix {

    int[][] a;

    BinaryMatrix(int[][] a) {
        this.a = a;
    }

    public int get(int x, int y) {
        return a[x][y];
    }

    public List<Integer> dimensions() {
        List array = new ArrayList<Integer>();
        array.add(a.length);
        array.add(a[0].length);
        return array;
    }
}

