package Leetcode;

import java.util.TreeMap;

/**
 * @author kalpak
 *
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into sets of k consecutive numbers
 * Return True if it is possible. Otherwise, return False.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,3,4,4,5,6], k = 4
 * Output: true
 * Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
 * Example 2:
 *
 * Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
 * Output: true
 * Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].
 * Example 3:
 *
 * Input: nums = [3,3,2,2,1,1], k = 3
 * Output: true
 * Example 4:
 *
 * Input: nums = [1,2,3,4], k = 3
 * Output: false
 * Explanation: Each array should be divided in subarrays of size 3.
 *
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 105
 * 1 <= nums[i] <= 10^9
 *
 */

public class DivideArrayInSetsOfKConsecutiveNumbers {
    public static boolean isPossibleDivide(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return true;

        if (nums.length % k != 0)
            return false;

        TreeMap<Integer, Integer> freqMap = new TreeMap<>();

        for (int num : nums)
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);

        while(freqMap.size() > 0) {
            int min = freqMap.firstKey();           // O(log n) lookup
            int minOccurrences = freqMap.get(min);  // O(log n) lookup

            for (int i = 0; i < k; ++i) {
                // Check if we can create minOccurrences sequences of k consecutive numbers with the min as the first number.
                if (freqMap.containsKey(min + i) && minOccurrences <= freqMap.get(min + i)) {
                    freqMap.put(min + i, freqMap.get(min + i) - minOccurrences);

                    if (freqMap.get(min + i) == 0)
                        freqMap.remove(min + i);     // Remove min+1 if there are no more occurrences left.
                } else
                    return false;   // min doesn't belong to a group of k consecutive numbers so we return false.
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,1,2,3,4,3,4,5,9,10,11};
        System.out.println(isPossibleDivide(nums, 3));
    }
}
