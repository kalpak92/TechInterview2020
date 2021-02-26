package Leetcode;

import java.util.Arrays;

/**
 * @author kalpak
 *
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 *
 * Notice that you may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 * Input: k = 2, prices = [2,4,1]
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 *
 * Example 2:
 * Input: k = 2, prices = [3,2,6,5,0,3]
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
 * Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *
 *
 * Constraints:
 *
 * 0 <= k <= 100
 * 0 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 */

public class BuyAndSellStockIV {
    static Integer[][][] dp;
    public static int maxProfit(int k, int[] prices) {
        if(k >= prices.length / 2) {
            int result = 0;
            for(int i = 1; i < prices.length; i++) {
                if(prices[i] > prices[i - 1])
                    result += prices[i] - prices[i - 1];
            }
            return result;
        }

        dp = new Integer[prices.length + 1][k + 1][2];
        return computeMaxProfit(prices, 0, 1, 0, k);
    }

    private static int computeMaxProfit(int[] prices, int currentIndex, int buy, int txCount, int k) {
        if(currentIndex >= prices.length || txCount >= k)
            return 0;

        if(dp[currentIndex][txCount][buy] != null)
            return dp[currentIndex][txCount][buy];

        if(buy == 1)
            return dp[currentIndex][txCount][buy] = Math.max(-prices[currentIndex] + computeMaxProfit(prices, currentIndex + 1, 0, txCount, k) , computeMaxProfit(prices, currentIndex + 1, buy, txCount, k));
        else
            return dp[currentIndex][txCount][buy] = Math.max(prices[currentIndex] + computeMaxProfit(prices, currentIndex + 1, 1, txCount + 1, k) ,
                    computeMaxProfit(prices, currentIndex + 1, buy, txCount, k));
    }

    public static int computeMaxProfitBottomsUp(int k, int[] prices) {
        if(k == 0 || prices.length==0)
            return 0;

        int[] cost = new int[k];
        int[] profits = new int[k];

        Arrays.fill(cost, Integer.MAX_VALUE);
        Arrays.fill(profits, 0);

        for(int price: prices) {
            for(int i = 0; i < k; i++) {
                if(i==0) {
                    // first time buy the stock
                    cost[i] = Math.min(cost[i], price);
                    profits[i] = Math.max(profits[i], price - cost[i] );
                } else {
                    // from 2nd time to k times we should buy from previous profits
                    cost[i] = Math.min(cost[i], price - profits[i-1]);
                    profits[i] = Math.max(profits[i], price - cost[i] );
                }
            }
        }

        return profits[k - 1];
    }

    public static void main(String[] args) {
        int[] prices = new int[]{3,2,6,5,0,3};
        System.out.println(maxProfit(2, prices));
        System.out.println(computeMaxProfitBottomsUp(2, prices));
    }
}
