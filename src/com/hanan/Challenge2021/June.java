package com.hanan.Challenge2021;

public class June {

    public static void main(String[] aa) {
        //System.out.println(new June().maxAreaOfIsland(new int[][]{{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}}));
        System.out.println(new June().isInterleave("aabcc", "dbbca", "aadbbbaccc"));
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
