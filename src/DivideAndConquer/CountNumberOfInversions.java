package DivideAndConquer;

import java.util.Arrays;

public class CountNumberOfInversions {
    static int findInversionCount(int arr[], int left, int right) {
        int inversionCount = 0;

        if (left < right) {
            // Find the middle point
            int mid = left + (right - left) / 2;

            // Sort first and second halves and store the inversion count
            inversionCount += findInversionCount(arr, left, mid);
            inversionCount += findInversionCount(arr, mid + 1, right);

            // Merge the sorted halves
            inversionCount += merge(arr, left, mid, right);
        }
        return inversionCount;
    }

    static int merge(int arr[], int left, int mid, int right)  {
        int inversionCount = 0;

        // Left subarray
        int[] leftHalf = Arrays.copyOfRange(arr, left, mid + 1);

        // Right subarray
        int[] rightHalf = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left;

        while (i < leftHalf.length && j < rightHalf.length) {
            if (leftHalf[i] <= rightHalf[j])
                arr[k++] = leftHalf[i++];
            else {
                arr[k++] = rightHalf[j++];
                inversionCount += (mid + 1) - (left + i);
            }
        }

        while(i < leftHalf.length)
            arr[k++] = leftHalf[i++];

        while(j < rightHalf.length)
            arr[k++]=rightHalf[j++];

        return inversionCount;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 3, 2, 4, 1};
        System.out.println(findInversionCount(arr, 0 , arr.length - 1));
    }
}
