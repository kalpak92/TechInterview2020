package BinarySearch;

/**
 * Given an array which is sorted, but after sorting some elements are moved to either of the adjacent positions,
 * i.e., arr[i] may be present at arr[i+1] or arr[i-1]. Write an efficient function to search an element in this array.
 *
 * Basically the element arr[i] can only be swapped with either arr[i+1] or arr[i-1].
 *
 * For example consider the array {2, 3, 10, 4, 40}, 4 is moved to next position and 10 is moved to previous position.
 *
 * Example :
 * Input: arr[] =  {10, 3, 40, 20, 50, 80, 70}, key = 40
 * Output: 2
 * Output is index of 40 in given array
 *
 * Input: arr[] =  {10, 3, 40, 20, 50, 80, 70}, key = 90
 * Output: -1
 * -1 is returned to indicate element is not present
 */

public class FindElementInAlmostSortedArray {
    public static int searchInAlmostSortedArray(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while(left <= right) {
            int mid = left + (right - left)/2;

            if(nums[mid] == target)
                return mid;

            if(mid - 1 >= left && nums[mid - 1] == target)
                return mid-1;

            if(mid + 1 <= right && nums[mid + 1] == target)
                return mid+1;

            if (nums[mid] < target)
                left = mid + 2;
            else if (nums[mid] > target)
                right = mid - 2;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 10, 4, 40};
        System.out.println(searchInAlmostSortedArray(nums, 4));
    }
}
