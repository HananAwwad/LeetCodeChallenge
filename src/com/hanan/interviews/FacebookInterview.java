package com.hanan.interviews;

import com.hanan.common.Node;
import com.hanan.common.TreeNode;

import java.util.*;

public class FacebookInterview {
    public static void main(String[] a) {
        // aVeryBigSum(new ArrayList<Long>(){1000, 100});
        ///  System.out.println(new FacebookInterview().spiralOrder(new int[][]{}));
        //System.out.println(new FacebookInterview().rotationalCipher("abcdefghijklmNOPQRSTUVWXYZ0123456789", 39));
        //System.out.println(new FacebookInterview().countSubarrays(new int[]{3, 4, 1, 6, 2}));
        //System.out.println(new FacebookInterview().numberOfWays2(new int[]{1, 5, 3, 3, 3}, 6));
        //System.out.println(new FacebookInterview().canBeEqual(new int[]{1, 1, 1, 1, 1}, new int[]{1, 1, 1, 1, 1}));
        //System.out.println(new FacebookInterview().findSignatureCounts(new int[]{1, 2}));
        // System.out.println(new FacebookInterview().matchingPairs("mno", "mno"));
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
//        root.right.left = new TreeNode(25);
//        root.right.right = new TreeNode(40);
        // System.out.println(new FacebookInterview().leftView(root));
        ArrayList<Query> list = new ArrayList<Query>();
        list.add(new Query(1, 'a'));
        // System.out.println(new FacebookInterview().countOfNodes(root, list, "aba"));
        //  System.out.println(new FacebookInterview().getTotalTime(new int[]{4, 2, 1, 3}));
        //System.out.println(new FacebookInterview().findMinArray(new int[]{8, 9, 11, 2, 1}, 3));
//        int arr[] = {7, 6, 9, 2, 1};
//        int n = arr.length;
//        int k = 3;
//
//        minimizeWithKSwaps(arr, n, k);
//
//        //Print the final Array
//        for (int i = 0; i < n; ++i)
//            System.out.print(arr[i] + " ");

        // System.out.println(new FacebookInterview().isBalanced("{[()]}"));
        // System.out.println(new FacebookInterview().findPositions(new int[]{1, 2, 2, 3, 4, 5}, 5));
        //new FacebookInterview().reverse(new Node());
//        System.out.println(new FacebookInterview().minOperations(new int[]{3, 1, 2}));
//        System.out.println(minOperations(new int[] {3, 1, 2}) == 2);
//        System.out.println(minOperations(new int[] {4, 3, 1, 2}) == 2);
//        System.out.println(minOperations(new int[] {6, 1, 2, 3, 4, 5}) == 2);
        // System.out.println(new FacebookInterview().findEncryptedWord("abcxcba"));
        // System.out.println(new FacebookInterview().canGetExactChange(94, new int[]{5, 10, 25, 100, 200}));
        //System.out.println(new FacebookInterview().getMilestoneDays(new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100}, new int[]{100, 200, 500}));

        //System.out.println(new FacebookInterview().getBillionUsersDay(new float[]{(float) 1.1, (float) 1.2, (float) 1.3}));
        //System.out.println(new FacebookInterview().findMaxProduct(new int[]{1, 2, 3, 4, 5}));
        // System.out.println(new FacebookInterview().maxCandies(new int[]{2,1,7,4,2}, 3));
        //System.out.println(new FacebookInterview().findMedian(new int[]{5, 15, 1, 3}));
        ArrayList<Sides> sides = new ArrayList<>();
        sides.add(new Sides(2, 2, 3));
        sides.add(new Sides(3, 2, 2));
        sides.add(new Sides(2, 5, 6));
        //System.out.println(new FacebookInterview().countDistinctTriangles(sides));
        // System.out.println(new FacebookInterview().balancedSplitExists(new int[]{12, 7, 6, 7, 6}));
        System.out.println(new FacebookInterview().getLocalMin(new int[]{0}));

    }// Given an array of integers,
// A Local minimum is defined as element that is less than or equals to its neighbors
// Find any one local minimum from the array

// for example, here
// [5, 9, 7, 10, 12]
// 5 and 7 are local minimums
// returning either 5 or 7 works

//[8,6,5,1]...O(n)...T(C).... O(1)
// [5, 9, 7, 10, 12]
//[8,6,5,1]...1
// [0,0,0,0]......0
// [9,-2,3,-6]......-2
// [3,0]......0
// [1].....1

    int getLocalMin(int[] arr) {
        int currentElement = arr[0];
        if (arr.length == 1)
            return currentElement;
        for (int i = 0; i < arr.length; i++) {

            int beforeElement;
            currentElement = arr[i];
            int afterElement;

            if (i == 0) {
                afterElement = arr[i + 1];
                if (currentElement <= afterElement)
                    return currentElement;
            } else if (i == arr.length - 1) {
                beforeElement = arr[i - 1];
                if (currentElement <= beforeElement)
                    return currentElement;
            } else {
                beforeElement = arr[i - 1];
                afterElement = arr[i + 1];
                if (currentElement <= beforeElement && currentElement <= afterElement)
                    return currentElement;
            }
        }
        return currentElement;

    }


    boolean balancedSplitExists(int[] arr) {
        int len = arr.length;
        if (len == 0) {
            return false;
        }

        Arrays.sort(arr);
        int totalSum = 0;
        int remaining = arr[len - 1];
        for (int i = 0; i < len - 1; i++) {
            totalSum += arr[i];
        }

        for (int i = len - 2; i >= 0; i--) {
            if (totalSum == remaining && arr[i] < arr[i + 1]) return true;
            if (totalSum < remaining) break;

            totalSum -= arr[i];
            remaining += arr[i];
        }

        return false;
    }

    int countDistinctTriangles(ArrayList<Sides> arr) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < arr.size(); i++) {
            Sides t = arr.get(i);
            int[] sides = new int[]{t.a, t.b, t.c};
            Arrays.sort(sides);
            String key = Arrays.toString(sides);
            set.add(key);
        }
        return set.size();
    }

    static class Sides {
        int a;
        int b;
        int c;

        public Sides(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    int[] findMedian(int[] arr) {
        if (arr == null || arr.length == 0) return null;

        Queue<Integer> q = new PriorityQueue<>();

        int[] output = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            q.offer(arr[i]);

            int len = q.size();
            int mid = -1;


            int[] temp = new int[len];
            for (int j = 0; j < len; j++) {
                temp[j] = q.poll();
            }


            if (len % 2 == 0) {
                mid = len / 2 - 1;
                output[i] = (temp[mid] + temp[mid + 1]) / 2;
            } else {
                mid = len / 2;
                output[i] = temp[mid];
            }

            for (int j = 0; j < len; j++) {
                q.offer(temp[j]);
            }

        }

        return output;
    }

    int maxCandies(int[] arr, int k) {
        // Write your code here
        PriorityQueue<Integer> maxValue = new PriorityQueue<>(Collections.reverseOrder());

        //nlog(n)
        for (int i : arr) {
            maxValue.add(i);
        }


        //klog(k);
        int sum = 0;
        while (k > 0) {
            int cur = maxValue.poll();
            sum += cur;
            maxValue.add(cur / 2);
            k--;
        }

        return sum;
    }


    int[] findMaxProduct(int[] arr) {
        int[] result = new int[arr.length];
        // call a priority queue
        PriorityQueue<Integer> q = new PriorityQueue(Collections.reverseOrder());

        // traversing the array
        for (int i = 0; i < arr.length; i++) {
            // pushing arr[i] in array
            q.add(arr[i]);

            // if less than three elements are present
            // in array print -1
            if (q.size() < 3) {
                System.out.println("-1");
                result[i] = -1;
            } else {
                // pop three largest elements
                int x = q.poll();
                int y = q.poll();
                int z = q.poll();

                // Reinsert x, y, z in priority_queue
                int ans = x * y * z;
                System.out.println(ans);
                result[i] = ans;
                q.add(x);
                q.add(y);
                q.add(z);
            }

        }
        return result;
    }

    int minOverallAwkwardness(int[] arr) {
        Arrays.sort(arr);
        int diff = arr[1] - arr[0];

        for (int i = 2; i < arr.length; i += 2) {
            diff = Math.max(diff, arr[i] - arr[i - 2]);
        }
        for (int i = 3; i < arr.length; i += 2) {
            diff = Math.max(diff, arr[i] - arr[i - 2]);
        }

        return Math.max(diff, arr[arr.length - 1] - arr[arr.length - 2]);
    }

    // GP n-th element formula: [a * (r^ (n- 1)]
    // In this case as a == r, it simplifies to r^n
    private double userOnDay(float rate, int day) {
        return Math.pow(rate, day);
    }

    public int getBillionUsersDay(float[] growthRates) {
        // Write your code here
        int start = 1;
        int end = Integer.MAX_VALUE; // considering this to be the upper_limit; can be discussed with the interviewer
        double target = 1000000000;
        double target1 = 1000000000;

        while (start < end) {
            double total = 0;
            int mid = start + (end - start) / 2;

            // calculate mid value
            for (float growthRate : growthRates) {
                total += userOnDay(growthRate, mid);
            }

            if (total == target) {
                return mid;
            }
            if (total > target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }


    int searchDays(int[] revenues, int target) {
        int l = 0;
        int r = revenues.length - 1;
        while (l < r) {
            int mid = l + ((r - l) / 2);
            if (revenues[mid] == target)
                return mid;
            else if (revenues[mid] > target)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }

    int[] getMilestoneDays(int[] revenues, int[] milestones) {
        int[] result = new int[milestones.length];
        for (int i = 1; i < revenues.length; i++) {
            revenues[i] += revenues[i - 1];
        }
        for (int x = 0; x < milestones.length; x++) {
            result[x] = searchDays(revenues, milestones[x]) + 1;
        }

        return result;
    }

    int[] getMilestoneDays1(int[] revenues, int[] milestones) {
        int[] result = new int[milestones.length];

        for (int i = 0; i < milestones.length; i++) {
            int currentRevenues = 0;

            for (int j = 0; j < revenues.length; j++) {
                currentRevenues += revenues[j];
                if (currentRevenues >= milestones[i]) {
                    result[i] = j + 1;
                    break;
                }
            }

        }
        return result;
    }

    boolean canGetExactChange(int targetMoney, int[] denominations) {
        if (targetMoney < 0)
            return false;
        if (targetMoney == 0)
            return true;
        for (int i = 0; i < denominations.length; i++) {
            if (canGetExactChange(targetMoney - denominations[i], denominations)) {
                return true;
            }
        }
        return false;

    }

    boolean canGetExactChange1(int targetMoney, int[] denominations) {
        Arrays.sort(denominations);
        for (int i = denominations.length - 1; i >= 0; i--) {
            if (denominations[i] > targetMoney)
                continue;
            targetMoney = substractFrom(targetMoney, denominations[i]);
            if (targetMoney == 0)
                return true;
        }

        return false;
    }

    int substractFrom(int target, int denomination) {
        while (target >= denomination)
            target -= denomination;
        return target;
    }

    String findEncryptedWord(String s) {
        int length = s.length();
        int middle = length / 2;
        if (middle % 2 == 0)
            middle = middle - 1;
        if (length == 0)
            return "";
        if (length == 1)
            return s;

        return s.charAt(middle) + findEncryptedWord(s.substring(0, middle)) + findEncryptedWord(s.substring(middle + 1, length));
    }

    private static void reverse(int[] arr, int l, int r) {
        // reverse array [i,...,j]
        while (l < r) {
            int xor = arr[l] ^ arr[r];
            arr[l++] = xor ^ arr[l];
            arr[r--] = xor ^ arr[r];
        }
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != i + 1) return false;
        }
        return true;
    }

    static int minOperations(int[] arr) {
        int operations = 0;
        if (arr == null || arr.length == 0 || isSorted(arr)) return operations;
        Set<String> visited = new HashSet<>();
        visited.add(Arrays.toString(arr));

        Queue<int[]> queue = new LinkedList<>();
        queue.add(arr);

        while (!queue.isEmpty()) {
            operations++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                int[] cur = queue.poll();
                for (int l = 0; l < cur.length; l++) {
                    for (int r = l + 1; r < cur.length; r++) {
                        int[] next = Arrays.copyOf(cur, cur.length);
                        reverse(next, l, r);
                        String key = Arrays.toString(next);
                        if (visited.contains(key)) continue;
                        if (isSorted(next)) return operations;
                        visited.add(key);
                        queue.add(next);
                    }
                }
            }
        }
        return -1; // not reach here
    }

    public void reverse1(int[] arr, int from, int to) {
        while (from < to) {
            int tmp = arr[from];
            arr[from] = arr[to];
            arr[to] = tmp;
            from++;
            to--;
        }
    }

    int minOperations1(int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] - 1 != i) {
                int dif = arr[i] - 1;
                result++;
                reverse(arr, i, dif);
                i--;
            }
        }
        return result;
    }

    Node reverse(Node head) {
        Node dummy = new Node(0);
        dummy.next = head;

        Node prev = dummy;
        Node curr = head;

        while (curr != null) {
            if (curr.val % 2 == 0) {
                prev.next = reverseOdds(curr);
            }

            prev = curr;
            curr = curr.next;
        }

        return dummy.next;

    }

    Node reverseOdds(Node head) {
        Node prev = null;
        Node curr = head;

        while (curr != null && curr.val % 2 == 0) {
            Node t = curr.next;
            curr.next = prev;

            prev = curr;
            curr = t;
        }

        head.next = curr;
        return prev;
    }

    class Position {
        int index;
        int val;

        Position(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }

    int[] findPositions(int[] arr, int x) {
        int[] output = new int[x];
        Queue<Position> positions = new LinkedList();
        for (int i = 0; i < arr.length; i++) {
            positions.add(new Position(i + 1, arr[i]));
        }
        List<Position> popped;
        int pass = 0;
        for (int i = 0; i < x; i++) {

            popped = new ArrayList<>();
            // Step 1. Pop x elements
            for (int j = 0; j < x && !positions.isEmpty(); j++) {
                popped.add(positions.poll());
            }
            // Step 2. Remove Largest element
            int max = 0;
            int maxIdx = Integer.MAX_VALUE;
            for (Position p : popped) {
                if (p.val == max) {
                    maxIdx = Math.min(maxIdx, p.index);
                } else if (p.val > max) {
                    max = p.val;
                    maxIdx = p.index;
                }
            }

            output[pass++] = maxIdx;

            // Step 3. Decrement Values & Add Back
            for (Position p : popped) {
                if (p.index != maxIdx) { // remove from queue
                    Position next = new Position(p.index, (p.val == 0) ? p.val : p.val - 1);
                    positions.add(next);
                }
            }

        }


        return output;

    }

    boolean isBalanced(String a) {
        Stack<Character> stack = new Stack();
        Map<Character, Character> map = new HashMap();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        for (int i = 0; i < a.length(); i++) {

            char c = a.charAt(i);
            if (map.containsKey(c)) {
                stack.push(map.get(c));
            } else if (map.containsValue(c)) {
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }

            }

        }
        return stack.isEmpty();
    }


    // Modifies arr[0..n-1] to lexicographically
    // smallest with k swaps.
    static void minimizeWithKSwaps(int arr[], int n, int k) {
        for (int i = 0; i < n - 1 && k > 0; ++i) {

            // Set the position where we want
            // to put the smallest integer
            int pos = i;
            for (int j = i + 1; j < n; ++j) {

                // If we exceed the Max swaps
                // then terminate the loop
                if (j - i > k)
                    break;

                // Find the minimum value from i+1 to
                // max k or n
                if (arr[j] < arr[pos])
                    pos = j;
            }

            // Swap the elements from Minimum position
            // we found till now to the i index
            int temp;

            for (int j = pos; j > i; --j) {
                temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
            }

            // Set the final value after swapping pos-i
            // elements
            k -= pos - i;
        }
    }


    public int[] findMinArray(int[] arr, int k) {
        for (int i = 0; i < arr.length && k > 0; i++) {
            int minIndex = findMinAtDistanceK(arr, i, k);
            //if minimum element is already at position i, nothing to do
            if (minIndex == i) {
                continue;
            }
            swap(arr, i, minIndex);
            // we have used up minindex-i swaps
            k -= minIndex - i;
        }
        return arr;
    }

    private int findMinAtDistanceK(int[] arr, int start, int k) {
        int index = 0, min = Integer.MAX_VALUE;
        // find minimum element at distance k from start
        for (int i = start; i <= start + k; i++) {
            if (arr[i] < min) {
                min = arr[i];
                index = i;
            }
        }
        return index;
    }

    private void swap(int[] arr, int start, int end) {
        //move element at position end to start
        while (end > start) {
            int temp = arr[end];
            arr[end] = arr[end - 1];
            arr[end - 1] = temp;
            end--;
        }
    }

    int getTotalTime(int[] arr) {
        Arrays.sort(arr);
        int sum = arr[arr.length - 1];
        int penalty = 0;
        for (int i = arr.length - 2; i >= 0; i--) {
            sum += arr[i];
            penalty += sum;
        }
        return penalty;
    }

    private int getCount(TreeNode node, Query q, String s) {
        if (node == null) {
            return 0;
        }
        int count = 0;
        if (s.charAt(node.val - 1) == q.c) {
            count++;
        }
        count += getCount(node.left, q, s);
        count += getCount(node.right, q, s);

        return count;
    }

    private TreeNode findNode(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }

        TreeNode left = findNode(root.left, val);
        TreeNode right = findNode(root.right, val);

        if (left != null)
            return left;
        else if (right != null)
            return right;

        return null;
    }

    int[] countOfNodes(TreeNode root, ArrayList<Query> queries, String s) {
        int[] result = new int[queries.size()];
        int i = 0;
        for (Query q : queries) {
            TreeNode TreeNode = findNode(root, q.u);

            result[i++] = getCount(TreeNode, q, s);
        }
        return result;
    }

    int[] countOfNodes1(TreeNode root, ArrayList<Query> queries, String s) {
        int[] result = new int[queries.size()];
        Map map = new HashMap();
        inOrderTraversal(root, 0, map, s);

        for (int i = 0; i < queries.size(); i++) {
            TreeNode subTree = findSubTreeQueries(root, queries.get(i).getU());
            int currentCount = 0;
            searchForQuery(subTree, queries.get(i).getC(), currentCount, map);
            result[i] = currentCount;
        }

        return result;
    }

    void searchForQuery(TreeNode node, char c, int count, Map map) {
    }

    TreeNode findSubTreeQueries(TreeNode node, int u) {

        if (node == null)
            return null;
        if (node.val == u) {
            return node;
        } else {
            TreeNode left = findSubTreeQueries(node.left, u);
            if (left != null)
                return left;
            TreeNode right = findSubTreeQueries(node.right, u);
            if (right != null)
                return right;
        }
        return null;
    }

    void inOrderTraversal(TreeNode node, int charIndex, Map map, String s) {
        if (node == null)
            return;
        map.put(node.val, s.charAt(charIndex));
        inOrderTraversal(node.left, charIndex + 1, map, s);
        inOrderTraversal(node.right, charIndex + 1, map, s);
    }

    static class Query {
        int u;
        char c;

        Query(int u, char c) {
            this.u = u;
            this.c = c;
        }

        public int getU() {
            return u;
        }

        public void setU(int u) {
            this.u = u;
        }

        public char getC() {
            return c;
        }

        public void setC(char c) {
            this.c = c;
        }

    }

    public List leftView(TreeNode root) {
        List result = new ArrayList();
        leftViewUtil(root, result, 0);
        return result;
    }

    int max_level = 0;

    public void leftViewUtil(TreeNode node, List l, int level) {
        if (node == null)
            return;

        // If this is the first node of its level
        if (max_level < level) {
            System.out.print(" " + node.val);
            l.add(node.val);
            max_level = level;
        }

        // Recur for left and right subtrees
        leftViewUtil(node.left, l, level + 1);
        leftViewUtil(node.right, l, level + 1);

    }

    public int matchingPairs(String s, String t) {
        Set<String> mismatchS = new HashSet<>();
        char[] sToChar = s.toCharArray();
        char[] tToChar = t.toCharArray();
        int matching = 0;
        for (int i = 0; i < sToChar.length; i++) {
            if (sToChar[i] != tToChar[i]) {
                mismatchS.add(sToChar[i] + "" + tToChar[i]);
            } else
                matching++;
        }
        for (String mism : mismatchS) {
            String reverse = mism.charAt(1) + "" + mism.charAt(0);
            if (mismatchS.contains(reverse)) {
                return matching + 2;
            }
        }
        if (mismatchS.size() <= 1)
            matching--;
        if (mismatchS.size() == 0)
            matching--;
        return matching;
    }

    public void swap(String s, char source, char dest) {
        char temp = source;
        s.replace(source, dest);
        s.replace(dest, temp);
    }

    public int[] findSignatureCounts(int[] arr) {
        // Write your code here
        int[] output = new int[arr.length];

        for (int student = 1; student <= arr.length; student++) {
            int bookOwner = student;
            int currentHolder = student;

            do {
                output[student - 1] += 1;
                currentHolder = arr[currentHolder - 1];
            } while (currentHolder != bookOwner);

        }
        return output;

    }

    public int[] findSignatureCount(int[] nums) {
        int[] res = new int[nums.length];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i + 1);
        }
        Set<Integer> visited = new HashSet<>();
        for (int book : map.keySet()) {
            if (!visited.contains(book)) {
                Set<Integer> round = new HashSet<>();
                while (!visited.contains(book)) {
                    round.add(book);
                    visited.add(book);
                    book = map.get(book);
                }
                for (int i : round) {
                    res[i - 1] = round.size();
                }
            }
        }
        return res;
    }

    public boolean canBeEqual(int[] target, int[] arr) {

        int[] map = new int[1001];
        for (int element : target) map[element]++;
        for (int element : arr) map[element]--;
        for (int element : map) if (element != 0) return false;
        return true;
    }

    public boolean areTheyEqual(int[] target, int[] arr) {

        if (target.length != arr.length)
            return false;
        int N = target.length;
        int[] result = new int[N];
        Set nonMatchingItems = new HashSet();
        for (int i = 0; i < N; i++) {
            if (target[i] == arr[i])
                result[i] = target[i];
            else
                nonMatchingItems.add(arr[i]);
        }
        for (int i = 0; i < N; i++) {
            if (result[i] == 0 && nonMatchingItems.contains(target[i])) {
                result[i] = target[i];
                nonMatchingItems.remove(result[i]);
            }
        }
        return (nonMatchingItems.size() == 0);
    }

    public int numberOfWays(int[] nums, int target) {
        int number = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                number++;
            }
        }
        return number;
    }

    public int numberOfWays2(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            int complement = k - nums[i];
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    int[] countSubarrays(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                int idx = stack.pop();
                ans[i] += ans[idx];
            }
            stack.push(i);
            ans[i]++;
        }
        stack.clear();
        int[] temp = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                int idx = stack.pop();
                ans[i] += temp[idx];
                temp[i] += temp[idx];
            }
            stack.push(i);
            temp[i]++;
        }
        return ans;
    }

    String rotationalCipher(String input, int rotationFactor) {
        StringBuilder sb = new StringBuilder(input.length());
        for (char ch : input.toLowerCase(Locale.ROOT).toCharArray()) {
            if (ch != ' ' && (ch - 'a') >= 0) {
                int OriginalCharacterIndex = ch - 'a';
                int NewCharacterIndex = (OriginalCharacterIndex + rotationFactor) % 26;
                char newcharacter = (char) ('a' + NewCharacterIndex);
                sb.append(newcharacter);
            } else {
                if (Character.isDigit(ch)) {
                    int newdigit = (int) ((ch - '0')) + rotationFactor;
                    if ((newdigit / 10) > 0) // if any carry digit
                    {
                        newdigit = (newdigit % 10);
                    }
                    sb.append(newdigit);
                } else {
                    sb.append(ch);
                }
            }
        }
        return sb.toString();
    }


    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int cnt = 1;
        int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int d = 0;
        int row = 0;
        int col = 0;
        while (cnt <= n * n) {
            result[row][col] = cnt++;
            int r = Math.floorMod(row + dir[d][0], n);
            int c = Math.floorMod(col + dir[d][1], n);

            // change direction if next cell is non zero
            if (result[r][c] != 0) d = (d + 1) % 4;

            row += dir[d][0];
            col += dir[d][1];
        }
        return result;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List ans = new ArrayList();
        if (matrix.length == 0)
            return ans;
        int beginRow = 0, endRow = matrix.length - 1;
        int beginCol = 0, endCol = matrix[0].length - 1;
        while (beginRow <= endRow && beginCol <= endCol) {

            for (int c = beginCol; c <= endCol; c++)
                ans.add(matrix[beginRow][c]);
            for (int r = beginRow + 1; r <= endRow; r++)
                ans.add(matrix[r][endCol]);

            if (beginRow < endRow && beginCol < endCol) {
                for (int c = endCol - 1; c > beginCol; c--)
                    ans.add(matrix[endRow][c]);
                for (int r = endRow; r > beginRow; r--)
                    ans.add(matrix[r][beginCol]);
            }
            beginRow++;
            endRow--;
            beginCol++;
            endCol--;
        }
        return ans;
    }

    public static long aVeryBigSum(List<Long> ar) {
        // Write your code here
        return 0;
    }

}
