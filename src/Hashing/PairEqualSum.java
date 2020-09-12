package Hashing;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kalpak
 * Given an array of distinct integers, find if there are two pairs (a, b) and (c, d) such that a+b = c+d, and a, b, c and d are distinct elements.
 * If there are multiple answers, then print any of them.
 *
 * Example:
 *
 * Input:   {3, 4, 7, 1, 2, 9, 8}
 * Output:  (3, 8) and (4, 7)
 * Explanation: 3+8 = 4+7
 *
 * Input:   {3, 4, 7, 1, 12, 9};
 * Output:  (4, 12) and (7, 9)
 * Explanation: 4+12 = 7+9
 *
 * Input:  {65, 30, 7, 90, 1, 9, 8};
 * Output:  No pairs found
 */
public class PairEqualSum {
    public static void findPairWithEqualSum(int[] arr) {
        Map<Integer, Integer[]> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i != j) {
                    Integer sum = arr[i] + arr[j];
                    if(map.get(sum) != null) {
                        Integer[] a= map.get(sum);

                        if((arr[i]^arr[j]^a[0]^a[1]) != 0) {    // check that the pair should not be the same
                            System.out.println(map.get(sum)[0]+" "+ map.get(sum)[1]);
                            System.out.println(arr[i]+" "+arr[j]);
                            return;
                        }
                    } else {
                        map.put(sum, new Integer[]{arr[i], arr[j]});
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        findPairWithEqualSum(new int[]{3, 4, 7, 1, 2, 9, 8});
    }
}

