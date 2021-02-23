package DynamicProgramming;

/**
 * @author kalpak
 *
 * Given a rod of length `n` inches and an array of prices that contains prices of all pieces of size smaller than n.
 * Determine the maximum value obtainable by cutting up the rod and selling the pieces.
 *
 */

public class RodCutting {
    public static int solveRodCuttingRecursive(int[] lengths, int[] prices, int lengthOfRod) {
        Integer[][] dp = new Integer[lengths.length][lengthOfRod + 1];
        return solveRodCuttingTopDown(lengths, prices, dp, lengthOfRod, 0);
    }

    private static int solveRodCuttingTopDown(int[] lengths, int[] prices, Integer[][] dp, int lengthOfRod, int currentIndex) {
        // base condition
        if(currentIndex >= lengths.length || lengthOfRod <= 0 || lengths.length != prices.length || lengths.length == 0)
            return 0;

        // if we have already solved a similar problem, return the result from memory
        if(dp[currentIndex][lengthOfRod] != null)
            return dp[currentIndex][lengthOfRod];

        // recursive call after choosing the element at the currentIndex
        // if the length of the element at currentIndex exceeds the lengthOfRod, we shouldn't process this
        int profit1 = 0;
        if(lengths[currentIndex] <= lengthOfRod) {
            profit1 = prices[currentIndex] +
                    solveRodCuttingTopDown(lengths, prices, dp, lengthOfRod - lengths[currentIndex], currentIndex);
        }

        int profit2 = solveRodCuttingTopDown(lengths, prices, dp, lengthOfRod, currentIndex + 1);

        dp[currentIndex][lengthOfRod] = Math.max(profit1, profit2);
        return dp[currentIndex][lengthOfRod];
    }

    public static int solveRodCuttingBottomsUp(int[] lengths, int[] prices, int lengthOfRod) {
        if (lengthOfRod <= 0 || prices.length == 0 || prices.length != lengths.length)
            return 0;

        Integer[][] dp = new Integer[lengths.length][lengthOfRod + 1];

        // populate the lengthOfRod = 0 columns, with '0' as we have '0' profit
        for(int i = 0; i < lengths.length; i++)
            dp[i][0] = 0;

        // process all sub-arrays for all the capacities
        for(int i = 0; i < lengths.length; i++) {
            for(int c = 1; c <= lengthOfRod; c++) {
                int profit1 = 0, profit2 = 0;
                // include the item, if it is not more than the capacity, but since we can repeat it, do it from the same row
                if(lengths[i] <= c)
                    profit1 = prices[i] + dp[i][c - lengths[i]];

                // exclude the item
                if(i > 0)
                    profit2 = dp[i-1][c];

                // take maximum
                dp[i][c] = Math.max(profit1, profit2);
            }
        }
        return  dp[lengths.length - 1][lengthOfRod];
    }

    public static void main(String[] args) {
        int[] lengths = {1, 2, 3, 4, 5};
        int[] prices = {2, 6, 7, 10, 13};
        int maxProfit = solveRodCuttingRecursive(lengths, prices, 5);
        System.out.println(maxProfit);

        System.out.println(solveRodCuttingBottomsUp(lengths, prices, 5));
    }
}
