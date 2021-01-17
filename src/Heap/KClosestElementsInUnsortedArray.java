package Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author kalpak
 *
 * Given an unsorted array and two numbers x and k, find k closest values to x.
 *
 * Examples:
 * Input : arr[] = {10, 2, 14, 4, 7, 6}, x = 5, k = 3
 * Output : 4 6 7
 * Three closest values of x are 4, 6 and 7.
 *
 * Input : arr[] = {-10, -50, 20, 17, 80}, x = 20, k = 2
 * Output : 17, 20
 *
 */

public class KClosestElementsInUnsortedArray {
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> ((a[0] == b[0]) ? (b[1] - a[1]) : b[0] - a[0]));

        for(int i = 0; i < arr.length ; i++) {
            maxHeap.offer(new int[]{Math.abs(x-arr[i]),arr[i]});

            if(maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        while(!maxHeap.isEmpty()) {
            result.add(0, maxHeap.poll()[1]);
        }

        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {-10, 20, 17, 80, -50};
        System.out.println(findClosestElements(arr, 2, 20));

        arr = new int[]{10, 2, 14, 4, 7, 6};
        System.out.println(findClosestElements(arr, 3, 5));
    }
}
