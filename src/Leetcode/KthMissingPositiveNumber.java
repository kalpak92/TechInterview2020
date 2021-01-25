package Leetcode;

/**
 * @author kalpak
 *
 * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
 *
 * Find the kth positive integer that is missing from this array.
 *
 * Example 1:
 * Input: arr = [2,3,4,7,11], k = 5
 * Output: 9
 * Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
 *
 * Example 2:
 * Input: arr = [1,2,3,4], k = 2
 * Output: 6
 * Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 1000
 * 1 <= arr[i] <= 1000
 * 1 <= k <= 1000
 * arr[i] < arr[j] for 1 <= i < j <= arr.length
 */
public class KthMissingPositiveNumber {
    public static int findKthPositive(int[] arr, int k) {
        int left = 0;
        int right = arr.length;

        while(left < right) {
            int mid = left + ((right - left) / 2);
            // The number of positive integers which are missing before the arr[idx] is equal to arr[idx] - idx - 1.
            // Consider [2, 3, 4, 7, 11]
            // an array with no missing integers: [1, 2, 3, 4, 5]
            // Before 2, there is 2 - 0 - 1 = 1 missing integer.
            // Before 3, there is 3 - 1 - 1 = 1 missing integer.
            // Before 4, there is 4 - 2 - 1 = 1 missing integer.
            // Before 7, there are 7 - 3 - 1 = 3 missing integers.
            // Before 11, there are 11 - 4 - 1 = 6 missing integers.

            if(arr[mid] - (mid + 1) < k)
                // If the number of positive integers which are missing before arr[pivot] is less than k - continue to search on the right side of the array.
                left = mid + 1;
            else
                right = mid;
        }
        return left + k;
    }

    public static int missingElement(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        int numberOfMissing = 0;

        while(left <= right) {
            int mid = left + (right - left) / 2;
            numberOfMissing = nums[mid] - nums[0] - mid;

            if(k > numberOfMissing)
                left = mid + 1;        // number of missing numbers from nums[0] to mid is less than k, so go right
            else {
                right = mid - 1;
            }
        }

        if (numberOfMissing >= k) {
            // desired number is to the left of nums[left].
            // Minus 1, because we don't include nums[left]
            return nums[left] - 1 - (numberOfMissing - k);
        }
        return nums[right] + (k - numberOfMissing);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,3,4,7,11};
        System.out.println(findKthPositive(arr, 5));
        arr = new int[]{1, 2, 3, 4};
        System.out.println(findKthPositive(arr, 2));
    }
}
