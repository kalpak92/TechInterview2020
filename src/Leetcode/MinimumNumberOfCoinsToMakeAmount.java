package Leetcode;

/**
 * @author kalpak
 *
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Example 1:
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 *
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 *
 * Example 3:
 * Input: coins = [1], amount = 0
 * Output: 0
 *
 * Example 4:
 * Input: coins = [1], amount = 1
 * Output: 1
 *
 * Example 5:
 * Input: coins = [1], amount = 2
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 2^31 - 1
 * 0 <= amount <= 10^4
 *
 */

public class MinimumNumberOfCoinsToMakeAmount {
    public static int getMinimumCoinsForAmount(int[] coins, int amount) {
        Integer[][] dp = new Integer[coins.length][amount + 1];
        int result = waysToComputeAmountTopDown(coins, dp, amount, 0);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private static int waysToComputeAmountTopDown(int[] coins, Integer[][] dp, int amount, int currentIndex) {
        // If amount is 0, We need 0 coins
        if(amount == 0)
            return 0;

        // Base Case
        if(coins.length == 0 || currentIndex >= coins.length)
            return Integer.MAX_VALUE;

        // if we have already solved a similar problem, return the result from memory
        if(dp[currentIndex][amount] != null)
            return dp[currentIndex][amount];


        // recursive call after choosing the element at the currentIndex
        // if the coin value of the element at currentIndex exceeds the amount, we shouldn't process this
        int count1 = Integer.MAX_VALUE;
        if(coins[currentIndex] <= amount) {
            count1 = waysToComputeAmountTopDown(coins, dp, amount - coins[currentIndex], currentIndex);  // since no value is associated with the coins
            // include the current coin too
            if (count1 != Integer.MAX_VALUE)
                count1 += 1;
        }

        // recursive call after excluding the element at the currentIndex
        int count2 = waysToComputeAmountTopDown(coins, dp, amount, currentIndex + 1);

        dp[currentIndex][amount] = Math.min(count1, count2);   // Consider the minimum of the two choices

        return dp[currentIndex][amount];
    }

    public static int getMinimumCoinsForAmountBottomsUp(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];

        for(int i = 0; i < coins.length; i++)
            for(int j = 0; j <= amount; j++)
                dp[i][j] = Integer.MAX_VALUE;

        // for amount = 0, we initialize the table to 0 since we dont need any coins
        for(int i = 0; i < coins.length; i++)
            dp[i][0] = 0;

        // process all sub-arrays for all the capacities
        for(int i = 0; i < coins.length; i++) {
            for(int c = 1; c <= amount; c++) {
                int count1 = Integer.MAX_VALUE, count2 = Integer.MAX_VALUE;
                // include the item, if it is not more than the capacity, but since we can repeat it, do it from the same row
                if(coins[i] <= c) {
                    count1 = dp[i][c - coins[i]];       // We dont add the coins[i] here since it is not the profit/value but the weights
                    if (count1 != Integer.MAX_VALUE)
                        count1 += 1;                    // include the current coin
                }

                // exclude the item
                if(i > 0)
                    count2 = dp[i - 1][c];

                // Store the minimum of both
                dp[i][c] = Math.min(count1, count2);
            }
        }
        return dp[coins.length - 1][amount] == Integer.MAX_VALUE ? -1 : dp[coins.length - 1][amount];
    }

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        System.out.println(getMinimumCoinsForAmount(coins, 11));
        System.out.println(getMinimumCoinsForAmountBottomsUp(coins, 11));
    }
}
