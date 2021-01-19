package Leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author kalpak
 *
 * We are given a list schedule of employees, which represents the working time for each employee.
 *
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 *
 * Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
 *
 * (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays.
 * For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).
 * Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
 *
 *
 * Example 1:
 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * Output: [[3,4]]
 * Explanation: There are a total of three employees, and all common
 * free time intervals would be [-inf, 1], [3, 4], [10, inf].
 * We discard any intervals that contain inf as they aren't finite.
 *
 *
 * Example 2:
 * Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 * Output: [[5,6],[7,9]]
 *
 * Constraints:
 *
 * 1 <= schedule.length , schedule[i].length <= 50
 * 0 <= schedule[i].start < schedule[i].end <= 10^8
 */


public class EmployeeFreeTime {
    static class Interval {
        public int start;
        public int end;

        public Interval() {}

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    };

    public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> result = new ArrayList<>();
        PriorityQueue<Interval> startTimeMinHeap = new PriorityQueue<>((a, b) -> a.start - b.start);

        schedule.forEach(i -> startTimeMinHeap.addAll(i));

        Interval temp = startTimeMinHeap.poll();
        while(!startTimeMinHeap.isEmpty()) {
            if(temp.end < startTimeMinHeap.peek().start) { // disjoint interval
                result.add(new Interval(temp.end, startTimeMinHeap.peek().start));
                temp = startTimeMinHeap.poll();
            } else {
                // overlapping or intersecting intervals
                temp = temp.end < startTimeMinHeap.peek().end ? startTimeMinHeap.peek() : temp;
                startTimeMinHeap.poll();
            }
        }

        return result;
    }

    public static void printResult(List<Interval> result) {
        for(Interval r :result)
            System.out.println(r.start + ", " + r.end);
        System.out.println();
    }

    public static void main(String[] args) {
        List<List<Interval>> interval = new ArrayList<>();

        Interval i1 = new Interval(1, 2);
        Interval i2 = new Interval(5, 6);
        List<Interval> employee1 = new ArrayList<>();
        employee1.add(i1);
        employee1.add(i2);
        interval.add(employee1);

        Interval i3 = new Interval(1, 3);
        Interval i4 = new Interval(4, 10);
        List<Interval> employee2 = new ArrayList<>();
        employee2.add(i3);
        employee2.add(i4);
        interval.add(employee2);

        printResult(employeeFreeTime(interval));;

        interval = new ArrayList<>();
        i1 = new Interval(1, 3);
        i2 = new Interval(6, 7);
        employee1 = new ArrayList<>();
        employee1.add(i1);
        employee1.add(i2);
        interval.add(employee1);

        i3 = new Interval(2, 4);
        i4 = new Interval(2, 5);
        Interval i5 = new Interval(9, 12);
        employee2 = new ArrayList<>();
        employee2.add(i3);
        employee2.add(i4);
        employee2.add(i5);
        interval.add(employee2);

        printResult(employeeFreeTime(interval));

    }
}
