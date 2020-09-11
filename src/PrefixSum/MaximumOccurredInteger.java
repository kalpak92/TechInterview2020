package PrefixSum;

/*
  @author kalpak
 *
 * Given n ranges of the form L and R, the task is to find the maximum occurred integer in all the ranges.
 * If more than one such integer exists, print the smallest one.
 * 0 <= Li, Ri < 1000000.
 * Examples :
 *
 * Input : L1 = 1 R1 = 15
 *         L2 = 4 R2 = 8
 *         L3 = 3 R3 = 5
 *         L3 = 1 R3 = 4
 * Output : 4
 *
 * Input : L1 = 1 R1 = 15
 *         L2 = 5 R2 = 8
 *         L3 = 9 R3 = 12
 *         L4 = 13 R4 = 20
 *         L5 = 21 R5 = 30
 * Output : 5
 * Numbers having maximum occurrence i.e 2  are 5, 6,
 * 7, 8, 9, 10, 11, 12, 13, 14, 15. The smallest number among all are 5.
 */

/**
 * ALGORITHM:
 *
 * Initialize an array arr[] to 0.
 * For each range i, add +1 at Li index and -1 at Ri of the array.
 * Find the prefix sum of the array and find the smallest index having maximum prefix sum.
 */
public class MaximumOccurredInteger {
    static int MAX = 1000000;
    public static int maximumOccurredElement(int[] L, int[] R, int n) {
        // Initializing all element of array to 0.
        int[] arr = new int[MAX];
        int maximumIdxTraversed = -1;

        // Adding +1 at Li index and subtracting 1 at Ri + 1 index.
        // If you'll put -1 in arr[R[i]] then it denotes that you are removing the last element from the given range of arr[L[i]] to arr[R[i]].
        // On the other hand if we do arr[R[i]+1] then it signifies that we removed element at R[i]+1 'th index from the previous range.
        for (int i = 0; i < n; i++) {
            arr[L[i]] = arr[L[i]] + 1;
            arr[R[i] + 1] = arr[R[i]] - 1;

            if(R[i] > maximumIdxTraversed){
                maximumIdxTraversed = R[i];
            }
        }

        // Finding prefix sum and index having maximum prefix sum.
        int runningSum = arr[0];
        int maxIdx = 0;                 // If more than one such integer exists, print the smallest one.

        for (int i = 1; i < maximumIdxTraversed+1; i++) {
            arr[i] += arr[i - 1];
            if (runningSum < arr[i]) {
                runningSum = arr[i];
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public static void main(String[] args) {
        int[] L = { 1, 4, 9, 13, 21 };
        int[] R = { 15, 8, 12, 20, 30 };
        int n = L.length;

        System.out.println("The Maximum occurred element: " +maximumOccurredElement(L, R, n));
    }
}
