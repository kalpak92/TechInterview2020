package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kalpak
 *
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 * Example 1:
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 *
 * Example 2:
 * Input: nums = [-1]
 * Output: [0]
 *
 * Example 3:
 * Input: nums = [-1,-1]
 * Output: [0,0]
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -10^4 <= nums[i] <= 10^4
 */

public class CountOfNumbersSmallerThanSelf {
    static int[] count;

    public static List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();

        count = new int[nums.length];
        int[] indexes = new int[nums.length];

        for(int i = 0; i < nums.length; i++){
            indexes[i] = i;
        }

        mergesort(nums, indexes, 0, nums.length - 1);

        for(int i = 0; i < count.length; i++) {
            result.add(count[i]);
        }
        return result;
    }

    private static void mergesort(int[] nums, int[] indexes, int start, int end){
        if(end <= start) {
            return;
        }

        int mid = (start + end) / 2;
        mergesort(nums, indexes, start, mid);
        mergesort(nums, indexes, mid + 1, end);

        merge(nums, indexes, start, mid, end);
    }

    private static void merge(int[] nums, int[] indexes, int start, int mid, int end){
        int left_index = start;
        int right_index = mid + 1;
        int rightCount = 0;
        int[] new_indexes = new int[end - start + 1];

        int sort_index = 0;

        while(left_index <= mid && right_index <= end) {
            if(nums[indexes[right_index]] < nums[indexes[left_index]]){
                new_indexes[sort_index] = indexes[right_index];
                rightCount++;
                right_index++;
            } else {
                new_indexes[sort_index] = indexes[left_index];
                count[indexes[left_index]] += rightCount;
                left_index++;
            }
            sort_index++;
        }

        while(left_index <= mid){
            new_indexes[sort_index] = indexes[left_index];
            count[indexes[left_index]] += rightCount;
            left_index++;
            sort_index++;
        }

        while(right_index <= end){
            new_indexes[sort_index++] = indexes[right_index++];
        }

        for(int i = start; i <= end; i++){
            indexes[i] = new_indexes[i - start];
        }
    }

    public static void main(String[] args) {
        System.out.println(countSmaller(new int[]{5,2,6,1}));
    }
}
