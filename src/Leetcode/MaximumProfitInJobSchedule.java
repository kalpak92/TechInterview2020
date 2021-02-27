package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author kalpak
 *
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
 *
 * You're given the startTime, endTime and profit arrays,
 * return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.
 *
 * If you choose a job that ends at time X you will be able to start another job that starts at time X.
 *
 *
 * Example 1:
 * Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * Output: 120
 * Explanation: The subset chosen is the first and fourth job.
 * Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
 *
 * Example 2:
 * Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
 * Output: 150
 * Explanation: The subset chosen is the first, fourth and fifth job.
 * Profit obtained 150 = 20 + 70 + 60.
 *
 * Example 3:
 * Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
 * Output: 6
 *
 *
 * Constraints:
 *
 * 1 <= startTime.length == endTime.length == profit.length <= 5 * 10^
 * 1 <= startTime[i] < endTime[i] <= 10^9
 * 1 <= profit[i] <= 10^4
 *
 */

public class MaximumProfitInJobSchedule {
    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        // for each job = [s, e, p], where s = start time, e = end time, p = profit,
        // case 1: don't do this job  -> nothing changes, dp[end_time] = dp[previous end_time]
        // case 2: do this job ->
        //                      dp[end_time] = (dp[previous end_time <= s that gives max profit]) + p
        //         find the max profit we can make before start time s (using binary search),
        //          so we can know the max profit we can make after doing this job

        Job[] jobs = new Job[endTime.length];

        for (int i = 0; i < endTime.length; i++)
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);

        // Sort in increasing order of end time
        Arrays.sort(jobs, (j1, j2) -> Integer.compare(j1.end, j2.end));

        List<Integer> endTimes = new ArrayList<>();
        List<Integer> profits = new ArrayList<>();

        endTimes.add(0); // base case to avoid IndexOutBoundExp
        profits.add(0);

        for (Job job : jobs) {
            int withoutProfit = profits.get(profits.size() - 1);
            int includingProfit = profits.get(search(endTimes, job.start)) + job.profit;

            if (includingProfit > withoutProfit) {
                endTimes.add(job.end);
                profits.add(includingProfit);
            }
        }

        return profits.get(profits.size() - 1);
    }

    private static int search(List<Integer> endTimes, int target) {
        int start = 0;
        int end = endTimes.size() - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (endTimes.get(mid) >= target)
                end = mid;
            else
                start = mid + 1;
        }

        return endTimes.get(start) <= target ? start : start - 1;
    }

    static class Job {
        int start;
        int end;
        int profit;

        public Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }

    public static void main(String[] args) {
        int[] startTime = new int[]{1, 2, 3, 3};
        int[] endTime = new int[]{3, 4, 5, 6};
        int[] profit = new int[]{50, 10, 40, 70};

        System.out.println(jobScheduling(startTime, endTime, profit));
    }
}
