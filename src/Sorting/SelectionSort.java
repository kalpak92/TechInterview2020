package Sorting;

import java.util.Arrays;

/**
 * @author kalpak
 *
 * Implementation of Selection Sort
 *
 */
public class SelectionSort {
    public static void selectionSort(int[] nums) {
        if(nums == null)
            return;

        for(int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10, 4, 6, 8, 13, 2, 3};
        selectionSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
