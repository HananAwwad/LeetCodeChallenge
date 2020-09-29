package com.hanan.Sep;

import com.hanan.common.TreeNode;

import java.util.*;

public class SepChallenge {

    class Node {
        String key;
        double value;

        public Node(String key, double value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String a[]) {
//        System.out.println(new SepChallenge().largestOverlap(new int[][]{{1, 1, 0},
//                {0, 1, 0},
//                {0, 1, 0}}, new int[][]{{0, 0, 0},
//                {0, 1, 1},
//                {0, 0, 1}}));
        //System.out.println(new SepChallenge().wordPattern("abba", "dog cat cat dog"));
        // System.out.println(new SepChallenge().isIsomorphic("egg", "add"));
        //System.out.println(new SepChallenge().compareVersion("1.0.1", "1"));
        //System.out.println(new SepChallenge().getHint("1807", "7810"));
        // System.out.println(new SepChallenge().carPooling(new int[][]{{3, 2, 7}, {3, 7, 9}, {8, 3, 9}}, 11));
        // System.out.println(new SepChallenge().majorityElement(new int[]{1, 1, 1, 3, 3, 2, 2, 2}));
        //  System.out.println(new SepChallenge().canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
//        List<List<String>> equations = new ArrayList<>();
//        equations.add(Arrays.asList("a", "b"));
//        equations.add(Arrays.asList("b", "c"));
//        equations.add(Arrays.asList("bc", "cd"));
//        List<List<String>> queries = new ArrayList<>();
//        queries.add(Arrays.asList("a", "c"));
//        queries.add(Arrays.asList("c", "b"));
//        queries.add(Arrays.asList("bc", "cd"));
//        queries.add(Arrays.asList("cd", "bc"));
//        System.out.println(new SepChallenge().calcEquation(equations, new double[]{1.5, 2.5, 5.0}, queries));
        System.out.println(new SepChallenge().numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k < 0) return 0;
        int left = 0, right = 0, count = 0, prod = 1;

        while (right < nums.length) {
            prod *= nums[right];
            while (prod >= k) prod /= nums[left++];
            count += 1 + (right - left);
            right++;
        }
        return count;
    }

    public int numSubarrayProductLessThanK1(int[] nums, int k) {


        int counter = 0;
        for (int start = 0; start < nums.length; start++) {
            int prod = 1;

            for (int end = start; end < nums.length; end++) {
                prod *= nums[end];
                if (prod < k) {
                    counter++;
                } else
                    break;


            }
        }
        return counter;
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Node>> graph = buildGrap(equations, values);
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            result[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), new HashSet(), graph);
        }
        return result;
    }

    private double dfs(String s, String d, HashSet visited, Map<String, List<Node>> graph) {
        if (!graph.containsKey(s) && !graph.containsKey(d))
            return -1.0;
        if (s.equals(d))
            return 1.0;

        visited.add(s);
        for (Node ng : graph.get(s)) {
            if (!visited.contains(ng.key)) {
                double ans = dfs(ng.key, d, visited, graph);
                if (ans != -1.0)
                    return ans * ng.value;
            }
        }
        return -1.0;
    }

    Map<String, List<Node>> buildGrap(List<List<String>> equations, double[] values) {
        Map<String, List<Node>> graph = new HashMap();

        for (int i = 0; i < values.length; i++) {

            String src = equations.get(i).get(0);
            String desc = equations.get(i).get(1);
            graph.putIfAbsent(src, new ArrayList<>());
            graph.putIfAbsent(desc, new ArrayList<>());
            graph.get(src).add(new Node(desc, values[i]));
            graph.get(desc).add(new Node(src, 1 / values[i]));

        }
        return graph;
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int tank = 0, total = 0, index = 0;
        for (int i = 0; i < gas.length; i++) {
            int consume = gas[i] - cost[i];
            tank += consume;
            if (tank < 0) {
                tank = 0;
                index = i + 1;
            }
            total += consume;

        }
        return total < 0 ? -1 : index;
    }

    public List<Integer> majorityElement(int[] nums) {
        Integer candidate1 = null;
        Integer candidate2 = null;

        int count1 = 0;
        int count2 = 0;

        for (int n : nums) {
            if (candidate1 != null && candidate1 == n) {
                count1++;
            } else if (candidate2 != null && candidate2 == n) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = n;
                count1++;
            } else if (count2 == 0) {
                candidate2 = n;
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        List<Integer> result = new ArrayList<>();
        for (int n : nums) {
            if (candidate1 != null && candidate1 == n) {
                count1++;
            }
            if (candidate2 != null && candidate2 == n) {
                count2++;
            }
        }
        int p = nums.length / 3;
        if (count1 > p) result.add(candidate1);
        if (count2 > p) result.add(candidate2);
        return result;

    }

    public boolean carPooling(int[][] trips, int capacity) {

        Map<Integer, Integer> timestamp = new TreeMap<>();
        for (int[] trip : trips) {
            int start_passenger = timestamp.getOrDefault(trip[1], 0) + trip[0];
            timestamp.put(trip[1], start_passenger);

            int end_passenger = timestamp.getOrDefault(trip[2], 0) - trip[0];
            timestamp.put(trip[2], end_passenger);
        }
        int ued_capacity = 0;
        for (int passenger_change : timestamp.values()) {
            ued_capacity += passenger_change;
            if (ued_capacity > capacity) {
                return false;
            }
        }
        return true;

    }

    public String getHint(String secret, String guess) {
        int cows = 0, bulls = 0;

        int[] numbers = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';
            if (s == g)
                bulls++;
            else {
                if (numbers[s]++ < 0) cows++;
                if (numbers[g]-- > 0) cows++;
            }
        }
        return bulls + "A" + cows + "B";

    }

    public int compareVersion(String version1, String version2) {
        String[] ver1Strings = version1.split("\\.");
        String[] ver2Strings = version2.split("\\.");
        int max = Math.max(ver1Strings.length, ver2Strings.length);
        for (int i = 0; i < max; i++) {
            int v1 = (i < ver1Strings.length) ? Integer.parseInt(ver1Strings[i]) : 0;
            int v2 = (i < ver2Strings.length) ? Integer.parseInt(ver2Strings[i]) : 0;

            if (v1 < v2) {
                return -1;
            } else if (v1 > v2) {
                return 1;
            }
        }
        return 0;
    }


    public int sumRootToLeaf(TreeNode root) {

        return sum(root, 0);
    }

    public int sum(TreeNode root, int sum) {

        if (root == null)
            return 0;

        sum = (sum << 1) + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        } else
            return sum(root.left, sum) + sum(root.right, sum);
    }

    public boolean isIsomorphic(String s, String t) {

        int n = s.length(), i = 0;
        if (n != t.length())
            return false;

        Map<Character, Character> sMap = new HashMap();
        Map<Character, Character> tMap = new HashMap();

        while (i < n) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if (sMap.containsKey(sChar) != tMap.containsKey(tChar))
                return false;

            if (sMap.containsKey(sChar)) {
                if (sMap.get(sChar) != tChar || tMap.get(tChar) != sChar)
                    return false;
            } else {
                sMap.put(sChar, tChar);
                tMap.put(tChar, sChar);
            }

            i++;
        }

        return i == n;
    }

    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");

        int n = pattern.length(), i = 0;
        if (n != words.length)
            return false;

        Map<Character, String> chrarMap = new HashMap();
        Map<String, Character> stringMap = new HashMap();

        while (i < n) {
            char c = pattern.charAt(i);
            String word = words[i];
            if (chrarMap.containsKey(c) != stringMap.containsKey(word))
                return false;

            if (chrarMap.containsKey(c)) {
                if (!chrarMap.get(c).equals(word) || stringMap.get(word) != c)
                    return false;
            } else {
                chrarMap.put(c, word);
                stringMap.put(word, c);
            }

            i++;
        }

        return i == n;
    }


    private int shitAndCount(int[][] A, int[][] B) {
        int n = A.length;
        int count = 0;

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                int tmp = 0;
                for (int i = y; i < n; i++) {
                    for (int j = x; j < n; j++) {
                        if (A[i][j] == 1 && B[i - y][j - x] == 1) tmp++;
                    }
                }
                count = Math.max(tmp, count);
            }
        }

        return count;
    }

    public int largestOverlap(int[][] A, int[][] B) {
        return Math.max(shitAndCount(A, B), shitAndCount(B, A));

    }

}