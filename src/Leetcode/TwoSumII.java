package Leetcode;

/**
 * @author kalpak
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
 *
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
 *
 * Note:
 *
 * Your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution and you may not use the same element twice.
 *
 *
 * Example 1:
 *
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
 * Example 2:
 *
 * Input: numbers = [2,3,4], target = 6
 * Output: [1,3]
 * Example 3:
 *
 * Input: numbers = [-1,0], target = -1
 * Output: [1,2]
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 3 * 104
 * -1000 <= nums[i] <= 1000
 * nums is sorted in increasing order.
 * -1000 <= target <= 1000
 */

public class TwoSumII {
    public static int[] twoSumII(int[] numbers, int target) {
        int[] result = new int[2];

        int left = 0;
        int right = numbers.length - 1;

        while(left <= right) {
            int sum = numbers[left] + numbers[right];

            if(sum == target) {
                result[0] = left + 1;
                result[1] = right + 1;
                return result;
            } else if(sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return result;
    }

    // Time Complexity : O(nlogn)
    public static int[] twoSumIIBinarySearch(int[] numbers, int target) {
        int[] result = new int[2];

        int left = 0;
        int right = numbers.length - 1;

        while(left <= right) {
            int mid = left + (right - left)/2;
            int sum = numbers[left] + numbers[right];

            if(sum == target) {
                result[0] = left + 1;
                result[1] = right + 1;
                return result;
            } else if(sum < target) {
                if(numbers[mid] + numbers[right] < target)
                    left = mid + 1;
                else
                    left++;
            } else {
                if(numbers[mid] + numbers[left] > target)
                    right = mid - 1;
                else
                    right--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,7,11,15};
        int[] result = twoSumII(arr, 9);
        for(int i : result)
            System.out.print(i + " ");

        System.out.println();

        arr = new int[]{2, 3, 4};
        result = twoSumII(arr, 6);
        for(int i : result)
            System.out.print(i + " ");

        System.out.println();
    }
}
