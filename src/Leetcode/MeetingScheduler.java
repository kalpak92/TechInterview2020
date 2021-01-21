package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author kalpak
 *
 * Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration,
 * return the earliest time slot that works for both of them and is of duration duration.
 *
 * If there is no common time slot that satisfies the requirements, return an empty array.
 *
 * The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.
 *
 * It is guaranteed that no two availability slots of the same person intersect with each other.
 * That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.
 *
 *
 * Example 1:
 *
 * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
 * Output: [60,68]
 * Example 2:
 *
 * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
 * Output: []
 *
 *
 * Constraints:
 *
 * 1 <= slots1.length, slots2.length <= 10^4
 * slots1[i].length, slots2[i].length == 2
 * slots1[i][0] < slots1[i][1]
 * slots2[i][0] < slots2[i][1]
 * 0 <= slots1[i][j], slots2[i][j] <= 10^9
 * 1 <= duration <= 10^6
 */

public class MeetingScheduler {
    public static List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        for(int[] slot1 : slots1) {
            // Filtering out availability slots with lesser durations
            if(slot1[1] - slot1[0] >= duration)
                minHeap.offer(slot1);
        }

        for(int[] slot2 : slots2) {
            // Filtering out availability slots with lesser durations
            if(slot2[1] - slot2[0] >= duration)
                minHeap.offer(slot2);
        }

        // Pop out the slots one by one, comparing every consective two to check if having duration time in common.
        while(minHeap.size() > 1) {
            int[] temp = minHeap.poll();
            if(temp[1] >= minHeap.peek()[0] + duration)
                return Arrays.asList(new Integer[]{minHeap.peek()[0], minHeap.peek()[0] + duration});
        }

        return Arrays.asList();
    }

    public static void main(String[] args) {
        int[][] slots1 = new int[][]{{10, 50}, {60, 120}, {140, 210}};
        int[][] slots2 = new int[][]{{0, 15}, {60, 70}};
        int duration = 8;

        System.out.println(minAvailableDuration(slots1, slots2, duration));
    }
}
