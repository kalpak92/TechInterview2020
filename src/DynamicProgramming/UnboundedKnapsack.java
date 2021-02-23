package DynamicProgramming;

/**
 * @author kalpak
 *
 * Given a knapsack weight W and a set of n items with certain value val_i and weight weight_i,
 * we need to calculate the maximum amount that could make up this quantity exactly.
 *
 * Examples:
 * Input : W = 100
 *        val[]  = {1, 30}
 *        wt[] = {1, 50}
 * Output : 100
 *
 * There are many ways to fill knapsack.
 * 1) 2 instances of 50 unit weight item.
 * 2) 100 instances of 1 unit weight item.
 * 3) 1 instance of 50 unit weight item and 50
 *    instances of 1 unit weight items.
 *
 * We get maximum value with option 2.
 *
 * Input : W = 8
 *        val[] = {10, 40, 50, 70}
 *        wt[]  = {1, 3, 4, 5}
 *
 * Output : 110
 *
 * We get maximum value with one unit of
 * weight 5 and one unit of weight 3.
 *
 *
 * Its an unbounded knapsack problem as we can use 1 or more instances of any resource.
 * Here number of items never changes. We always have all items available.
 *
 * So, we compute :
 *  - profit1 = profit[currentIndex] + knapsack(weights, profits, dp, capacity - weights[currentIndex], currentIndex);  // consider the element
 *  - profit2 = knapsack(weights, profits, dp, capacity, currentIndex + 1);     // exclude the element
 *  - dp[currentIndex][capacity] = Max(profit1, profit2)
 *
 */


public class UnboundedKnapsack {
    public int solveUnboundedKnapsackRecursive(int[] profits, int[] weights, int capacity) {
        Integer[][] dp = new Integer[profits.length][capacity + 1];
        return unboundedKnapsackRecursive(dp, profits, weights, capacity, 0);
    }

    private int unboundedKnapsackRecursive(Integer[][] dp, int[] profits, int[] weights, int capacity, int currentIndex) {
        // base checks
        if (currentIndex >= profits.length || capacity <= 0)
            return 0;

        // if we have already solved a similar problem, return the result from memory
        if(dp[currentIndex][capacity] != null)
            return dp[currentIndex][capacity];

        // recursive call after choosing the element at the currentIndex
        // if the weight of the element at currentIndex exceeds the capacity, we shouldn't process this
        int profit1 = 0;
        if( weights[currentIndex] <= capacity )
            profit1 = profits[currentIndex] + unboundedKnapsackRecursive(dp, profits, weights,
                    capacity - weights[currentIndex], currentIndex);

        // recursive call after excluding the element at the currentIndex
        int profit2 = unboundedKnapsackRecursive(dp, profits, weights, capacity, currentIndex + 1);

        dp[currentIndex][capacity] = Math.max(profit1, profit2);
        return dp[currentIndex][capacity];
    }

    public int solveUnboundedKnapsackBottomsUp(int[] profits, int[] weights, int capacity) {
        // basic checks
        if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
            return 0;

        int[][] dp = new int[profits.length][capacity + 1];

        // populate the capacity = 0 columns, with '0' capacity we have '0' profit
        for(int i = 0; i < profits.length; i++)
            dp[i][0] = 0;


        // process all sub-arrays for all the capacities
        for(int i = 0; i < profits.length; i++) {
            for(int c = 1; c <= capacity; c++) {
                int profit1 = 0, profit2 = 0;
                // include the item, if it is not more than the capacity, but since we can repeat it, do it from the same row
                if(weights[i] <= c)
                    profit1 = profits[i] + dp[i][c-weights[i]];

                // exclude the item
                if(i > 0)
                    profit2 = dp[i-1][c];

                // take maximum
                dp[i][c] = Math.max(profit1, profit2);
            }
        }

        // maximum profit will be at the bottom-right corner.
        return dp[profits.length - 1][capacity];
    }


    public static void main(String[] args) {
        UnboundedKnapsack uks = new UnboundedKnapsack();

        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};

        int maxProfit = uks.solveUnboundedKnapsackRecursive(profits, weights, 7);
        System.out.println("Total knapsack profit with repetition allowed ---> " + maxProfit);

        maxProfit = uks.solveUnboundedKnapsackBottomsUp(profits, weights, 7);
        System.out.println("Total knapsack profit with repetition allowed ---> " + maxProfit);
    }
}
