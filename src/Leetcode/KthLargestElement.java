package Leetcode;

import java.util.PriorityQueue;

/**
 * @author kalpak
 *
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Example 1:
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 *
 * Example 2:
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 *
 */

public class KthLargestElement {
    public static int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length == 0)
            return -1;
        int result = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for(int i = 0; i < nums.length; i++) {
            minHeap.offer(nums[i]);
            if(minHeap.size() > k)
                minHeap.poll();
        }
        return minHeap.peek();
    }

    public static int findKthLargestUsingQuickSelect(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        int result = Integer.MIN_VALUE;

        while(true) {
            int pivot = partition(nums, left, right);

            if(pivot == k - 1) {
                result = nums[pivot];
                break;
            }

            if(pivot < k - 1)
                left = pivot + 1;
            else
                right = pivot - 1;
        }
        return result;
    }

    private static int partition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int pIndex = start;

        for(int i = start; i < end; i++) {
            if(nums[i] >= pivot) {              // Sort in descending order
                swap(nums, i, pIndex);
                pIndex++;
            }
        }

        swap(nums, end, pIndex);
        return pIndex;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,3,1,2,4,5,5,6};
        System.out.println(findKthLargest(nums, 4));
    }
}
