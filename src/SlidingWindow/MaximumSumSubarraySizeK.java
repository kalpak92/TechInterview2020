package SlidingWindow;

/**
 * Given an array of integers Arr of size N and a number K. Return the maximum sum of a subarray of size K.
 *
 * Example 1:
 * Input:
 * N = 4, K = 2
 * Arr = [100, 200, 300, 400]
 * Output:
 * 700
 * Explanation:
 * Arr3  + Arr4 =700, which is maximum.
 *
 * Example 2:
 * Input:
 * N = 4, K = 4
 * Arr = [100, 200, 300, 400]
 * Output:
 * 1000
 * Explanation:
 * Arr1 + Arr2 + Arr3
 * + Arr4 =1000, which is maximum.
 *
 * Expected Time Complexity: O(N)
 * Expected Auxiliary Space: O(1)
 *
 * Constraints:
 * 1<=N<=105
 * 1<=K<=N
 *
 */
public class MaximumSumSubarraySizeK {
    public static int maximumSumSubarray(int[] arr, int K){
        if(arr.length < K) {
            System.out.println("Invalid value of K");
            return -1;
        }

        int windowSum = 0;
        int result = Integer.MIN_VALUE;

        for(int i = 0; i < K; i++)
            windowSum += arr[i];

        result = windowSum;
        for(int i = K; i < arr.length; i++) {
            windowSum += arr[i] - arr[i - K];
            result = Math.max(result, windowSum);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{100, 200, 300, 400};
        System.out.println(maximumSumSubarray(arr, 2));
    }
}
