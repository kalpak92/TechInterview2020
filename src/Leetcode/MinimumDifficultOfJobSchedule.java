package Leetcode;

import java.util.Arrays;

/**
 * @author kalpak
 *
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the i-th job,
 * you have to finish all the jobs j where 0 <= j < i).
 *
 * You have to finish at least one task every day.
 * The difficulty of a job schedule is the sum of difficulties of each day of the d days.
 * The difficulty of a day is the maximum difficulty of a job done in that day.
 *
 * Given an array of integers jobDifficulty and an integer d.
 * The difficulty of the i-th job is jobDifficulty[i].
 *
 * Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
 *
 * Example 1:
 * Input: jobDifficulty = [6,5,4,3,2,1], d = 2
 * Output: 7
 * Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
 * Second day you can finish the last job, total difficulty = 1.
 * The difficulty of the schedule = 6 + 1 = 7
 *
 *
 * Example 2:
 * Input: jobDifficulty = [9,9,9], d = 4
 * Output: -1
 * Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
 *
 *
 * Example 3:
 * Input: jobDifficulty = [1,1,1], d = 3
 * Output: 3
 * Explanation: The schedule is one job per day. total difficulty will be 3.
 *
 *
 * Example 4:
 * Input: jobDifficulty = [7,1,7,1,7,1], d = 3
 * Output: 15
 * Example 5:
 *
 * Input: jobDifficulty = [11,111,22,222,33,333,44,444], d = 6
 * Output: 843
 *
 *
 * Constraints:
 *
 * 1 <= jobDifficulty.length <= 300
 * 0 <= jobDifficulty[i] <= 1000
 * 1 <= d <= 10
 *
 */

public class MinimumDifficultOfJobSchedule {
    public static int minDifficultyRecursive(int[] jobDifficulty, int d) {
        if(d > jobDifficulty.length)
            return -1;

        return findMinDifficulty(jobDifficulty, d - 1, 0);
    }

    private static int findMinDifficulty(int[] jobDifficulty, int days, int currentIdx) {
        // Base Case
        if(days == 0) {
            // if there's only one day left, just return the max value of the remaining array.
            int max = jobDifficulty[currentIdx];

            for(int i = currentIdx; i < jobDifficulty.length; i++)
                max = Math.max(max, jobDifficulty[i]);

            return max;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        //try out all ranges for that day
        for(int i = currentIdx; i < jobDifficulty.length - days; i++) {
            // sequentially increase the size of the jobs for that day and
            // recursively call the function for the subsequent days.
            max = Math.max(max, jobDifficulty[i]);
            min = Math.min(min, max + findMinDifficulty(jobDifficulty, days - 1, i + 1));
        }
        return min;
    }

    public static int minDifficultyTopDown(int[] jobDifficulty, int d) {
        if(d > jobDifficulty.length)
            return -1;

        int[][] dp = new int[d - 1][jobDifficulty.length];
        for(int[] day : dp)
            Arrays.fill(day, -1);

        return findMinDifficultyTopDown(jobDifficulty, dp, d - 1, 0);
    }

    private static int findMinDifficultyTopDown(int[] jobDifficulty, int[][] dp, int days, int currentIdx) {
        // Base Case
        if(days == 0) {
            // if there's only one day left, just return the max value of the remaining array.
            int max = jobDifficulty[currentIdx];

            for(int i = currentIdx; i < jobDifficulty.length; i++)
                max = Math.max(max, jobDifficulty[i]);

            return max;
        }

        //just making sure we start indexing from 0
        int day = dp.length - days;

        //we already have this in the dp, just return it right here
        if(dp[day][currentIdx] != -1)
            return dp[day][currentIdx];

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        //try out all ranges for that day
        for(int i = currentIdx; i < jobDifficulty.length - days; i++) {
            // sequentially increase the size of the jobs for that day and
            // recursively call the function for the subsequent days.
            max = Math.max(max, jobDifficulty[i]);
            min = Math.min(min, max + findMinDifficultyTopDown(jobDifficulty, dp, days - 1, i + 1));
        }
        return dp[day][currentIdx] = min;
    }

    public static void main(String[] args) {
        int[] jobDifficulty = new int[]{5,2,4,1,6,2,7};
        System.out.println(minDifficultyRecursive(jobDifficulty, 3));
        System.out.println(minDifficultyTopDown(jobDifficulty, 3));
    }
}
