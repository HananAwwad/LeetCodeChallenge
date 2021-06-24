package com.hanan.interviews;

import java.util.*;

public class FacebookInterview {
    public static void main(String[] a) {
        // aVeryBigSum(new ArrayList<Long>(){1000, 100});
        ///  System.out.println(new FacebookInterview().spiralOrder(new int[][]{}));
        //System.out.println(new FacebookInterview().rotationalCipher("abcdefghijklmNOPQRSTUVWXYZ0123456789", 39));
        //System.out.println(new FacebookInterview().countSubarrays(new int[]{3, 4, 1, 6, 2}));
        //System.out.println(new FacebookInterview().numberOfWays2(new int[]{1, 5, 3, 3, 3}, 6));
        System.out.println(new FacebookInterview().canBeEqual(new int[]{1, 1, 1, 1, 1}, new int[]{1, 1, 1, 1, 1}));
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
