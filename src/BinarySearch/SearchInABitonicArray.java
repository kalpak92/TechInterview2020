package BinarySearch;

/**
 * @author kalpak
 *
 * Given a bitonic sequence of n distinct elements,
 * write a program to find a given element x in the bitonic sequence in O(log n) time.
 *
 * A Bitonic Sequence is a sequence of numbers that is first strictly increasing then after a point strictly decreasing.
 *
 * Examples:
 *
 * Input :  arr[] = {-3, 9, 18, 20, 17, 5, 1};
 *          key = 20
 * Output : Found at index 3
 *
 * Input :  arr[] = {5, 6, 7, 8, 9, 10, 3, 2, 1};
 *          key = 30
 * Output : Not Found
 */
public class SearchInABitonicArray {
    public static int search(int[] arr, int left, int right, int key, boolean ascend) {
        if (arr.length == 0)
            return -1;

        while (left <= right) {
            int mid = (left+right)/2;
            if (arr[mid] == key) return mid;
            if (ascend) {
                if (arr[mid] < key) left = mid+1;
                else right = mid-1;
            } else {
                if (arr[mid] < key) right = mid-1;
                else left = mid+1;
            }
        }
        return -1;
    }

    public static int findPeakElement(int[] nums) {
        if (nums.length == 1)
            return 0;

        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            int mid = left + (right - left)/2;

            if(mid > 0 && mid < nums.length - 1) {
                if(nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1])
                    return mid;
                else if (nums[mid - 1] > nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            } else if (mid == 0) {                  // element on the left edge
                if(nums[mid] > nums[mid + 1])
                    return 0;
                else
                    return 1;
            } else if (mid == nums.length - 1) {    // element on the right edge
                if(nums[mid] > nums[mid - 1])
                    return nums.length - 1;
                else
                    return nums.length - 2;
            }
        }
        return -1;
    }

    public static int searchInBitonicArray(int[] nums, int target) {
        int peakElementIndex = findPeakElement(nums);
        int searchLowerHalf = search(nums, 0, peakElementIndex - 1, target, true);
        int searchUpperHalf = search(nums, peakElementIndex, nums.length-1, target, false);

        if(searchLowerHalf == -1 && searchUpperHalf == -1)
            return -1;
        else if (searchLowerHalf == -1)
            return searchUpperHalf;
        else
            return searchLowerHalf;
    }

    public static void main(String[] args) {
        System.out.println(searchInBitonicArray(new int[] { 1, 3, 8, 4, 3 }, 4));
        System.out.println(searchInBitonicArray(new int[] { 3, 8, 3, 1 }, 8));
        System.out.println(searchInBitonicArray(new int[] { 1, 3, 8, 12 }, 12));
        System.out.println(searchInBitonicArray(new int[] { 10, 9, 8 }, 10));
        System.out.println(searchInBitonicArray(new int[] {10, 9, 8}, 123));
    }
}
