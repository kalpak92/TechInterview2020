package Hashing;

import java.util.HashSet;
import java.util.Set;

/**
 * Given two sets represented by two arrays, how to check if the given two sets are disjoint or not?
 * It may be assumed that the given arrays have no duplicates.
 *
 * Input: set1[] = {12, 34, 11, 9, 3}
 *        set2[] = {2, 1, 3, 5}
 * Output: Not Disjoint
 * 3 is common in two sets.
 *
 * Input: set1[] = {12, 34, 11, 9, 3}
 *        set2[] = {7, 2, 1, 5}
 * Output: Yes, Disjoint
 * There is no common element in two sets.
 */
public class ArrayDisjoint {
    private static final Set<Integer> elements = new HashSet<>();

    public static boolean areDisjoint(int[] arr1, int[] arr2) {
        for(int i : arr1) {
            elements.add(i);
        }

        for(int i : arr2) {
            if(elements.contains(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr1 = {10, 5, 3, 4, 6};
        int[] arr2 = {8, 7, 9};
        if (areDisjoint(arr1, arr2))
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}
