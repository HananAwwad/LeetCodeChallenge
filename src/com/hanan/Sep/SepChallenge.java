package com.hanan.Sep;

public class SepChallenge {
    public static void main(String a[]) {
        System.out.println(new SepChallenge().largestOverlap(new int[][]{{1, 1, 0},
                {0, 1, 0},
                {0, 1, 0}}, new int[][]{{0, 0, 0},
                {0, 1, 1},
                {0, 0, 1}}));
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