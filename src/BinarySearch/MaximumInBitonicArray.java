package BinarySearch;

/**
 * @author kalpak
 *
 * Given an array of integers which is initially increasing and then decreasing, find the maximum value in the array.
 * Examples :
 *
 * Input: arr[] = {8, 10, 20, 80, 100, 200, 400, 500, 3, 2, 1}
 * Output: 500
 *
 * Input: arr[] = {1, 3, 50, 10, 9, 7, 6}
 * Output: 50
 *
 * Corner case (No decreasing part)
 * Input: arr[] = {10, 20, 30, 40, 50}
 * Output: 50
 *
 * Corner case (No increasing part)
 * Input: arr[] = {120, 100, 80, 20, 0}
 * Output: 120
 *
 */
public class MaximumInBitonicArray {
    public static int findMaxBitonic(int[] nums) {
        if (nums.length == 1)
            return 0;

        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            int mid = left + (right - left)/2;

            if(mid > 0 && mid < nums.length - 1) {
                if(nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1])
                    return nums[mid];
                else if (nums[mid - 1] > nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            } else if (mid == 0) {                  // element on the left edge
                if(nums[mid] > nums[mid + 1])
                    return nums[0];
                else
                    return nums[1];
            } else if (mid == nums.length - 1) {    // element on the right edge
                if(nums[mid] > nums[mid - 1])
                    return nums[nums.length - 1];
                else
                    return nums[nums.length - 2];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findMaxBitonic(new int[]{8, 10, 20, 80, 100, 200, 400, 500, 3, 2, 1}));
        System.out.println(findMaxBitonic(new int[]{1, 3, 50, 10, 9, 7, 6}));
        System.out.println(findMaxBitonic(new int[]{10, 20, 30, 40, 50}));
        System.out.println(findMaxBitonic(new int[]{120, 100, 80, 20, 0}));
    }
}
