package Leetcode;

import java.util.*;

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

        // Create the frequency mappublic static int[] topKFrequent(int[] nums, int k) {
        //        Map<Integer, Integer> map = new HashMap<>();
        //        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));
        //        int[] result = new int[k];
        //        int idx = k - 1;            // to push the elements in ascending order
        //
        //        // Create the frequency map
        //        for(int i : nums) {
        //            map.put(i, map.getOrDefault(i, 0) + 1);
        //        }
        //
        //        // Build the minHeap.
        //        for(int key : map.keySet()) {
        //            minHeap.offer(key);
        //
        //            if(minHeap.size() > k)
        //                minHeap.poll();
        //        }
        //
        //        while (!minHeap.isEmpty())
        //            result[idx--] = minHeap.poll();
        //
        //        return result;
        //    }
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

    public static int[] topKFrequentUsingQuickSelect(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[k];
        int[][] numberAndCount;

        // Create the frequency map
        for(int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        numberAndCount = new int[map.size()][2];
        int index = 0;

        for(int key : map.keySet()) {
            numberAndCount[index][0] = key;
            numberAndCount[index][1] = map.get(key);
            index++;
        }

        int left = 0;
        int right = numberAndCount.length - 1;

        while(true) {
            int pivot = partition(numberAndCount, left, right);

            if(pivot == k - 1) {
                // result = numberAndCount[pivot];
                break;
            }

            if(pivot < k - 1)
                left = pivot + 1;
            else
                right = pivot - 1;
        }

        for(int i = 0; i < k && i < numberAndCount.length; i++) {
            result[i] = numberAndCount[i][0];
        }

        return result;
    }

    private static int partition(int[][] numberAndCount, int start, int end) {
        int pivot = numberAndCount[end][1];
        int pIndex = start;

        for(int i = start; i < end; i++) {
            if(numberAndCount[i][1] >= pivot) {              // Sort in descending order
                swap(numberAndCount, i, pIndex);
                pIndex++;
            }
        }

        swap(numberAndCount, end, pIndex);
        return pIndex;
    }

    private static void swap(int[][] numberAndCount, int i, int j) {
        int[] temp = numberAndCount[i];
        numberAndCount[i] = numberAndCount[j];
        numberAndCount[j] = temp;
    }

    public static int[] topKFrequentUsingBucketSort(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[k];

        // Create the frequency map
        for(int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        // Buckets based on frequencies
        // Use a list because there can be multiple numbers of the same frequency
        List<Integer>[] buckets = new ArrayList[nums.length + 1];

        for(int key : map.keySet()) {
            if(buckets[map.get(key)] == null)
                buckets[map.get(key)] = new ArrayList<>();

            buckets[map.get(key)].add(key);
        }

        // Process the buckets from the highest frequency
        int resultIdx = 0;
        for(int i = buckets.length - 1; i >= 0; i--) {
            if(buckets[i] == null)
                continue;
            for(int num : buckets[i]) {
                result[resultIdx] = num;
                resultIdx++;

                if(resultIdx == k)
                    return result;
            }

        }
        return result;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,1,1,2,2,3};
        System.out.println(Arrays.toString(topKFrequent(arr, 2)));

        System.out.println(Arrays.toString(topKFrequentUsingQuickSelect(arr, 2)));

        System.out.println(Arrays.toString(topKFrequentUsingBucketSort(arr, 2)));
    }
}

