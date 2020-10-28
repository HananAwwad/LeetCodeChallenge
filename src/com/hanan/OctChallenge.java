package com.hanan;

import java.util.*;

public class OctChallenge {
    public static void main(String[] aa) {
        //System.out.println(new OctChallenge().buddyStrings("ab", "ab"));
        //System.out.println(new OctChallenge().findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        // System.out.println(new OctChallenge().maxProfit(1, new int[]{3, 2, 6, 5, 0, 3}));
        // System.out.println(new OctChallenge().minDominoRotations(new int[]{2, 1, 2, 4, 2, 2}, new int[]{5, 2, 6, 2, 3, 2}));
        //    System.out.println(new OctChallenge().find132pattern(new int[]{3, 1, 4, 2}));
        //    System.out.println(new OctChallenge().bagOfTokensScore(new int[]{100, 200, 300, 400}, 200));
        System.out.println(new OctChallenge().champagneTower(2 , 1  , 1));
    }

    public double champagneTower(int poured, int query_row, int query_glass) {
        if (poured == 0)
            return 0;
        List<Double> l = new ArrayList();
        l.add((double) poured);
        while (query_row -- > 0) {
            List<Double> temp = new ArrayList();
            temp.add(Math.max((l.get(0) - 1) / 2, 0));
            for (int i = 1; i < l.size(); i++) {
                temp.add(Math.max((l.get(i - 1) - 1) / 2, 0) + Math.max((l.get(i) -  1) / 2, 0));
            }
            temp.add(temp.get(0));
            l = new ArrayList<>(temp);

        }
        return Math.min(1, l.get(query_glass));
    }

    public int bagOfTokensScore(int[] tokens, int P) {
        Arrays.sort(tokens);
        int left = 0, right = tokens.length - 1;
        int score = 0;
        while (left < right) {
            if (P >= tokens[left]) {
                P -= tokens[left];
                score++;
            } else {
                if (right != left && score != 0 && (P + tokens[right] >= tokens[left])) {
                    P += tokens[right];
                    right--;
                    score--;
                } else
                    break;
            }
        }
        return score;

    }

    public boolean find132pattern(int[] nums) {
        if (nums.length < 3)
            return false;
        int[] min = new int[nums.length];
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++)
            min[i] = Math.min(min[i - 1], nums[i]);

        for (int j = nums.length - 1, k = nums.length; j >= 0; j--) {
            if (nums[j] > min[j]) {
                k = Arrays.binarySearch(nums, k, nums.length, min[j] + 1);
                if (k < 0)
                    k = -1 - k;
                if (k < nums.length && nums[k] < nums[j])
                    return true;
                nums[--k] = nums[j];
            }
        }
        return false;
    }

    public boolean find132pattern1(int[] nums) {

        int n = nums.length;
        int[] min = new int[n];
        min[0] = nums[0];
        for (int i = 1; i < n; i++)
            min[i] = Math.min(min[i - 1], nums[i]);

        Stack<Integer> stack = new Stack<>();
        for (int j = nums.length - 1; j >= 0; j--) {
            if (nums[j] > min[j]) {
                //check if nums[i] < nums[k]
                while (!stack.isEmpty() && stack.peek() <= min[j])
                    stack.pop();
                //check if nums[k] > nums[j]
                if (!stack.isEmpty() && stack.peek() < nums[j])
                    return true;
                stack.push(nums[j]);
            }
        }
        return false;
    }

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        else if (root.left == null && root.right == null) return 1;
        else if (root.left == null) return 1 + minDepth(root.right);
        else if (root.right == null) return 1 + minDepth(root.left);
        else return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }

    public int minDominoRotations(int[] A, int[] B) {

        int n = A.length;
        int[] faceA = new int[7], faceB = new int[7], same = new int[7];
        for (int i = 0; i < n; i++) {
            faceA[A[i]]++;
            faceB[B[i]]++;
            if (A[i] == B[i])
                same[A[i]]++;
        }
        int minRotation = Integer.MAX_VALUE;
        for (int i = 1; i < 7; i++) {
            if (faceA[i] + faceB[i] - same[i] == n)
                minRotation = Math.min(minRotation, Math.min(faceA[i], faceB[i]) - same[i]);
        }
        return minRotation == Integer.MAX_VALUE ? -1 : minRotation;
    }


    public int maxProfit(int k, int[] prices) {
        k = k > prices.length / 2 ? prices.length / 2 : k; // Case of Problem 122. Best Time to Buy and Sell Stock II
        int[] buy = new int[k + 1], sell = new int[k + 1];
        Arrays.fill(buy, Integer.MIN_VALUE);
        for (int price : prices) {
            for (int i = 1; i <= k; i++) {
                buy[i] = Math.max(buy[i], sell[i - 1] - price);
                sell[i] = Math.max(sell[i], buy[i] + price);
            }
        }
        return sell[k];
    }

    public int helper(int[] prices, int i, int buy, int txCount, int k) {
        if (i > prices.length || txCount >= k)
            return 0;
        if (buy == 1) {
            return Math.max(-prices[i] + helper(prices, i + 1, 0, txCount, k), helper(prices, i + 1, buy, txCount, k));
        } else
            return Math.max(prices[i] + helper(prices, i + 1, 1, txCount + 1, k), helper(prices, i + 1, buy, txCount, k));

    }

    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> seen = new HashSet(), repeated = new HashSet<>();
        for (int i = 0; i < s.length() - 9; i++) {
            String subString = s.substring(i, i + 10);
            if (seen.contains(subString))
                repeated.add(subString);

            seen.add(subString);
        }
        return new ArrayList<>(repeated);
    }

    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) return false;
        if (A.equals(B)) {

            Set<Character> set = new HashSet<>();
            for (char c : A.toCharArray()) {
                if (set.contains(c))
                    return true;
                set.add(c);

            }
            return false;
        } else {
            List<Integer> list = new ArrayList();
            for (int i = 0; i < A.length(); i++) {
                if (A.charAt(i) != B.charAt(i))
                    list.add(i);
            }
            return list.size() == 2 && A.charAt(list.get(0)) == B.charAt(list.get(1)) && A.charAt(list.get(1)) == B.charAt(list.get(0));
        }

    }

    public String removeDuplicateLetters(String s) {
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        boolean[] seen = new boolean[26];
        Stack<Integer> st = new Stack();
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if (seen[c])
                continue;

            while (!st.empty() && st.peek() > c && i < lastIndex[st.peek()])
                seen[st.pop()] = false;

            st.push(c);
            seen[c] = true;
        }
        StringBuilder sb = new StringBuilder();
        while (!st.empty()) {
            sb.append((char) (st.pop() + 'a'));
        }
        return sb.reverse().toString();

    }

    public int findMinArrowShots(int[][] points) {

        if (points.length == 0) return 0;

        Arrays.sort(points, (a, b) -> (a[0] - b[0]));

        int arrows = 1;

        int pos = points[0][1];

        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= pos) {
                continue;
            }
            arrows++;
            pos = points[i][1];
        }

        return arrows;
    }

    public int search(int[] nums, int target) {

        int pivot, left = 0, right = nums.length - 1;
        while (left <= right) {
            pivot = left + (right - left) / 2;

            if (nums[pivot] == target)
                return pivot;

            if (target < nums[pivot]) right = pivot - 1;

            else left = pivot + 1;
        }
        return -1;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0)
            return head;
        int length = 0;
        ListNode tail = head;
        while (tail.next != null) {
            length++;
            tail = tail.next;
        }
        length++;
        tail.next = head;
        int rotatePoint = length - k % length;
        tail = head;

        while (rotatePoint-- > 1) {
            tail = tail.next;
        }
        head = tail.next;
        tail.next = null;
        return head;
    }

    public int bitwiseComplement(int N) {

        int bitlength = ((int) (Math.log(N) / Math.log(2)) + 1);
        int bitMask = (1 << bitlength) - 1;

        return N ^ bitMask;
    }

    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        int[] current = new int[]{-1, -1};
        int result = 0;
        for (int[] in : intervals) {
            if (in[0] > current[0] && in[1] > current[1]) {
                current[0] = in[0];
                result++;
            }
            current[1] = Math.max(current[1], in[1]);
        }
        return result;
    }

    public int findPairs(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int result = 0;

        for (int key : map.keySet()) {
            if (k == 0 && map.get(key) > 1)
                result++;
            if (k > 0 && map.containsKey(key + k))
                result++;
        }
        return result;
    }
}