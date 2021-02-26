package Leetcode;

/**
 * @author kalpak
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 *
 * Note: You may not engage in multiple transactions at the same time
 * (i.e., you must sell the stock before you buy again).
 *
 *
 * Example 1:
 * Input: prices = [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 *
 * Example 2:
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later,
 * as you are engaging multiple transactions at the same time. You must sell before buying again.
 *
 * Example 3:
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 * Example 4:
 * Input: prices = [1]
 * Output: 0
 *
 * Constraints:
 * 1 <= prices.length <= 10^5
 * 0 <= prices[i] <= 10^5
 */

public class BuyAndSellStockIII {
    public static int maxProfit(int[] prices) {
        if(prices.length == 0 || prices == null)
            return 0;

        int buy1    = Integer.MAX_VALUE;
        int profit1 = Integer.MIN_VALUE;
        int buy2    = Integer.MAX_VALUE;
        int profit2 = Integer.MIN_VALUE;

        for(int i = 0; i < prices.length; i++) {
            buy1    = Math.min(buy1, prices[i]);
            profit1 = Math.max(profit1, prices[i] - buy1);
            buy2    = Math.min(buy2, prices[i] - profit1);
            profit2 = Math.max(profit2, prices[i] - buy2);
        }
        return profit2;
    }

    public static int maxProfitUsingDivideAndConquerAndDP(int[] prices) {

        if(prices.length == 0 || prices == null) {
            return 0;
        }

        int[] left = new int[prices.length];        // highest profit in 0 ... i
        int[] right = new int[prices.length];       // highest profit in i ... n - 1

        // DP from left to right
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            left[i] = Math.max(left[i - 1], prices[i] - min);
        }

        // DP from right to left
        int max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            right[i] = Math.max(right[i + 1], max - prices[i]);
        }

        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            profit = Math.max(profit, left[i] + right[i]);
        }

        return profit;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{3,3,5,0,0,3,1,4};
        System.out.println(maxProfit(prices));
        System.out.println(maxProfitUsingDivideAndConquerAndDP(prices));
    }
}
