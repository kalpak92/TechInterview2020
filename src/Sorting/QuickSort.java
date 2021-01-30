package Sorting;

import java.util.Arrays;

/**
 * @author kalpak
 *
 * Implemeent Quick Sort with Lomuto and Hoare partition scheme
 *
 */
public class QuickSort {
    public static void quickSort(int[] nums) {
        if (nums == null)
            return;

        quickSort(nums, 0, nums.length - 1);
    }

    public static void quickSort(int[] nums, int left, int right) {
        if(left < right) {
            int pivot = hoarePartition(nums, left, right);
            quickSort(nums, left, pivot);
            quickSort(nums, pivot + 1, right);
        }
    }

    public static int lomutoPartition(int[] nums, int start, int end) {
        // Pick rightmost element as pivot from the array
        int pivot = nums[end];

        // elements less than pivot will be pushed to the left of pIndex
        // elements more than pivot will be pushed to the right of pIndex
        // equal elements can go either way
        int pIndex = start;

        // each time we finds an element less than or equal to pivot,
        // pIndex is incremented and that element would be placed
        // before the pivot.
        for (int i = start; i < end; i++) {
            if (nums[i] <= pivot) {
                swap(nums, i, pIndex);
                pIndex++;
            }
        }

        // swap pIndex with Pivot
        swap(nums, end, pIndex);

        // return pIndex (index of pivot element)
        return pIndex;
    }

    public static int hoarePartition(int[] nums, int start, int end) {
        int pivot = nums[start];
        int i = start - 1;
        int j = end + 1;

        while (true) {
            do {
                i++;
            } while (nums[i] < pivot);

            do {
                j--;
            } while (nums[j] > pivot);

            if (i >= j)
                return j;

            swap(nums, i, j);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10, 4, 6, 4, 8, -13, 2, 3};
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
