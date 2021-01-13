package SlidingWindow;

/**
 * @author kalpak
 *
 * Given an array arr[] of size n containing positive integers.
 * The problem is to find the length of the longest sub-array having sum equal to the given value k.
 *
 * Examples:
 * Input : arr[] = { 10, 5, 2, 7, 1, 9 },
 *         k = 15
 * Output : 4
 */

public class LargestSubarraySumEqualsKforPositiveNumbers {
    public static int largestSubarraySumEqualsK(int[] arr, int k) {
        int sum = 0;
        int result = Integer.MIN_VALUE;
        int i = 0;
        int j = 0;

        while (j < arr.length) {
            sum += arr[j];
            if(sum < k) {
                //System.out.println("sum < k : " + j);
                j++;
            }
            else if(sum == k) {
                result = Math.max(result, j - i + 1);
                // System.out.println("sum == k : " + j);
                // System.out.println("result : " + result);
                j++;
            } else if(sum > k) {
                while(sum > k) {
                    sum -= arr[i];
                    i++;
                }
                // System.out.println("sum > k : " + j);
                j++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 1, 1, 1, 2, 3, 5};
        System.out.println("The size of the largest subarray is : " + largestSubarraySumEqualsK(arr, 5));
    }
}
