package Leetcode;

/**
 * @author kalpak
 *
 * Given an integer array arr, you should partition the array into (contiguous) subarrays of length at most k.
 * After partitioning, each subarray has their values changed to become the maximum value of that subarray.
 *
 * Return the largest sum of the given array after partitioning.
 *
 *
 * Example 1:
 * Input: arr = [1,15,7,9,2,5,10], k = 3
 * Output: 84
 *
 * Explanation: arr becomes [15,15,15,9,10,10,10]
 *
 *
 * Example 2:
 * Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
 * Output: 83
 *
 *
 * Example 3:
 * Input: arr = [1], k = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 500
 * 0 <= arr[i] <= 109
 * 1 <= k <= arr.length
 */

public class PartitionArrayForMaximumSum {
    public static int maxSumAfterPartitioning(int[] arr, int k) {
        int[] dp = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int curMax = 0;

            for (int j = 1; j <= k && i - j + 1 >= 0; j++) {
                curMax = Math.max(curMax, arr[i - j + 1]);
                dp[i] = Math.max(dp[i], (i >= j ? dp[i - j] : 0) + curMax * j);
            }
        }
        return dp[arr.length - 1];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,15,7,9,2,5,10};
        System.out.println(maxSumAfterPartitioning(arr, 3));
    }
}
