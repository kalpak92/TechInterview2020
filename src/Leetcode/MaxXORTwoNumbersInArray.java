package Leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author kalpak
 *
 * Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 ≤ i ≤ j < n.
 *
 * Follow up: Could you do this in O(n) runtime?
 *
 * Example 1:
 * Input: nums = [3,10,5,25,2,8]
 * Output: 28
 * Explanation: The maximum result is 5 XOR 25 = 28.
 *
 * Example 2:
 * Input: nums = [0]
 * Output: 0
 *
 * Example 3:
 * Input: nums = [2,4]
 * Output: 6
 *
 * Example 4:
 * Input: nums = [8,10,2]
 * Output: 10
 *
 * Example 5:
 * Input: nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * Output: 127
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 10^4
 * 0 <= nums[i] <= 2^31 - 1
 *
 */

public class MaxXORTwoNumbersInArray {
    public static int findMaximumXOR(int[] nums) {
        int mask = 0;
        int max = 0;

        for(int i = 31; i >= 0; i--) {
            // The mask will grow like  100..000 , 110..000, 111..000,  then 1111...111
            // for each iteration, we only care about the left parts
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();

            for(int num : nums)
                // we only care about the left parts, for example, if i = 2, then we have
                // {1100, 1000, 0100, 0000} from {1110, 1011, 0111, 0010}
                set.add(mask & num);

            // if i = 1 and before this iteration, the max we have now is 1100,
            // my wish is the max will grow to 1110, so I will try to find a candidate
            // which can give me the greedyTry;
            int temp = max | (1 << i);

            for(int prefix : set) {
                // This is the most tricky part, coming from a fact that if a ^ b = c, then a ^ c = b;
                // now we have the 'c', which is greedyTry, and we have the 'a', which is leftPartOfNum

                // If we hope the formula a ^ b = c to be valid, then we need the b,
                // and to get b, we need a ^ c, if a ^ c exisited in our set, then we're good to go
                if(set.contains(temp ^ prefix)) {
                    max = temp;
                    break;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(findMaximumXOR(new int[]{3,10,5,25,2,8}));
    }
}
