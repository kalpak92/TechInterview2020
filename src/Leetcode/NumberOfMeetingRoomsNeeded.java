package Leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author kalpak
 *
 * Given an array of meeting time intervals intervals where intervals[i] = [start_i, end_i],
 * return the minimum number of conference rooms required.
 *
 * Example 1:
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 *
 * Example 2:
 * Input: intervals = [[7,10],[2,4]]
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 10^4
 * 0 <= start_i < end_i <= 10^6
 */

public class NumberOfMeetingRoomsNeeded {
    public static int minMeetingRooms(int[][] intervals) {
        if(intervals.length < 1)
            return 0;

        // Sort the array based on the starting time of the meeting
        Arrays.sort(intervals, (a, b) -> (a[0] -  b[0]));

        // Create a minHeap to get the meeting that finishes first
        // Check if the intervals overlap, then we need a separate room, else merge them
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        minHeap.offer(intervals[0]);

        for(int i = 1; i < intervals.length; i++) {
            if(minHeap.peek()[1] <= intervals[i][0]) {
                int[] temp = minHeap.poll();
                temp[1] = intervals[i][1];
                minHeap.offer(temp);
            } else {
                minHeap.offer(intervals[i]);
            }
        }
        return minHeap.size();
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{0,30},{5,10},{15,20}};
        System.out.println(minMeetingRooms(intervals));
    }
}
