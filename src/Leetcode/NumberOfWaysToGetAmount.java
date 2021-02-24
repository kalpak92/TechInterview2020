package Leetcode;

/**
 * @author kalpak
 *
 * You are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that amount.
 * You may assume that you have infinite number of each kind of coin.
 *
 * Example 1:
 * Input: amount = 5, coins = [1, 2, 5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 * Example 2:
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 *
 * Example 3:
 * Input: amount = 10, coins = [10]
 * Output: 1
 *
 *
 * Note:
 * You can assume that
 * 0 <= amount <= 5000
 * 1 <= coin <= 5000
 * the number of coins is less than 500
 * the answer is guaranteed to fit into signed 32-bit integer
 *
 */

public class NumberOfWaysToGetAmount {
    public static int waysToComputeAmountRecursive(int[] coins, int amount) {
        Integer[][] dp = new Integer[coins.length][amount + 1];
        return waysToComputeAmountTopDown(coins, dp, amount, 0);
    }

    private static int waysToComputeAmountTopDown(int[] coins, Integer[][] dp, int amount, int currentIndex) {
        // If amount is 0, we can create it in 1 way
        if(amount == 0)
            return 1;

        // Base Case
        if(coins.length == 0 || currentIndex >= coins.length)
            return 0;

        // if we have already solved a similar problem, return the result from memory
        if(dp[currentIndex][amount] != null)
            return dp[currentIndex][amount];


        // recursive call after choosing the element at the currentIndex
        // if the denominatoin of the coin at currentIndex exceeds the amount, we shouldn't process this
        int ways1 = 0;
        if(coins[currentIndex] <= amount)
            ways1 = waysToComputeAmountTopDown(coins, dp, amount - coins[currentIndex], currentIndex);  // since no value is associated with the coins

        // recursive call after excluding the element at the currentIndex
        int ways2 = waysToComputeAmountTopDown(coins, dp, amount, currentIndex + 1);

        dp[currentIndex][amount] = ways1 + ways2;   // Add both the ways since we are counting here

        return dp[currentIndex][amount];
    }

    public static int waysToComputeAmountBottomsUp(int[] coins, int amount) {
        // basic checks
        if(amount == 0)
            return 1;

        if (amount < 0 || coins.length == 0)
            return 0;

        int[][] dp = new int[coins.length][amount + 1];

        // populate the amount = 0 columns, we have 1 way to achieve that
        for(int i = 0; i < coins.length; i++)
            dp[i][0] = 1;


        // process all sub-arrays for all the capacities
        for(int i = 0; i < coins.length; i++) {
            for(int c = 1; c <= amount; c++) {
                int ways1 = 0, ways2 = 0;
                // include the item, if it is not more than the capacity, but since we can repeat it, do it from the same row
                if(coins[i] <= c)
                    ways1 = dp[i][c - coins[i]];    // We dont add the coins[i] here since it is not the profit/value but the weights

                // exclude the item
                if(i > 0)
                    ways2 = dp[i - 1][c];

                // Add both ways
                dp[i][c] = ways1 + ways2;
            }
        }
        return dp[coins.length - 1][amount];
    }

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        System.out.println("The number of ways to compute the given amount is : " +
                waysToComputeAmountRecursive(coins, 5));
        System.out.println("The number of ways to compute the given amount is : " +
                waysToComputeAmountBottomsUp(coins, 5));
    }
}
