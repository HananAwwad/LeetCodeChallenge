package com.hanan;

import java.util.*;

public class PetasenseInterview {
    public static void main(String[] aa) {
        System.out.println(" burstBallons result......"+new PetasenseInterview().burstBallons(new int[]{0, 4, 4}));
        System.out.println("permutation result "+new PetasenseInterview().permutations(new int[]{1,2,3}));
    }
    public int burstBallons(int[] ballonHeights) {
        int minShots = 0;

        if (ballonHeights.length == 0)
            return 0;

        Map<Integer, Integer> map = new HashMap();

        for (int i = 0; i < ballonHeights.length; i++) {
            map.put(i, ballonHeights[i]);
        }

        while (!map.isEmpty()) {
            minShots++;

            Map.Entry<Integer, Integer> entry = map.entrySet().iterator().next();

            int arrowHeight = entry.getValue();

            Iterator it = map.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if (arrowHeight == (int) pair.getValue()) {
                    System.out.println("Deleting the following pairs from the map " + pair.getKey() + " = " + pair.getValue());
                    arrowHeight--;
                    it.remove();
                }
            }


        }
        return minShots;
    }


    public List<List<Integer>> permutations(int[] nums) {
        Integer[] list = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            list[i] = nums[i];
        }
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        findPermutationRecursive(list, 0, result);
        return result;
    }

    void findPermutationRecursive(Integer nums[], int index, ArrayList<List<Integer>> result) {

        if (index == nums.length) {
            result.add(new ArrayList<Integer>(Arrays.asList(nums)));
        }

        for (int i = index; i < nums.length; i++) {

            int temp = nums[i];
            nums[i] = nums[index];
            nums[index] = temp;

            findPermutationRecursive(nums, index + 1, result);

            temp = nums[i];
            nums[i] = nums[index];
            nums[index] = temp;
        }
    }


}
