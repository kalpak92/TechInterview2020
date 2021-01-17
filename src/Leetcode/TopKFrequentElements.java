package Leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author kalpak
 *
 * Given a non-empty array of integers, return the k most frequent elements.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 * Note:
 *
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
 * You can return the answer in any order.
 */
public class TopKFrequentElements {
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));
        int[] result = new int[k];
        int idx = k - 1;            // to push the elements in ascending order

        // Create the frequency map
        for(int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        // Build the minHeap.
        for(int key : map.keySet()) {
            minHeap.offer(key);

            if(minHeap.size() > k)
                minHeap.poll();
        }

        while (!minHeap.isEmpty())
            result[idx--] = minHeap.poll();

        return result;
    }

    public static void printArray(int[] nums) {
        for(int i : nums)
            System.out.print(i + " ");

        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,1,2,2,3};
        printArray(topKFrequent(arr, 2));
    }
}

