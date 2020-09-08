package com.hanan.Sep;

import java.util.HashMap;
import java.util.Map;

public class SepChallenge {
    public static void main(String a[]) {
//        System.out.println(new SepChallenge().largestOverlap(new int[][]{{1, 1, 0},
//                {0, 1, 0},
//                {0, 1, 0}}, new int[][]{{0, 0, 0},
//                {0, 1, 1},
//                {0, 0, 1}}));
        //System.out.println(new SepChallenge().wordPattern("abba", "dog cat cat dog"));
        System.out.println(new SepChallenge().isIsomorphic("egg", "add"));

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