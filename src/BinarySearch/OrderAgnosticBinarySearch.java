package BinarySearch;

/**
 * @author kalpak
 *
 * Given a sorted array of numbers, find out if a given number key is present in the array.
 * Though we know that the array is sorted, we don’t know if it’s sorted in ascending or descending order. You should assume that the array can have duplicates.
 *
 * Write a function to return the index of the key if it is present in the array, otherwise return -1.
 *
 * Example-1:
 * Input: [1, 2, 3, 4, 5, 6, 7], key = 5,
 * Output: 4
 *
 * Example-2:
 * Input: [10, 6, 4], key = 10,
 * Output: 0
 *
 */

public class OrderAgnosticBinarySearch {
    public static int search(int[] arr, int key) {
        if (arr.length == 0)
            return -1;

        boolean ascend = arr[0] < arr[arr.length-1];
        int start = 0;
        int end = arr.length-1;

        while (start <= end) {
            int mid = (start+end)/2;
            if (arr[mid] == key) return mid;
            if (ascend) {
                if (arr[mid] < key) start = mid+1;
                else end = mid-1;
            } else {
                if (arr[mid] < key) end = mid-1;
                else start = mid+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[] { 4, 6, 10 }, 10));
        System.out.println(search(new int[] {1, 2, 3, 4, 5, 6, 7}, 5));
    }
}
