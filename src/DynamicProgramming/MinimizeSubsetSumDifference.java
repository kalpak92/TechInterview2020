package DynamicProgramming;

/**
 * @author kalpak
 *
 * Given a set of integers, the task is to divide it into two sets S1 and S2
 * such that the absolute difference between their sums is minimum.
 *
 * If there is a set S with n elements, then if we assume Subset1 has m elements, Subset2 must have n-m elements and
 * the value of abs(sum(Subset1) – sum(Subset2)) should be minimum.
 *
 * Example:
 *
 * Input:  arr[] = {1, 6, 11, 5}
 * Output: 1
 * Explanation:
 * Subset1 = {1, 5, 6}, sum of Subset1 = 12
 * Subset2 = {11}, sum of Subset2 = 11
 *
 */

public class MinimizeSubsetSumDifference {
    public static int minimizeSubsetSumDifference(int[] nums) {
        int sum = 0;
        for(int i : nums)
            sum += i;

        int firstPartition = getFirstPossiblePartition(nums, sum/2);
        int secondPartition = sum - firstPartition;

        return Math.abs(secondPartition - firstPartition);
    }

    private static int getFirstPossiblePartition(int[] nums, int target) {
        boolean[][] dp = new boolean[nums.length][target + 1];

        // Now the first column of the memoization table will be to true since we can achieve a target of 0 with empty set
        for(int i = 0; i < nums.length; i++)
            dp[i][0] = true;

        // with only one number, we can form a subset only when the required sum is
        // equal to its value
        for (int s = 1; s <= target; s++) {
            dp[0][s] = (nums[0] == s ? true : false);
        }

        // process all subsets for all sums
        for (int i = 1; i < nums.length; i++) {
            for (int s = 1; s <= target; s++) {
                // if we can get the sum 's' without the number at index 'i'
                if (dp[i - 1][s]) {
                    dp[i][s] = dp[i - 1][s];
                } else if (nums[i] < s) {
                    // else include the number and see if we can find a subset to get the remaining
                    // sum
                    dp[i][s] = dp[i - 1][s - nums[i]];
                }
            }
        }

        int firstPartitionValue = -1;
        for(int i = target; i >= 0; i--) {
            if(dp[dp.length - 1][i]) {
                firstPartitionValue = i;
                break;
            }
        }

        return firstPartitionValue;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 5, 13};
        System.out.println(minimizeSubsetSumDifference(nums));

        nums = new int[]{1, 2, 3, 9};
        System.out.println(minimizeSubsetSumDifference(nums));

        nums = new int[]{1, 2, 7, 1, 5};
        System.out.println(minimizeSubsetSumDifference(nums));

        nums = new int[]{1, 3, 100, 4};
        System.out.println(minimizeSubsetSumDifference(nums));
    }
}
