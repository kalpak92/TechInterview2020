package Leetcode;

/**
 * @author kalpak
 *
 * Given an array nums of size n, return the majority element.
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,3]
 * Output: 3
 * Example 2:
 *
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 5 * 104
 * -2^31 <= nums[i] <= 2^31 - 1
 *
 */

public class MajorityElement {
    public static int majorityElementCount(int[] nums) {
        int count = 0;
        int candidate = Integer.MAX_VALUE;

        for(int i : nums) {
            if(count == 0)
                candidate = i;

            if(i == candidate)
                count++;
            else
                count--;
        }
        return candidate;
    }

    public static void main(String[] args) {
        System.out.println(majorityElementCount(new int[]{2,2,1,1,1,2,2}));
    }
}
