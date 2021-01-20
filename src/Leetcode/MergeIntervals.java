package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author kalpak
 *
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 *
 */

public class MergeIntervals {
    public static int[][] merge(int[][] intervals) {
        if(intervals.length <= 1)
            return intervals;

        List<int []> result = new ArrayList<>();

        // Sort on the basis of the start element of each interval
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Add the first interval to the list and then check for overlaps
        result.add(intervals[0]);
        int[] currentInterval = intervals[0];

        for(int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] <= currentInterval[1])
                currentInterval[1] = Math.max(currentInterval[1], intervals[i][1]);
            else {
                currentInterval = intervals[i];
                result.add(intervals[i]);
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] result = merge(intervals);

        for(int[] i: result)
        System.out.print("["+i[0] + " " +i[1] + "], ");
    }
}
