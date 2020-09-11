package PrefixSum;

/*
  @author kalpak
 *
 * Given an array of n positive integers and a positive integer k,
 * the task is to find the maximum subarray size such that all subarrays of that size have sum of elements less than or equals to k.
 *
 * Examples :
 *
 * Input :  arr[] = {1, 2, 3, 4} and k = 8.
 * Output : 2
 * Sum of subarrays of size 1: 1, 2, 3, 4.
 * Sum of subarrays of size 2: 3, 5, 7.
 * Sum of subarrays of size 3: 6, 9.
 * Sum of subarrays of size 4: 10.
 * So, maximum subarray size such that all subarrays
 * of that size have the sum of elements less than 8 is 2.
 *
 * Input :  arr[] = {1, 2, 10, 4} and k = 8.
 * Output : -1
 * There is an array element with value greater than k,
 * so subarray sum cannot be less than k.
 *
 * Input :  arr[] = {1, 2, 10, 4} and K = 14
 * Output : 2
 */

/**
 * ALGORITHM
 * First of all, required subarray size must lie between 1 to n.
 *
 * Now, since all the array element are positive integers, we can say that the prefix sum of any subarray shall be strictly increasing.
 * Thus, we can say that
 * if arr[i] + arr[i + 1] + ..... + arr[j - 1] + arr[j] <= K
 * then arr[i] + arr[i + 1] + ..... + arr[j - 1] <= K, as
 * arr[j] is a positive integer.
 *
 * So, we perform Binary Search over the range 1 to n and find the highest subarray size
 * such that all the subarrays of that size have the sum of elements less than or equals to k.
 */

public class MaxSubarraySizeWithSumLessThanK {
    private static int binarySearch(int[] arr, int k) {
        int result = -1;
        int left = 1;
        int right = arr.length - 1;

        while(left <= right) {
            int mid = (left + right) / 2;

            // Check for all subarrays after mid
            int i;
            for (i = mid; i <= arr.length; i++) {

                // Checking if all the subarrays
                // of a size is less than k.
                if (arr[i] - arr[i - mid] > k)
                    break;
            }

            // All subarrays of size mid have
            // sum less than or equal to k
            if (i == arr.length + 1) {
                left = mid + 1;
                result = mid;
            }

            // We found a subrray of size mid
            // with sum greater than k
            else
                right = mid - 1;
        }
        return result;
    }

    public static int maximumSubarraySize(int[] arr, int k) {
        int[] prefixSum = new int[arr.length + 1];

        for(int i = 0; i < arr.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + arr[i];
        }

        return binarySearch(prefixSum, k);
    }

    public static void main(String[] args) {
        int[] arr = {4, 4, 2, 3, 1 };
        System.out.println(maximumSubarraySize(arr, 7));
    }
}
