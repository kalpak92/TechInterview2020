package Heap;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author kalpak
 *
 * Given an array of integers and two numbers k1 and k2. Find the sum of all elements between given two k1’th and k2’th smallest elements of the array.
 * It may be assumed that (1 <= k1 < k2 <= n) and all elements of array are distinct.
 *
 * Examples :
 * Input : arr[] = {20, 8, 22, 4, 12, 10, 14},  k1 = 3,  k2 = 6
 * Output : 26
 *          3rd smallest element is 10. 6th smallest element
 *          is 20. Sum of all element between k1 & k2 is
 *          12 + 14 = 26
 *
 * Input : arr[] = {10, 2, 50, 12, 48, 13}, k1 = 2, k2 = 6
 * Output : 73
 */

public class SumOfK1andK2SmallestElements {
    public static int findSumOfElements(int[] nums, int k1, int k2) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int result = 0;

        for(int i : nums)
            minHeap.offer(i);

        for(int i = 0; i < k1; i++)
            minHeap.poll();

        for(int i = 0; i < k2 - k1 - 1; i++)
            result += minHeap.poll();

        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{20, 8, 22, 4, 12, 10, 14};
        System.out.println(findSumOfElements(nums, 3, 6));
    }
}
