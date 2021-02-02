package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author kalpak
 *
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 *
 */

public class SubsetsOfElementsWithDuplicates {
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        result.add(new ArrayList<>());

        // Sort the nums
        Arrays.sort(nums);
        // Range from which a new number is to be inserted
        int start = 0;
        int end = 0;

        for(int i = 0; i < nums.length; i++) {
            start = 0;
            if(i > 0 && nums[i] == nums[i-1]) // if current number is a duplicate, move the start
                start = end;

            end = result.size();
            for(int j = start; j < end; j++) {
                List<Integer> temp = new ArrayList<>(result.get(j));
                temp.add(nums[i]);
                result.add(temp);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 3};
        System.out.println(subsetsWithDup(nums));
    }
}
