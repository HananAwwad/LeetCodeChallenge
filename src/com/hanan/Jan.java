package com.hanan;

import java.util.HashSet;
import java.util.Set;

public class Jan {
    public static void main(String[] aa) {
        //  System.out.println(new DecChallenge().partition(""));
        //  System.out.println(new DecChallenge().sortedSquares(new int[]{-4, -1, 0, 3, 10}));
        //  System.out.println(new DecChallenge().isValidBST(new TreeNode(5)));
        //System.out.println(new DecChallenge().decodeAtIndex("leet2code3", 15));
        System.out.println(new Jan().findKthPositive(new int[]{2, 3, 4, 7, 11}, 5));
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
