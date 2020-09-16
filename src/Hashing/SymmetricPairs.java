package Hashing;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Given an array of pairs, find all symmetric pairs in it
 * Two pairs (a, b) and (c, d) are said to be symmetric if c is equal to b and a is equal to d. For example, (10, 20) and (20, 10) are symmetric.
 *
 * Given an array of pairs find all symmetric pairs in it.
 * It may be assumed that the first elements of all pairs are distinct.
 *
 * Example:
 * Input: arr[] = {{11, 20}, {30, 40}, {5, 10}, {40, 30}, {10, 5}}
 * Output: Following pairs have symmetric pairs
 * (30, 40)
 * (5, 10)
 *
 */

public class SymmetricPairs {
    private static Map<Integer, Integer> numberPairs = new HashMap<>();

    public static List<List<Integer>> findSymmetricPairs(int arr[][]) {
        List<List<Integer>> result = new ArrayList<>();

        for(int i = 0; i < arr.length; i++) {
            int first = arr[i][0];
            int second = arr[i][1];

            if(numberPairs.get(second) != null && numberPairs.get(second) == first) {
                List<Integer> temp = new ArrayList<>();
                temp.add(first);
                temp.add(second);
                result.add(temp);
            } else {
                numberPairs.put(first, second);
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        int arr[][] = new int[5][2];
        arr[0][0] = 1; arr[0][1] = 2;
        arr[1][0] = 3; arr[1][1] = 4;
        arr[2][0] = 5; arr[2][1] = 6;
        arr[3][0] = 2; arr[3][1] = 1;
        arr[4][0] = 4; arr[4][1] = 3;

        System.out.println("Symmetric pairs");
        List<List<Integer>> result = findSymmetricPairs(arr);
        for(List<Integer> i : result) {
            System.out.println(i.get(0) + " " + i.get(1));
        }
    }
}
