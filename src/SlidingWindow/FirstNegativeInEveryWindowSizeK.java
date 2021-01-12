package SlidingWindow;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author kalpak
 *
 * Given an array and a positive integer k, find the first negative integer for each window(contiguous subarray) of size k.
 * If a window does not contain a negative integer, then print 0 for that window.
 *
 * Examples:
 * Input : arr[] = {-8, 2, 3, -6, 10}, k = 2
 * Output : -8 0 -6 -6
 * First negative integer for each window of size k
 * {-8, 2} = -8
 * {2, 3} = 0 (does not contain a negative integer)
 * {3, -6} = -6
 * {-6, 10} = -6
 *
 * Input : arr[] = {12, -1, -7, 8, -15, 30, 16, 28} , k = 3
 * Output : -1 -1 -7 -15 -15 0
 *
 */

public class FirstNegativeInEveryWindowSizeK {
    public static List<Integer> firstNegativeInteger(int[] arr, int k) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;

        while(j < arr.length) {
            if(arr[j] < 0)
                queue.add(arr[j]);

            if(j - i + 1 < k)
                j++;
            else if (j - i + 1 == k) {
                if (queue.isEmpty())
                    result.add(0);
                else {
                    result.add(queue.peek());
                    if(arr[i] == queue.peek())
                        queue.poll();
                }
                i++;
                j++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{12, -1, -7, 8, -15, 30, 16, 28};
        System.out.println(firstNegativeInteger(arr, 3));
    }
}
