package Leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author kalpak
 *
 * Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].
 * Return true if there is a 132 pattern in nums, otherwise, return false.
 *
 * Follow up: The O(n^2) is trivial, could you come up with the O(n logn) or the O(n) solution?
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: false
 * Explanation: There is no 132 pattern in the sequence.
 * Example 2:
 *
 * Input: nums = [3,1,4,2]
 * Output: true
 * Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
 * Example 3:
 *
 * Input: nums = [-1,3,2,0]
 * Output: true
 * Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 104
 * -109 <= nums[i] <= 109
 */

/**
 * Algorithm:
 * Create a stack and initialize a variable second with INT_MIN value.
 * Start traversing from the end of array.
 * Check if the current number is lesser than second. If it is, then it means our 132 pattern is satisfied as the stack is taking care of the 32 pattern and the current number satisfies the entire pattern. So return true.
 * If the above is not true, update the peak value in the stack. Keep popping from the stack until stack is empty OR the top value is greater than the current value.
 * Push the current number into the stack.
 * If the loop terminates, it means that the pattern was not found in the array. So, return false.
 */

public class Pattern132 {
    public static boolean find132pattern(int[] nums) {
        int second = Integer.MIN_VALUE;
        // stack implementation will take care of the 32 pattern and then we will iterate over the array to find if any number satisfies the 1 pattern.
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = nums.length - 1; i >= 0; i--) {
            if(nums[i] < second)
                return true;
            else {
                while(!stack.isEmpty() && nums[i] > stack.peek()) {
                    second = stack.pop();
                }
                stack.push(nums[i]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 6, 4, 1, 2};
        System.out.println(find132pattern(arr));
    }
}
