package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * Implement FreqStack, a class which simulates the operation of a stack-like data structure.
 *
 * FreqStack has two functions:
 *
 * - push(int x), which pushes an integer x onto the stack.
 *
 * - pop(), which removes and returns the most frequent element in the stack.
 * If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.
 *
 * Example 1:
 *
 * Input:
 * ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
 * [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
 * Output: [null,null,null,null,null,null,null,5,7,5,4]
 * Explanation:
 * After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:
 *
 * pop() -> returns 5, as 5 is the most frequent.
 * The stack becomes [5,7,5,7,4].
 *
 * pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
 * The stack becomes [5,7,5,4].
 *
 * pop() -> returns 5.
 * The stack becomes [5,7,4].
 *
 * pop() -> returns 4.
 * The stack becomes [5,7].
 *
 *
 * Note:
 *
 * Calls to FreqStack.push(int x) will be such that 0 <= x <= 10^9.
 * It is guaranteed that FreqStack.pop() won't be called if the stack has zero elements.
 * The total number of FreqStack.push calls will not exceed 10000 in a single test case.
 * The total number of FreqStack.pop calls will not exceed 10000 in a single test case.
 * The total number of FreqStack.push and FreqStack.pop calls will not exceed 150000 across all test cases.
 */

public class MaximumFrequencyStack {
    Map<Integer, Integer> map;
    PriorityQueue<int[]> maxHeap;
    int index;

    public MaximumFrequencyStack() {
        map = new HashMap<>();
        maxHeap = new PriorityQueue<>((a, b) -> (a[1] == b[1]) ? b[2] - a[2] : b[1] - a[1]);
        index = 0;
    }

    public void push(int x) {
        map.put(x, map.getOrDefault(x, 0) + 1);
        maxHeap.offer(new int[]{x, map.get(x), index++});
    }

    public int pop() {
        int[] temp = maxHeap.poll();
        map.put(temp[0], map.get(temp[0]) - 1);

        if(map.get(temp[0]) == 0)
            map.remove(temp[0]);

        return temp[0];
    }

    public static void main(String[] args) {
        MaximumFrequencyStack obj = new MaximumFrequencyStack();
        obj.push(5);
        obj.push(7);
        obj.push(5);
        obj.push(7);
        obj.push(4);
        obj.push(5);
        System.out.println(obj.pop());
        System.out.println(obj.pop());
        System.out.println(obj.pop());
        System.out.println(obj.pop());

    }
}

/**
 * Solution Using Two Maps
 * 1. Frequency Map
 * 2. Maximum Frequency Map with Stack
 */

class FreqStack {
    Map<Integer, Integer> frequencyMap;
    Map<Integer, Deque<Integer>> maxFrequencyMap;
    int maxFrequency;

    public FreqStack() {
        frequencyMap = new HashMap<>();
        maxFrequencyMap = new HashMap<>();
        maxFrequency = 0;
    }

    public void push(int x) {
        frequencyMap.put(x, frequencyMap.getOrDefault(x, 0) + 1);

        if(frequencyMap.get(x) > maxFrequency)
            maxFrequency = frequencyMap.get(x);

        maxFrequencyMap.computeIfAbsent(frequencyMap.get(x), z -> new ArrayDeque()).push(x);
    }

    public int pop() {
        int element = maxFrequencyMap.get(maxFrequency).poll();
        frequencyMap.put(element, frequencyMap.get(element) - 1);

        if(maxFrequencyMap.get(maxFrequency).size() == 0)
            maxFrequency--;

        return element;
    }
}