package BinarySearch;

/**
 * @author kalpak
 *
 * Given a sorted array of n elements, possibly with duplicates, find the number of occurrences of the target element.
 *
 * Example 1:
 *
 * Input: arr = [4, 4, 8, 8, 8, 15, 16, 23, 23, 42], target = 8
 * Output: 3
 * Example 2:
 *
 * Input: arr = [3, 5, 5, 5, 5, 7, 8, 8], target = 6
 * Output: 0
 * Example 3:
 *
 * Input: arr = [3, 5, 5, 5, 5, 7, 8, 8], target = 5
 * Output: 4
 */
public class NumberOfOccurrencesInASortedArray {
    public static int findCount(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return -1;

        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left)/2;

            if (nums[mid] == target) {
                result[0] = mid;
                right = mid - 1;
            } else if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }

        left = 0;
        right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left)/2;

            if (nums[mid] == target) {
                result[1] = mid;
                left = mid + 1;
            } else if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return (result[1] == - 1 && result[0] == -1) ? -1 : result[1] - result[0] + 1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 5, 5, 5, 7, 8, 8};
        System.out.println(findCount(arr, 5));
        System.out.println(findCount(arr, 6));
    }
}
