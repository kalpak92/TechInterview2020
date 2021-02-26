package Leetcode;

/**
 * @author kalpak
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 *
 * Example 1:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 *
 * Example 2:
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.
 *
 * Constraints:
 * 1 <= prices.length <= 10^5
 * 0 <= prices[i] <= 10^4
 */

public class BuyAndSellStock {
    public static int maxProfit(int[] prices) {
        return computeMaxProfitRecursive(prices, 0, Integer.MAX_VALUE, 0);
    }

    private static int computeMaxProfitRecursive(int[] prices, int currentIdx, int minStockPrice, int maxProfit) {
        if(currentIdx >= prices.length)
            return maxProfit;

        maxProfit = Math.max(maxProfit, prices[currentIdx] - minStockPrice);
        minStockPrice = Math.min(minStockPrice, prices[currentIdx]);

        return computeMaxProfitRecursive(prices, currentIdx + 1, minStockPrice, maxProfit);
    }

    public static int maxProfitIterative(int[] prices) {
        int result = Integer.MIN_VALUE;
        int minStockPrice = Integer.MAX_VALUE;

        for(int i = 0; i < prices.length; i++) {
            result = Math.max(result, prices[i] - minStockPrice);
            minStockPrice = Math.min(minStockPrice, prices[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
        System.out.println(maxProfit(prices));
    }
}
