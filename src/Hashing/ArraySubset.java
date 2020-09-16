package Hashing;

import java.util.HashSet;
import java.util.Set;

/**
 * @author kalpak
 *
 * Given two arrays: arr1[0..m-1] and arr2[0..n-1].
 * Find whether arr2[] is a subset of arr1[] or not. Both the arrays are not in sorted order.
 * It may be assumed that elements in both array are distinct.
 *
 * Examples:
 *
 *     Input: arr1[] = {11, 1, 13, 21, 3, 7}, arr2[] = {11, 3, 7, 1}
 *     Output: arr2[] is a subset of arr1[]
 *
 *     Input: arr1[] = {1, 2, 3, 4, 5, 6}, arr2[] = {1, 2, 4}
 *     Output: arr2[] is a subset of arr1[]
 *
 *     Input: arr1[] = {10, 5, 2, 23, 19}, arr2[] = {19, 5, 3}
 *     Output: arr2[] is not a subset of arr1[]
 */

public class ArraySubset {
    public static boolean isSubset(int[] arr1, int[] arr2) {
        Set<Integer> elements = new HashSet<>();
        // put all the elements of arr1 into the
        for(int i : arr1)
            elements.add(i);

        /*
         now check if all the elements of arr2 is present or not.
         if not, return false.
        */
        for(int i : arr2) {
            if(elements.contains(i) == false) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr1 = {11, 1, 13, 21, 3, 7};
        int[] arr2 = {11, 3, 7, 1};

        if(isSubset(arr1, arr2))
            System.out.println("arr2 is a subset of arr1");
        else
            System.out.println("arr2 is not a subset of arr1");
    }
}
