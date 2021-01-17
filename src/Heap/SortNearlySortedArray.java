package Heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author kalpak
 *
 * Given an array of n elements, where each element is at most k away from its target position, devise an algorithm that sorts in O(n log k) time.
 *
 * For example, let us consider k is 2, an element at index 7 in the sorted array, can be at indexes 5, 6, 7, 8, 9 in the given array.
 *
 * Examples:
 * Input : arr[] = {6, 5, 3, 2, 8, 10, 9}
 *             k = 3
 * Output : arr[] = {2, 3, 5, 6, 8, 9, 10}
 *
 * Input : arr[] = {10, 9, 8, 7, 4, 70, 60, 50}
 *          k = 4
 * Output : arr[] = {4, 7, 8, 9, 10, 50, 60, 70}
 */

public class SortNearlySortedArray {
    public static List<Integer>  sortAlmostSortedArray(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for(int i = 0; i < nums.length; i++) {
            minHeap.offer(nums[i]);

            if(minHeap.size() > k)
                result.add(minHeap.poll());
        }

        while (!minHeap.isEmpty())
            result.add(minHeap.poll());

        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{6, 5, 3, 2, 8, 10, 9};
        System.out.println(sortAlmostSortedArray(nums, 3));
    }
}
