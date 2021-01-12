package SlidingWindow;

/**
 * @author kalpak
 *
 * Given an array of integers Arr of size N and a number K. Return the minimum sum of a subarray of size K.
 *
 * Example 1:
 * Input:
 * K = 2
 * Arr = [100, 200, 300, 400]
 * Output:
 * 300
 */
public class MinimumSumSubarraySizeK {
    public static int minimumSumSubarray(int[] arr, int K){
        if(arr.length < K) {
            System.out.println("Invalid value of K");
            return -1;
        }

        int windowSum = 0;
        int result = Integer.MAX_VALUE;

        for(int i = 0; i < K; i++)
            windowSum += arr[i];

        result = windowSum;
        for(int i = K; i < arr.length; i++) {
            windowSum += arr[i] - arr[i - K];
            result = Math.min(result, windowSum);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{100, 200, 300, 400};
        System.out.println(minimumSumSubarray(arr, 2));
    }
}
