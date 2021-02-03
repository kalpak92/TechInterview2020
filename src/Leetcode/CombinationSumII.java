package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author kalpak
 *
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note: The solution set must not contain duplicate combinations.
 *
 *
 * Example 1:
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 *
 *
 * Example 2:
 * Input: candidates = [2,5,2,1,2], target = 5
 * Output:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 *
 * Constraints:
 *
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 */

public class CombinationSumII {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        Arrays.sort(candidates); // since the candidates are not distinct
        backtrack(result, temp, candidates, target, 0);
        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> temp, int[] candidates, int target, int index) {
        if(target < 0)
            return;

        if(target == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for(int i = index; i < candidates.length; i++) {
            if(i > index && candidates[i] == candidates[i-1])   // skip duplicates
                continue;

            temp.add(candidates[i]);
            backtrack(result, temp, candidates, target - candidates[i], i+1); // send index i+1 since we cannot reuse
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10,1,2,7,6,1,5};
        System.out.println(combinationSum2(nums, 8));
    }
}
