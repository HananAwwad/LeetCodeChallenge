package com.hanan.Challenge2021;

import com.hanan.common.TreeNode;
import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class June {


    public static void main(String[] aa) {
        //System.out.println(new June().maxAreaOfIsland(new int[][]{{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}}));
        //System.out.println(new June().isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        //   System.out.println(new June().openLock(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202"));
        //  System.out.println(new June().maxPerformance(6, new int[]{2,10,3,1,5,8}, new int[]{5,4,3,9,7,2}, 2));
        //  System.out.println(new June().longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        //System.out.println(new June().minCostClimbingStairs(new int[]{10, 15, 20}));
        //System.out.println(new June().buildTree(new int[]{3,9,20,15,7}, new int []{9,3,15,20,7}));
        // System.out.println(new June().maxResult(new int[]{}, 1));
        // System.out.println(new June().maximumUnits(new int[][]{{}},10));
        //System.out.println(new June().makesquare(new int[]{1, 1, 2, 2, 2}));
        //System.out.println(new June().numSubarrayBoundedMax(new int[]{}, 2, 3));
        //System.out.println(new June().swimInWater(new int[][]{{0, 1, 2, 3, 4}, {24, 23, 22, 21, 5}, {12, 13, 14, 15, 16}, {11, 17, 18, 19, 20}, {10, 9, 8, 7, 6}}));
        //System.out.println(new June().getRow(0));
        //System.out.println(new June().numMatchingSubseq("", new String[]{"", ""}));
        //System.out.println(new June().countSmaller(new int[]{5, 2, 6, 1}));
        //System.out.println(new June().candy(new int[]{1,2,2}));
        //System.out.println(new June().removeDuplicates("azxxzy"));
        System.out.println(new June().longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3));
    }

    public int longestOnes(int[] nums, int k) {
        int maxConsecutiveOne = 0;
        int start = 0;
        int zeroCount = 0;
        for (int end = 0; end < nums.length; end++) {
            if (nums[end] == 0)
                zeroCount++;
            while (zeroCount > k) {
                if (nums[start] == 0) {
                    zeroCount--;
                }
                start++;
            }
            maxConsecutiveOne = Math.max(maxConsecutiveOne, end - start + 1);
        }
        return maxConsecutiveOne;

    }

    public String removeDuplicates(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == c) {
                sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public int count(int n) {
        return (n * (n + 1)) / 2;
    }

    public int candy(int[] ratings) {
        if (ratings.length <= 1) {
            return ratings.length;
        }
        int candies = 0;
        int up = 0;
        int down = 0;
        int oldSlope = 0;
        for (int i = 1; i < ratings.length; i++) {
            int newSlope = (ratings[i] > ratings[i - 1]) ? 1
                    : (ratings[i] < ratings[i - 1] ? -1
                    : 0);

            if ((oldSlope > 0 && newSlope == 0) || (oldSlope < 0 && newSlope >= 0)) {
                candies += count(up) + count(down) + Math.max(up, down);
                up = 0;
                down = 0;
            }
            if (newSlope > 0) {
                up++;
            } else if (newSlope < 0) {
                down++;
            } else {
                candies++;
            }

            oldSlope = newSlope;
        }
        candies += count(up) + count(down) + Math.max(up, down) + 1;
        return candies;
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        ArrayList<Integer> sorted = new ArrayList<Integer>();

        for (int i = nums.length - 1; i >= 0; i--) {
            if (sorted.isEmpty()) {
                sorted.add(nums[i]);
                result.add(0);
            } else if (nums[i] > sorted.get(sorted.size() - 1)) {
                sorted.add(sorted.size(), nums[i]);
                result.add(sorted.size() - 1);
            } else {
                int l = 0;
                int r = sorted.size() - 1;

                while (l < r) {
                    int m = l + (r - l) / 2;

                    if (nums[i] > sorted.get(m)) {
                        l = m + 1;
                    } else {
                        r = m;
                    }
                }

                sorted.add(r, nums[i]);
                result.add(r);
            }
        }

        Collections.reverse(result);

        return result;
    }

    public List<Integer> countSmaller1(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int[] sortedArray = nums;
        int lenght = sortedArray.length;
        Arrays.sort(sortedArray);
        Map<Integer, Integer> indexes = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            indexes.put(sortedArray[i], i);
        }
        for (int i = 0; i < nums.length; i++) {


            result.add(indexes.get(nums[i]));
        }

        return result;
    }

    /* Approach: For every word, check if it is subsequence of input string */

    public int numMatchingSubseq(String s, String[] words) {

        String inputString = s;
        int count = 0;

        // Check for every words in array

        for (String word : words) {

            // Check if word is subsequence of input string

            if (checkSubsequence(word, inputString)) {
                count = count + 1;
            }

        }

        return count;
    }


    /* Helper function to check if given word is sub sequence of given input string */

    private boolean checkSubsequence(String word, String inputString) {

        int prevCharIndex = 0;   // It will store the index of input String where previous char was found

        /*  So, the curr character should be found after this index for maintaining subsequence order  */

        for (char ch : word.toCharArray()) {

            int index = inputString.indexOf(ch, prevCharIndex);   // search for char after prev char found index

            // If index == -1 means char not found, else found at index i.

            if (index == -1) {
                return false;
            }

            prevCharIndex = index + 1;   // set the prevCharIndex to current found char index + 1 for next search

            // We do index + 1 as maybe duplicate elements consider this same index twice, so increment by 1.

        }

        return true;   // Every chars traversed and found, return true.

    }

    public List<Integer> getRow(int rowIndex) {
        Integer[] result = new Integer[rowIndex + 1];
        Arrays.fill(result, 0);
        result[0] = 1;
        for (int i = 1; i <= rowIndex; i++)
            for (int j = i; j > 0; j--)
                result[j] = result[j] + result[j - 1];

        return Arrays.asList(result);

    }

    static int[][] DIRS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int swimInWater(int[][] grid) {
        int N = grid.length, NN = N * N;
        int[] index = new int[NN];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                index[grid[i][j]] = i * N + j;

        UnionFind uf = new UnionFind(NN);
        int start = 0, end = N * N - 1;

        for (int t = 1; t < NN; t++) {
            int idx = index[t];
            int i = idx / N, j = idx % N;
            for (int[] dir : DIRS) {
                int i2 = i + dir[0], j2 = j + dir[1];
                if (i2 >= 0 && i2 < N && j2 >= 0 && j2 < N && grid[i2][j2] < t)
                    uf.union(idx, i2 * N + j2);
            }
            if (uf.connected(start, end)) return t;
        }
        return -1;
    }


    static class UnionFind {
        int[] parent;
        int[] size;
        public int count;

        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int p) {
            int root = p;
            while (root != parent[root])
                root = parent[root];
            while (p != root) {
                int newp = parent[p];
                parent[p] = root;
                p = newp;
            }
            return root;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;

            if (size[rootP] < size[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            } else {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
            count--;
        }
    }

    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int start = -1, end = -1, res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > right) {
                start = end = i;
                continue;
            }
            if (nums[i] >= left)
                end = i;
            res += end - start;
        }
        return res;

    }


    // The memoization cache to be used during recursion.
    public HashMap<Pair<Integer, Integer>, Boolean> memo = new HashMap<Pair<Integer, Integer>, Boolean>();

    // Array containing our matchsticks.
    public int[] nums;

    // Possible side of our square depending on the total sum of all the matchsticks. 
    public int possibleSquareSide;

    // Main DP function.
    public boolean recurse(Integer mask, Integer sidesDone) {
        int total = 0;
        int L = this.nums.length;

        // The memo key for this recursion
        Pair<Integer, Integer> memoKey = new Pair(mask, sidesDone);

        // Find out the sum of matchsticks used till now.
        for (int i = L - 1; i >= 0; i--) {
            if ((mask & (1 << i)) == 0) {
                total += this.nums[L - 1 - i];
            }
        }

        // If the sum if divisible by our square's side, then we increment our number of complete sides formed variable.
        if (total > 0 && total % this.possibleSquareSide == 0) {
            sidesDone++;
        }

        // Base case.
        if (sidesDone == 3) {
            return true;
        }


        // Return precomputed results
        if (this.memo.containsKey(memoKey)) {
            return this.memo.get(memoKey);
        }

        boolean ans = false;
        int c = total / this.possibleSquareSide;

        // Remaining vlength in the current partially formed side.
        int rem = this.possibleSquareSide * (c + 1) - total;

        // Try out all remaining options (that are valid)
        for (int i = L - 1; i >= 0; i--) {
            if (this.nums[L - 1 - i] <= rem && (mask & (1 << i)) > 0) {
                if (this.recurse(mask ^ (1 << i), sidesDone)) {
                    ans = true;
                    break;
                }
            }
        }

        // Cache the computed results.
        this.memo.put(memoKey, ans);
        return ans;
    }

    public boolean makesquare(int[] nums) {

        // Empty matchsticks.
        if (nums == null || nums.length == 0) {
            return false;
        }

        // Find the perimeter of the square (if at all possible)
        int L = nums.length;
        int perimeter = 0;
        for (int i = 0; i < L; i++) {
            perimeter += nums[i];
        }

        int possibleSquareSide = perimeter / 4;
        if (possibleSquareSide * 4 != perimeter) {
            return false;
        }

        this.nums = nums;
        this.possibleSquareSide = possibleSquareSide;
        return this.recurse((1 << L) - 1, 0);
    }

    public List<Integer> numbers;
    public int[] squareSums = new int[4];
    // public int possibleSquareSide;


    public boolean makesquare2(int[] nums) {
        // Empty matchsticks.
        if (nums == null || nums.length == 0) {
            return false;
        }

        // Find the perimeter of the square (if at all possible)
        int L = nums.length;
        int perimeter = 0;
        for (int i = 0; i < L; i++) {
            perimeter += nums[i];
        }

        this.possibleSquareSide = perimeter / 4;
        if (this.possibleSquareSide * 4 != perimeter) {
            return false;
        }

        // Convert the array of primitive int to ArrayList (for sorting).
        this.numbers = Arrays.stream(nums).boxed().collect(Collectors.toList());
        Collections.sort(this.numbers, Collections.reverseOrder());
        return this.dfs(0);
    }

    // Depth First Search function.
    public boolean dfs(int index) {

        // If we have exhausted all our matchsticks, check if all sides of the square are of equal length
        if (index == this.numbers.size()) {
            return squareSums[0] == squareSums[1] && squareSums[1] == squareSums[2] && squareSums[2] == squareSums[3];
        }

        // Get current matchstick.
        int element = this.numbers.get(index);

        // Try adding it to each of the 4 sides (if possible)
        for (int i = 0; i < 4; i++) {
            if (this.squareSums[i] + element <= this.possibleSquareSide) {
                this.squareSums[i] += element;
                if (this.dfs(index + 1)) {
                    return true;
                }
                this.squareSums[i] -= element;
            }
        }

        return false;
    }


    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, new Comparator<int[]>() {
            public int compare(int[] idx1, int[] idx2) {
                return Integer.compare(idx2[1], idx1[1]);
            }
        });

        int i = 0, units = 0;
        while (i < boxTypes.length && truckSize > 0) {
            units += boxTypes[i][1] * (Math.min(truckSize, boxTypes[i][0]));
            truckSize -= boxTypes[i][0];
            i++;
        }

        return units;
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
