package PrefixSum;

/*
  @author kalpak
 *
 * Consider an array of size n with all initial values as 0, we need to perform following m range increment operations.
 *
 * increment(a, b, k) : Increment values from 'a'
 *                      to 'b' by 'k'.
 * After m operations, we need to calculate the maximum of the values in the array.
 * Examples:
 *
 * Input : n = 5 m = 3
 *         a = 0, b = 1, k = 100
 *         a = 1, b = 4, k = 100
 *         a = 2, b = 3, k = 100
 * Output : 200
 * Explanation:
 * Initially array = {0, 0, 0, 0, 0}
 * After first operation:
 * array = {100, 100, 0, 0, 0}
 * After second operation:
 * array = {100, 200, 100, 100, 100}
 * After third operation:
 * array = {100, 200, 200, 200, 100}
 * Maximum element after m operations
 * is 200.
 *
 * Input : n = 4 m = 3
 *         a = 1, b = 2, k = 603
 *         a = 0, b = 0, k = 286
 *         a = 3, b = 3, k = 882
 * Output : 882
 * Explanation:
 * Initially array = {0, 0, 0, 0}
 * After first operation:
 * array = {0, 603, 603, 0}
 * After second operation:
 * array = {286, 603, 603, 0}
 * After third operation:
 * array = {286, 603, 603, 882}
 * Maximum element after m operations
 * is 882.
 */

/**
 * ALGORITHM:
 * Perform two things in a single operation:
 * 1. Add k-value to only lower_bound of a range.
 * 2. Reduce upper_bound + 1 index by k-value.
 * After all operations, add all values, check the maximum sum and print maximum sum.
 */

public class MaxValAfterMrangeIncrement {
    public static int getMaxVal(int n, int[] a, int[] b, int[] k, int m) {
        int[] arr = new int [n + 1];

        for(int i = 0; i < m; i++) {
            int lowerBound = a[i];
            int upperBound = b[i];

            // update the array
            arr[lowerBound] = arr[lowerBound] + k[i];
            arr[upperBound + 1] = arr[upperBound + 1] - k[i];
        }

        // Check the maximum sum
        int runningSum = 0;
        int result = 0;
        for (int j : arr) {
            runningSum += j;
            result = Math.max(result, runningSum);
        }
        return result;
    }

    public static void main(String[] args) {
        // Number of values
        int n = 5;

        int[] a = {0, 1, 2};
        int[] b = {1, 4, 3};
        int[] k = {100, 100, 100};

        // m is number of operations.
        int m = a.length;

        System.out.println("The maximum value after m range increment operations: " + getMaxVal(n, a, b, k, m));
    }
}
