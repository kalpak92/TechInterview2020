package Leetcode;

import java.util.PriorityQueue;

/**
 * @author kalpak
 *
 * You have an inventory of different colored balls, and there is a customer that wants orders balls of any color.
 *
 * The customer weirdly values the colored balls. Each colored ball's value is the number of balls of that color you currently have in your inventory.
 * For example, if you own 6 yellow balls, the customer would pay 6 for the first yellow ball.
 * After the transaction, there are only 5 yellow balls left, so the next yellow ball is then valued at 5 (i.e., the value of the balls decreases as you sell more to the customer).
 *
 * You are given an integer array, inventory, where inventory[i] represents the number of balls of the ith color that you initially own.
 * You are also given an integer orders, which represents the total number of balls that the customer wants. You can sell the balls in any order.
 *
 * Return the maximum total value that you can attain after selling orders colored balls. As the answer may be too large, return it modulo 109 + 7.
 *
 *
 * Example 1:
 * Input: inventory = [2,5], orders = 4
 * Output: 14
 * Explanation: Sell the 1st color 1 time (2) and the 2nd color 3 times (5 + 4 + 3).
 * The maximum total value is 2 + 5 + 4 + 3 = 14.
 *
 *
 * Example 2
 * Input: inventory = [3,5], orders = 6
 * Output: 19
 * Explanation: Sell the 1st color 2 times (3 + 2) and the 2nd color 4 times (5 + 4 + 3 + 2).
 * The maximum total value is 3 + 2 + 5 + 4 + 3 + 2 = 19.
 *
 *
 * Example 3:
 * Input: inventory = [2,8,4,10,6], orders = 20
 * Output: 110
 *
 *
 * Example 4:
 * Input: inventory = [1000000000], orders = 1000000000
 * Output: 21
 * Explanation: Sell the 1st color 1000000000 times for a total value of 500000000500000000. 500000000500000000 modulo 109 + 7 = 21.
 *
 *
 * Constraints:
 *
 * 1 <= inventory.length <= 10^5
 * 1 <= inventory[i] <= 10^9
 * 1 <= orders <= min(sum(inventory[i]), 10^9)
 *
 */

public class SellDiminishingValuedColouredBalls {
    public static int maxProfit(int[] inventory, int orders) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        for (int num : inventory) {
            maxHeap.offer(num);
        }

        long result = 0;
        final int mod = 1000000007;
        int current = maxHeap.poll();
        int count = 1; // When pq poll the next element, +1 to count.

        while(orders > 0) {
            int next = maxHeap.isEmpty() ? 0 : maxHeap.peek();

            // If the number for [next + 1, current] less than orders, add them ALL.
            if ((current - next) * count <= orders) {
                // Add all the sum, and don't forget cast!
                long num =  (1L * (next + 1 + current) * (current - next) * count / 2) % mod;
                result = (result + num) % mod;
                orders -= (current - next) * count;

            } else {
                // If the number for [next + 1, current] greater than orders, only add partially.
                // Calculate the new next where the add stops.
                next = current - orders / count;
                long num =  (1L * (next + 1 + current) * (current - next) * count / 2) % mod;
                result = (result + num) % mod;
                // For the last number to add (new next), and don't forget cast! I forget here in contest!
                result = (result + 1L * next * (orders % count)) % mod;
                orders = 0;
            }

            if (!maxHeap.isEmpty())
                current = maxHeap.poll();

            count++;
        }
        return (int)result;
    }

    public static void main(String[] args) {
        int[] inventory = new int[]{2,8,4,10,6};
        System.out.println("The maximum total value that can be attained after selling colored balls : " + maxProfit(inventory, 20));
    }
}
