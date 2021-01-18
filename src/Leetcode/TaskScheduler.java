package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task.
 * Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.
 *
 * However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array),
 * that is that there must be at least n units of time between any two same tasks.
 *
 * Return the least number of units of times that the CPU will take to finish all the given tasks.
 *
 *
 * Example 1:
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation:
 * A -> B -> idle -> A -> B -> idle -> A -> B
 * There is at least 2 units of time between any two same tasks.
 *
 *
 * Example 2:
 * Input: tasks = ["A","A","A","B","B","B"], n = 0
 * Output: 6
 * Explanation: On this case any permutation of size 6 would work since n = 0.
 * ["A","A","A","B","B","B"]
 * ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"]
 * ...
 * And so on.
 *
 *
 * Example 3:
 * Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 * Output: 16
 * Explanation:
 * One possible solution is
 * A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A
 *
 *
 * Constraints:
 *
 * 1 <= task.length <= 104
 * tasks[i] is upper-case English letter.
 * The integer n is in the range [0, 100].
 *
 */

public class TaskScheduler {
    public static int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a)); // O(log(26))

        int result = 0;

        // Build the frequency map
        for(char c : tasks)
            map.put(c, map.getOrDefault(c, 0) + 1);

        // Create the maxHeap based on the frequency
        maxHeap.addAll(map.keySet());

        while(!maxHeap.isEmpty()) {
            int interval = n + 1;
            Queue<Character> waitQueue = new LinkedList<>();

            while(interval > 0 && !maxHeap.isEmpty()) {
                Character temp = maxHeap.poll();    // Most Frequent task
                map.put(temp, map.get(temp) - 1);   // reduce the count of occurrence, because a unit of task is done;
                // add it to the waitQueue
                waitQueue.offer(temp);

                interval--;     // reduce the interval
                result++;       // update the result
            }

            // Flush the waitQueue and put the tasks back to the MaxHeap
            while(!waitQueue.isEmpty()) {
                Character temp = waitQueue.poll();
                if(map.get(temp) > 0)
                    maxHeap.offer(temp);
            }

            // if the heap is empty, no more tasks to be done
            if(maxHeap.isEmpty())
                break;

            result = result + interval; // // if interval > 0, then it means we need to be idle
        }
        return result;
    }

    public static void main(String[] args) {
        char[] str1 = new char[]{'A','A','A','B','B','B'};
        System.out.println(leastInterval(str1, 2));

        char[] str2 = new char[]{'A','A','A','A','A','A','B','C','D','E','F','G'};
        System.out.println(leastInterval(str2, 2));

    }
}
