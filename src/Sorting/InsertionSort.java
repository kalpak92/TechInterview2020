package Sorting;

import java.util.Arrays;

/**
 * @author kalpak
 *
 * Implementation of Insertion Sort
 *
 */

public class InsertionSort {
    public static void insertionSort(int[] nums) {
        if(nums == null)
            return;

        for(int i = 1; i < nums.length; i++) {
            for(int j = i; j > 0 && nums[j] < nums[j-1]; j--)
                swap(nums, j - 1, j);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{10, 4, 6, 8, 13, 2, 3};
        insertionSort(nums);

        System.out.println(Arrays.toString(nums));
    }
}
