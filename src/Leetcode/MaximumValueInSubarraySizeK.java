package Leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author kalpak
 *
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the max sliding window.
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 * Example 3:
 *
 * Input: nums = [1,-1], k = 1
 * Output: [1,-1]
 *
 * Example 4:
 *
 * Input: nums = [9,11], k = 2
 * Output: [11]
 * Example 5:
 *
 * Input: nums = [4,-2], k = 2
 * Output: [4]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 */

public class MaximumValueInSubarraySizeK {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        int idx = 0;
        Deque<Integer> queue = new LinkedList<>();
        int i = 0;
        int j = 0;

        while(j < nums.length) {
            // Remove all elements smaller than the current element from the deque.
            while(!queue.isEmpty() && nums[j] > queue.peekLast()) {
                queue.pollLast();
            }
            // Add the current element
            queue.offer(nums[j]);

            // Check the window size.
            if(j - i + 1 < k)
                j++;            // If not reached, increase the window
            else if(j - i + 1 == k) {   // Window Size reached
                result[idx++] = queue.peekFirst();  // query the answer from the head of the deque

                // Need to slide the window
                if(!queue.isEmpty() && nums[i] == queue.peekFirst()) // remove the left element of the window if present in the deque, since it is no longer needed
                    queue.pollFirst();
                i++;
                j++;
            }
        }
        return result;
    }

    private static void printArray(int[] result) {
        for(int i : result)
            System.out.print(i + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1,3,-1,-3,5,3,6,7};
        int[] result = maxSlidingWindow(arr1, 3);
        printArray(result);

        int[] arr2 = new int[]{1, 3, 1, 2, 0, 5};
        result = maxSlidingWindow(arr2, 3);
        printArray(result);
    }
}
