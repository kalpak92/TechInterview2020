package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author kalpak
 *
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 *
 * Example 2:
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Constraints:
 *
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 */

public class PermutationOfElementsWithDuplicates {
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        boolean[] isUsed = new boolean[nums.length];

        Arrays.sort(nums);
        backtrack(result, temp, isUsed, nums);

        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> temp, boolean[] isUsed, int[] nums) {
        // Base Condition
        if(temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for(int i = 0; i < nums.length; i++) {
            // Check if the number is currently in use. We use the index as opposed to the number itself.
            // Also check, the number is a duplicate or not and whether it has been used to the present recursive stack or not.
            // isUsed[i - 1] == false mean that the in current recursion tree, nums[i-1] has not been used but is a duplicate. We then need to avoid the number because a same entry has already been done in the previous call.
            // Draw the recursion tree to understand the same
            if(isUsed[i] || (i > 0 && nums[i] == nums[i-1] && isUsed[i-1] == false))
                continue;

            // Mark the current number as visited and add it to the temporary list
            isUsed[i] = true;
            temp.add(nums[i]);

            // Recurse down the tree
            backtrack(result, temp, isUsed, nums);

            // Unmark the number and go up the recursive stack
            isUsed[i] = false;
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 1};
        System.out.println(permuteUnique(nums));
    }
}
