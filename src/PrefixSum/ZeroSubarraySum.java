package PrefixSum;

/*

  @author kalpak
 *
 * Given an array of positive and negative numbers, find if there is a subarray (of size at-least one) with 0 sum.
 *
 * Examples :
 *
 * Input: {4, 2, -3, 1, 6}
 * Output: true
 * There is a subarray with zero sum from index 1 to 3.
 *
 * Input: {4, 2, 0, 1, 6}
 * Output: true
 * There is a subarray with zero sum from index 2 to 2.
 *
 * Input: {-3, 2, 3, 1, 6}
 * Output: false
 * There is no subarray with zero sum.
 */

import java.util.HashSet;
import java.util.Set;

/**
 * ALGORITHM
 * If we consider all prefix sums, we can
 * notice that there is a subarray with 0 sum when :
 * 1) Either a prefix sum repeats or
 * 2) Or prefix sum becomes 0.
 */
public class ZeroSubarraySum {
    public static boolean checkZeroSubarray(int[] arr) {
        int runningSum = 0;
        Set<Integer> sumSet = new HashSet<>();

        for (int j : arr) {
            runningSum += j;
            if (j == 0 || runningSum == 0 || sumSet.contains(runningSum))
                return true;
            sumSet.add(j);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {-3, 2, 3, 1, 6};
        if (checkZeroSubarray(arr))
            System.out.println("Found a subarray with 0 sum");
        else
            System.out.println("No Such Sub Array Exists!");
    }
}
