package Leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author kalpak
 *
 * There are n engineers numbered from 1 to n and two arrays: speed and efficiency,
 * where speed[i] and efficiency[i] represent the speed and efficiency for the i-th engineer respectively.
 *
 * Return the maximum performance of a team composed of at most k engineers,
 * since the answer can be a huge number, return this modulo 10^9 + 7.
 *
 * The performance of a team is the sum of their engineers' speeds multiplied by the minimum efficiency among their engineers.
 *
 *
 * Example 1:
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
 * Output: 60
 * Explanation:
 * We have the maximum performance of the team by selecting engineer 2 (with speed=10 and efficiency=4) and engineer 5 (with speed=5 and efficiency=7).
 * That is, performance = (10 + 5) * min(4, 7) = 60.
 *
 * Example 2:
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
 * Output: 68
 * Explanation:
 * This is the same example as the first but k = 3. We can select engineer 1, engineer 2 and engineer 5 to get the maximum performance of the team.
 * That is, performance = (2 + 10 + 5) * min(5, 4, 7) = 68.
 *
 * Example 3:
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
 * Output: 72
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 * speed.length == n
 * efficiency.length == n
 * 1 <= speed[i] <= 10^5
 * 1 <= efficiency[i] <= 10^8
 * 1 <= k <= n
 *
 */

public class MaximumPerformanceOfTeam {
    public static int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int MOD = (int) (1e9 + 7);
        int[][] engineers = new int[n][2];

        for (int i = 0; i < n; ++i)
            engineers[i] = new int[] {efficiency[i], speed[i]};

        // Sort the array based on decreasing order of efficiency.
        Arrays.sort(engineers, (a, b) -> b[0] - a[0]);

        // MinHeap based on speed.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, (a, b) -> a - b);

        long result = Long.MIN_VALUE;
        long totalSpeed = 0;

        for (int[] engineer : engineers) {  // process the array from higest efficiency to lowest
            if (minHeap.size() == k)
                totalSpeed -= minHeap.poll();  // layoff the one with min speed

            minHeap.add(engineer[1]);
            totalSpeed = (totalSpeed + engineer[1]);

            result = Math.max(result, (totalSpeed * engineer[0]));  // min efficiency is the efficiency of new engineer
        }
        return (int) (result % MOD);
    }

    public static void main(String[] args) {
        System.out.println("The maximum performance of the team is : " + maxPerformance(6, new int[]{2,10,3,1,5,8}, new int[]{5,4,3,9,7,2}, 2));
    }
}
