package com.hanan.Challenge2021;

import javafx.util.Pair;

import java.util.*;

public class Jan {
    public static void main(String[] aa) {
        //  System.out.println(new DecChallenge().partition(""));
        //  System.out.println(new DecChallenge().sortedSquares(new int[]{-4, -1, 0, 3, 10}));
        //  System.out.println(new DecChallenge().isValidBST(new TreeNode(5)));
        //System.out.println(new DecChallenge().decodeAtIndex("leet2code3", 15));
        //System.out.println(new Jan().findKthPositive(new int[]{2, 3, 4, 7, 11}, 5));
        //System.out.println(new Jan().ladderLength("hit", "cog", new ArrayList<>(Arrays.asList(new String[]{"hot", "dot", "dog", "lot", "log", "cog"}))));
        System.out.println(new Jan().countVowelStrings(3));
    }

    public int countVowelStrings(int n) {
        int[] dp = new int[5];
        Arrays.fill(dp, 1);
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            for (int j = 3; j >= 0; j--) {
                dp[j] = dp[j] + dp[j + 1];
            }
        }

        for (int el : dp) {
            ans += el;
        }
        return ans;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        // Since all words are of same length.
        int L = beginWord.length();

        // Dictionary to hold combination of words that can be formed,
        // from any given word. By changing one letter at a time.
        Map<String, List<String>> allComboDict = new HashMap<>();

        wordList.forEach(
                word -> {
                    for (int i = 0; i < L; i++) {
                        // Key is the generic word
                        // Value is a list of words which have the same intermediate generic word.
                        String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                        List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
                        transformations.add(word);
                        allComboDict.put(newWord, transformations);
                    }
                });

        // Queue for BFS
        Queue<Pair<String, Integer>> Q = new LinkedList<>();
        Q.add(new Pair(beginWord, 1));

        // Visited to make sure we don't repeat processing same word.
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);

        while (!Q.isEmpty()) {
            Pair<String, Integer> node = Q.remove();
            String word = node.getKey();
            int level = node.getValue();
            for (int i = 0; i < L; i++) {

                // Intermediate words for current word
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

                // Next states are all the words which share the same intermediate state.
                for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                    // If at any point if we find what we are looking for
                    // i.e. the end word - we can return with the answer.
                    if (adjacentWord.equals(endWord)) {
                        return level + 1;
                    }
                    // Otherwise, add it to the BFS Queue. Also mark it visited
                    if (!visited.containsKey(adjacentWord)) {
                        visited.put(adjacentWord, true);
                        Q.add(new Pair(adjacentWord, level + 1));
                    }
                }
            }
        }

        return 0;
    }

    public int findKthPositive(int[] arr, int k) {

        int low = 0;
        int high = arr.length;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (arr[mid] - (mid + 1) >= k) {
                high = mid;
            } else
                low = mid + 1;
        }
        return low + k;

    }
}
