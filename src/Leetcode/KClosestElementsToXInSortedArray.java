package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.
 *
 * An integer a is closer to x than an integer b if:
 *
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * Output: [1,2,3,4]
 * Example 2:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = -1
 * Output: [1,2,3,4]
 *
 *
 * Constraints:
 *
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 10^4
 * arr is sorted in ascending order.
 * -10^4 <= arr[i], x <= 10^4
 *
 */

public class KClosestElementsToXInSortedArray {
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - k - 1;

        while (left <= right) {
            int mid = (left + right) /2;

            if ((x - arr[mid]) > (arr[mid + k] - x))  {
                // because the range can start from mid + 1 as the
                // arr[mid] is more than the arr[mid+k]

                // case 1: x - A[mid] < A[mid + k] - x, need to move window go left
                //-------x----A[mid]-----------------A[mid + k]----------

                // case 2: x - A[mid] < A[mid + k] - x, need to move window go left again
                // -------A[mid]----x-----------------A[mid + k]----------
                left = mid + 1;
            }
            else {
                // arr[mid] - x <= arr[mid+k] - x
                // then, this range [mid, mid + k - 1] is safe comparing to  mid + k because of this comparison

                // case 3: x - A[mid] > A[mid + k] - x, need to move window go right
                // -------A[mid]------------------x---A[mid + k]----------

                // case 4: x - A[mid] > A[mid + k] - x, need to move window go right
                // -------A[mid]---------------------A[mid + k]----x------
                right = mid - 1;
            }
        }

        List<Integer> list = new LinkedList<>();

        for (int i = left; i < left + k; i++) {
            list.add(arr[i]);
        }

        return list;
    }

    public static List<Integer> findClosestElementsUsingHeap(int[] arr, int k, int x) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> (Math.abs(b - x) == Math.abs(a - x)) ? (b - a)
                : (Math.abs(b - x) - Math.abs(a - x)));

        for(int i : arr) {
            maxHeap.offer(i);

            if(maxHeap.size() > k)
                maxHeap.poll();
        }

        List<Integer> result = new ArrayList<>();
        while(!maxHeap.isEmpty())
            result.add(maxHeap.poll());

        Collections.sort(result);

        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        System.out.println(findClosestElements(nums, 4, 1));
        System.out.println(findClosestElementsUsingHeap(nums, 4, 1));
    }
}
