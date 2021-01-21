package Leetcode;

import java.util.Arrays;

/**
 * @author kalpak
 *
 * Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.
 *
 * Example 1:
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: false
 *
 * Example 2:
 * Input: intervals = [[7,10],[2,4]]
 * Output: true
 *
 * Constraints:
 *
 * 0 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= start_i < end_i <= 10^6
 *
 */

public class ConflictingAppointments {
    public static boolean canAttendMeetings(int[][] intervals) {
        if(intervals == null)
            return false;

        if(intervals.length == 0)
            return true;

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        for(int i = 1; i < intervals.length; i++) {
            if(intervals[i-1][1] > intervals[i][0])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{0, 30}, {5, 10}, {15, 20}};
        System.out.println(canAttendMeetings(intervals));
    }
}
