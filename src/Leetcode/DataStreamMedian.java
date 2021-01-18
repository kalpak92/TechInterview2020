package Leetcode;

import java.util.PriorityQueue;

/**
 * @author kalpak
 *
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 *
 * For example,
 * [2,3,4], the median is 3
 *
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Design a data structure that supports the following two operations:
 *
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 *
 *
 * Example:
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 *
 *
 * Follow up:
 *
 * ** If all integer numbers from the stream are between 0 and 100, how would you optimize it?
 *      We can maintain an integer array of length 100 to store the count of each number along with a total count.
 *      Then, we can iterate over the array to find the middle value to get our median.
 *
 *      Time and space complexity would be O(100) = O(1).
 *
 *
 * ** If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 *      As 99% is between 0-100. So can keep a counter for less_than_hundred and greater_than_hundred.
 *      As we know soluiton will be definately in 0-100 we don't need to know those number which are >100 or <0,
 *      only count of them will be enough.
 */

public class DataStreamMedian {
    PriorityQueue<Integer> lowerHalf;
    PriorityQueue<Integer> upperHalf;
    /** initialize your data structure here. */
    public DataStreamMedian() {
        lowerHalf = new PriorityQueue<>((a, b) -> b - a);
        upperHalf = new PriorityQueue<>();
    }

    public void addNum(int num) {
        lowerHalf.offer(num);
        upperHalf.offer(lowerHalf.poll());

        if(upperHalf.size() > lowerHalf.size())
            lowerHalf.offer(upperHalf.poll());
    }

    public double findMedian() {
        if(lowerHalf.size() > upperHalf.size())
            return lowerHalf.peek()/1.0;

        return (lowerHalf.peek() + upperHalf.peek())/2.0;
    }

    public static void main(String[] args) {
        DataStreamMedian obj = new DataStreamMedian();

        obj.addNum(1);
        obj.addNum(2);
        System.out.println(obj.findMedian());
        obj.addNum(3);
        System.out.println(obj.findMedian());
    }
}
