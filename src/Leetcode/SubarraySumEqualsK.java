package Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kalpak
 *
 * Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * Example 2:
 *
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */

public class SubarraySumEqualsK {
    public static int subarraySum(int[] nums, int k) {
        int[] prefixSum = new int[nums.length + 1];
        int runningSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            prefixSum[i + 1] = runningSum;
        }

        //now the problem becomes find two items from this sums array so that prefixSum[j]-prefixSum[i] = k (similar to two sum)

        for (int i = 0; i < prefixSum.length; i++) {
            if(map.containsKey(prefixSum[i]))
                result += map.get(prefixSum[i]);

            int target = prefixSum[i] + k;
            map.put(target, map.getOrDefault(target, 0) + 1);
        }
        return result;
    }

    public static int subarraySumEqualsK(int[] nums, int k) {
        int runningSum = 0;
        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);
        for(int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            if(map.containsKey(runningSum - k))
                result += map.get(runningSum - k);

            map.put(runningSum, map.getOrDefault(runningSum, 0) + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 1};
        System.out.println(subarraySum(arr, 2));
        System.out.println(subarraySumEqualsK(arr, 2));
        arr = new int[]{1, 2, 3};
        System.out.println(subarraySum(arr, 3));
        System.out.println(subarraySumEqualsK(arr, 3));
    }
}
