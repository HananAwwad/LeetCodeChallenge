package com.hanan.Challenge2020.may;

import java.util.*;

public class Solution {

    int ans, count;
    TreeNode root;

    public static void main(String s[]) {
        // System.out.println(new Solution().firstBadVersion(5));
        // System.out.println(new Solution().findComplement(2));
        // System.out.printf("unique char " + new Solution().firstUniqChar("loveleetcode"));
        // System.out.println(new Solution().majorityElementBetterSol(new int[]{3, 2, 3}));
        // System.out.println(new Solution().checkStraightLine(new int[][]{{-4, -3}, {1, 0}, {3, -1}, {0, -1}, {-5, 2}}));
        //System.out.println(new Solution().isPerfectSquare(14));
        // System.out.println(new Solution().findJudge(4,new int[][]{{1,3},{1,4},{2,3},{2,4},{4,3}}));
        //System.out.println(new Solution().floodFill(new int[][]{{1, 2}}, 2, 1, 2));
        //System.out.println(new Solution().singleNonDuplicate(new int[]{3, 3, 7, 7, 10, 11, 11}));
        //System.out.println(new Solution().removeKdigits("", 4));

        //Your Trie object will be instantiated and called as such:
//        Trie obj = new Trie();
//        obj.insert("apple");
//        boolean param_2 = obj.search("apple");
//        boolean param_3 = obj.startsWith("app");
//        System.out.println("param_2    " + param_2 + "       param_3   " + param_3);
        // System.out.println(new Solution().maxSubarraySumCircular(new int[]{3, -1, 2, -1}));
        // System.out.println(new Solution().findAnagrams("cbaebabacd","abc"));
        //  System.out.println(new Solution().checkInclusion("ab", "eidbaooo"));

        // * Your StockSpanner object will be instantiated and called as such:
//        StockSpanner obj = new StockSpanner();
//        int[] aa = new int[]{12, 3, 9, 5, 6, 4, 7};
//        for (int i = 0; i < aa.length; i++) {
//            int param_1 = obj.next(aa[i]);
//            System.out.println(param_1);
//        }
//        System.out.println(" last value " + obj.next(10));
        //        System.out.println(new Solution().maxUncrossedLines(new int[]{2, 5, 1, 2, 5}, new int[]{10, 5, 2, 1, 5, 2}));
        System.out.println(new Solution().kClosest(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2));

    }

    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < word1.length() + 1; i++) {
            for (int j = 0; j < word2.length() + 1; j++) {
                dp[i][j] = -1;
            }
        }
        return ed(word1, word2, 0, 0, dp);
    }

    public int ed(String word1, String word2, int i, int j, int[][] dp) {

        if (dp[i][j] != -1)
            return dp[i][j];

        if (i == word1.length() || j == word2.length()) {
            return dp[i][j] = (i == word1.length()) ? word2.length() - j : word1.length() - i;

        }
        if (word1.charAt(i) == word2.charAt(j)) {
            return dp[i][j] = ed(word1, word2, i + 1, j + 1, dp);
        } else {
            return dp[i][j] = 1 + Math.min(
                    ed(word1, word2, i, j + 1, dp),
                    Math.min(ed(word1, word2, i + 1, j, dp),
                            ed(word1, word2, i + 1, j + 1, dp))
            );
        }
    }

    public int[][] kClosest(int[][] points, int K) {
        if (K == points.length)
            return points;

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> {
            int dist_a = (int) (Math.pow(a[0], 2) + Math.pow(a[1], 2));
            int dist_b = (int) (Math.pow(b[0], 2) + Math.pow(b[1], 2));
            return dist_a - dist_b;
        });

        for (int i = 0; i < points.length; i++) {
            pq.offer(new int[]{points[i][0], points[i][1]});
        }
        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) {
            int[] point = pq.poll();
            res[i][0] = point[0];
            res[i][1] = point[1];

        }
        return res;
    }


    List<Integer>[] adj;
    boolean[] visited;
    boolean[] marked;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        adj = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }
        visited = new boolean[numCourses];
        marked = new boolean[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            adj[prerequisites[i][0]].add(prerequisites[i][1]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                if (isCyclic(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean isCyclic(int i) {
        visited[i] = true;
        for (Integer j : adj[i]) {
            if (!visited[j]) {
                if (isCyclic(j)) {
                    return true;
                }
            } else if (!marked[j]) {
                return true;
            }
        }
        marked[i] = true;
        return false;
    }

    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        result[0] = 0;

        int idx = 0;
        int currentPow = 1;
        int nextPow = 2;
        while (idx < num) {
            idx++;
            if (nextPow == idx) {
                currentPow = nextPow;
                nextPow = nextPow << 1;
            }
            result[idx] = 1 + result[idx - currentPow];

        }
        return result;
    }

    public int maxUncrossedLines(int[] A, int[] B) {
        if (A == null || B == null
                || A.length == 0 || B.length == 0
        ) return 0;

        int[][] dp = new int[A.length + 1][B.length + 1];

        for (int i = 1; i <= A.length; i++) {

            for (int j = 1; j <= B.length; j++) {

                if (A[i - 1] == B[j - 1]) {

                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {

                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[A.length][B.length];
    }

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

        for (int i = 0; i < preorder.length; i++) insert(preorder[i]);

        return root;
    }

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

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> list = new ArrayList<>();
        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            int maxStart = Math.max(A[i][0], B[j][0]);
            int minEnd = Math.min(A[i][1], B[j][1]);
            if (maxStart <= minEnd) {
                list.add(new int[]{maxStart, minEnd});
            }
            if (A[i][1] < B[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        int[][] res = new int[list.size()][2];
        i = 0;
        for (int[] arr : list) {
            res[i++] = arr;
        }
        return res;
    }

    public String frequencySort(String s) {
        if (s == null || s.length() == 0)
            return s;

        int[] cnt_arr = new int[256];
        for (char c : s.toCharArray()) {
            cnt_arr[c]++;
        }
        PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> cnt_arr[b] - cnt_arr[a]);

        for (int i = 0; i < 256; i++) {
            pq.offer((char) i);
        }
        String sb = new String();
        while (!pq.isEmpty()) {
            Character c = pq.poll();
            for (int i = 0; i < cnt_arr[c]; i++) {
                sb += c;
            }
        }
        return sb.toString();


    }

    public int countSquares(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[2][n];
        int res = 0;
        int flag = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[flag][j] = matrix[i][j];
                } else {
                    dp[flag][j] = (matrix[i][j] == 1 ? Math.min(dp[flag ^ 1][j], Math.min(dp[flag ^ 1][j - 1], dp[flag][j - 1])) + 1 : 0);

                }
                res += dp[flag][j];
            }
            flag ^= 1;
        }

        return res;

    }

    public int kthSmallest(TreeNode root, int k) {
        inOrder(root, k, 0, 0);
        return ans;
    }

    void inOrder(TreeNode root, int k, int count, int ans) {
        if (root == null) return;
        inOrder(root.left, k, count, ans);
        count++;
        if (count == k) {
            ans = root.val;
            return;
        }
        inOrder(root.right, k, count, ans);
    }

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        int[] s1map = new int[26];
        int[] s2map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s2.length() - s1.length(); i++) {
            if (matches(s1map, s2map))
                return true;
            s2map[s2.charAt(i + s1.length()) - 'a']++;
            s2map[s2.charAt(i) - 'a']--;
        }
        return matches(s1map, s2map);
    }

    public boolean matches(int[] s1map, int[] s2map) {
        for (int i = 0; i < 26; i++) {
            if (s1map[i] != s2map[i])
                return false;
        }
        return true;
    }

    public boolean checkInclusion1(String s1, String s2) {

        if (s1.length() > s2.length())
            return false;
        int[] s1map = new int[26];
        int[] s2map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }
        int count = 0;
        for (int i = 0; i < 26; i++)
            if (s1map[i] == s2map[i])
                count++;
        for (int i = 0; i < s2.length() - s1.length(); i++) {
            int r = s2.charAt(i + s1.length()) - 'a', l = s2.charAt(i) - 'a';
            if (count == 26)
                return true;
            s2map[r]++;
            if (s2map[r] == s1map[r])
                count++;
            else if (s2map[r] == s1map[r] + 1)
                count--;
            s2map[l]--;
            if (s2map[l] == s1map[l])
                count++;
            else if (s2map[l] == s1map[l] - 1)
                count--;
        }
        return count == 26;
    }


    public List<Integer> findAnagrams(String wholeStr, String part) {
        int wholeStrLenght = wholeStr.length(), partLength = part.length();
        if (partLength > wholeStrLenght) return new ArrayList<>();

        int[] cnt_arr = new int[26];
        for (int i = 0; i < partLength; i++) {
            cnt_arr[wholeStr.charAt(i) - 'a']++;
            cnt_arr[part.charAt(i) - 'a']--;
        }

        List<Integer> result = new ArrayList<>();

        for (int i = partLength; i < wholeStrLenght; i++) {
            if (areAllZeros(cnt_arr)) {
                result.add(i - partLength);
            }
            cnt_arr[wholeStr.charAt(i) - 'a']++;
            cnt_arr[wholeStr.charAt(i - partLength) - 'a']--;
        }
        if (areAllZeros(cnt_arr)) {
            result.add(wholeStrLenght - partLength);
        }
        return result;
    }

    boolean areAllZeros(int[] cnt_arr) {
        for (int i = 0; i < cnt_arr.length; i++) {
            if (cnt_arr[i] != 0) return false;
        }
        return true;
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null)
            return null;

        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            odd = odd.next;
            even.next = even.next.next;
            even = even.next;

        }
        odd.next = evenHead;
        return head;

    }

    public int maxSubarraySumCircular(int[] A) {
        int maxSub = Integer.MIN_VALUE;
        int minSub = Integer.MIN_VALUE;
        int total = Integer.MAX_VALUE;
        int currentMax = 0;
        int currentMin = 0;

        for (int i = 0; i < A.length; i++) {
            currentMax += A[i];
            maxSub = Math.max(currentMax, maxSub);
            currentMax = Math.max(currentMax, 0);

            currentMin += A[i];
            minSub = Math.min(currentMin, minSub);
            currentMin = Math.min(currentMin, 0);

        }
        return maxSub < 0 ? maxSub : Math.max(maxSub, total - minSub);
    }

    public int maxSubArray(int[] nums) {
        int currentSub = 0;
        int maxSub = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            currentSub += nums[i];
            maxSub = Math.max(currentSub, maxSub);
            currentSub = Math.max(currentSub, 0);
        }
        return maxSub;
    }

    public String removeKdigits(String num, int k) {
        if (k == 0)
            return num;
        if (k == num.length())
            return "0";
        Stack<Character> stack = new Stack();
        for (char c : num.toCharArray()) {
            while (!stack.isEmpty() && k > 0 && stack.peek() > c) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }

        for (int i = 0; i < k; i++) {
            stack.pop();
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();

        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    public int singleNonDuplicate(int[] nums) {

        int si = 0, ei = nums.length - 1;

        while (si < ei) {
            int mid = si + (ei - si) / 2;
            if (nums[mid - 1] == nums[mid]) {
                boolean isEven = (ei - mid) % 2 == 0;
                if (isEven) {
                    ei = mid - 2;
                } else {
                    si = mid + 1;
                }
            } else if (nums[mid] == nums[mid + 1]) {

                boolean isEven = (ei - mid + 1) % 2 == 0;
                if (isEven) {
                    ei = mid - 1;
                } else {
                    si = mid + 2;
                }
            } else
                return nums[mid];
        }
        return nums[si];
    }


    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor)
            return image;

        dfs(image, sr, sc, image[sr][sc], newColor);

        return image;
    }

    public void dfs(int[][] image, int sr, int sc, int color, int newColor) {

        if (sr < 0 || sc >= image.length)
            return;
        if (sc < 0 || sc >= image[0].length)
            return;
        if (image[sr][sc] == newColor || image[sr][sc] != color)
            return;

        image[sr][sc] = newColor;

        dfs(image, sr - 1, sc, color, newColor);
        dfs(image, sr, sc - 1, color, newColor);
        dfs(image, sr + 1, sc, color, newColor);
        dfs(image, sr, sc + 1, color, newColor);

    }

    public int findJudge(int N, int[][] trust) {

        int[] inDegree = new int[N + 1];
        int[] outDegree = new int[N + 1];
        for (int i = 0; i < trust.length; i++) {
            outDegree[trust[i][0]]++;
            inDegree[trust[i][1]]++;

        }
        for (int i = 1; i <= N; i++) {
            if (outDegree[i] == 0 && inDegree[i] == (N - 1))
                return i;
        }
        return -1;
    }

    public boolean isPerfectSquare(int num) {

        if (num < 2) {
            return true;
        }
        long si = 2, ei = num / 2;
        while (si <= ei) {
            long mid = si + ((ei - si) / 2);
            long guessedNumber = mid * mid;
            if (guessedNumber == num)
                return true;
            else if (guessedNumber > num)
                ei = mid - 1;
            else
                si = mid + 1;


        }
        return false;


    }

    public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates.length == 2)
            return true;
        double slobe = (double) (coordinates[1][1] - coordinates[0][1]) / (coordinates[1][0] - coordinates[0][1]);

        for (int i = 1; i <= coordinates.length - 1; i++) {
            double currentSlobe = (double) (coordinates[i + 1][1] - coordinates[i][1]) / (coordinates[i + 1][0] - coordinates[i][0]);

            if (currentSlobe != slobe)
                return false;
        }

        return true;
    }


    public boolean isCousins(TreeNode root, int x, int y) {

        Pair xPair = getPair(root, x, null, 0);
        Pair yPair = getPair(root, y, null, 0);
        if (xPair.level == yPair.level && xPair.parent == yPair.parent) {
            return true;
        }
        return false;
    }

    public Pair getPair(TreeNode root, int val, TreeNode parent, int level) {
        if (root == null)
            return null;
        if (root.val == val)
            return new Pair(parent, level);

        Pair leftPair = getPair(root.left, val, root, level + 1);
        Pair rightPair = getPair(root.right, val, root, level + 1);

        return leftPair == null ? rightPair : leftPair;

    }

    public int majorityElementBetterSol(int[] nums) {

        int count = 0;
        int candadiate = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                candadiate = nums[i];
            }
            count += (candadiate == nums[i] ? 1 : -1);
        }
        return candadiate;
    }

    public int majorityElement(int[] nums) {
        int majorityElement = -1;
        Map map = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], (int) map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        int largestCount = 0;
        for (Object c : map.keySet()) {
            if ((int) map.get(c) > (nums.length / 2)) {
                if (largestCount < (int) map.get(c)) {
                    majorityElement = (int) c;
                    largestCount = (int) map.get(c);
                }
            }

        }

        return majorityElement;


    }

    public int firstUniqChar(String s) {

        int[] map = new int[26];
        for (char c : s.toCharArray()) {
            map[c - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map[c - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    public int findComplement(int num) {

        int bitlength = ((int) (Math.log(num) / Math.log(2)) + 1);
        int bitMask = (1 << bitlength) - 1;

        return num ^ bitMask;
    }

    public int numJewelsInStones(String J, String S) {
        int res = 0;
        Set<Character> unique = new HashSet<Character>();
        for (char c : J.toCharArray()) {
            unique.add(c);
        }
        for (int i = 0; i < S.toCharArray().length; i++) {
            if (unique.contains(S.toCharArray()[i])) {
                res++;
            }
        }

        return res;
    }

    public int firstBadVersion(int n) {
        int res = -1;
        int left = 1;
        int right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (new VersionControl().isBadVersion(mid)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return res;
    }
}

class VersionControl {
    boolean isBadVersion(int version) {

        if (version == 4) {
            return true;
        }
        return false;
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

class Pair {
    TreeNode parent;
    int level;

    Pair(TreeNode parent, int level) {
        this.parent = parent;
        this.level = level;
    }

}


class Trie {

    class Node {
        char c;
        Node[] arr;
        boolean b;

        Node(char c) {
            this.c = c;
            arr = new Node[26];
        }
    }

    Node root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        this.root = new Node('\0');
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Node node = root;
        for (char c : word.toCharArray()) {
            Node tmp = node.arr[c - 'a'];
            if (tmp == null) {
                tmp = new Node(c);
                node.arr[c - 'a'] = tmp;
            }
            node = tmp;
        }
        node.b = true;

    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Node node = root;
        for (char c : word.toCharArray()) {
            Node tmp = node.arr[c - 'a'];
            if (tmp == null) {
                return false;
            }
            node = tmp;
        }
        return node.b;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        Node node = root;
        for (char c : prefix.toCharArray()) {
            Node tmp = node.arr[c - 'a'];
            if (tmp == null) {
                return false;
            }
            node = tmp;
        }
        return true;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class StockSpanner {

    Stack<int[]> stk;

    public StockSpanner() {

        stk = new Stack<>();
    }

    public int next(int price) {
        int span = 1;
        while (!stk.isEmpty() && price >= stk.peek()[0]) {
            span += stk.peek()[1];
            stk.pop();
        }
        stk.push(new int[]{price, span});
        return span;
    }
}