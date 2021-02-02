package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author kalpak
 *
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
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
 * All the numbers of nums are unique.
 *
 */

public class SubsetsOfUniqueElements {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());

        for(int num : nums) {
            int len = result.size();
            for(int i = 0; i < len; i++) {
                List<Integer> temp = new ArrayList<>(result.get(i));
                temp.add(num);
                result.add(temp);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5};
        System.out.println(subsets(nums));
    }
}
