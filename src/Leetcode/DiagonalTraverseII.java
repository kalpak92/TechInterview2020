package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * Given a list of lists of integers, nums, return all elements of nums in diagonal order as shown in the below images.
 *
 *
 * Example 1:
 * Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,4,2,7,5,3,8,6,9]
 *
 *
 * Example 2:
 * Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
 * Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
 *
 *
 * Example 3:
 * Input: nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]]
 * Output: [1,4,2,5,3,8,6,9,7,10,11]
 *
 *
 * Example 4:
 * Input: nums = [[1,2,3,4,5,6]]
 * Output: [1,2,3,4,5,6]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i].length <= 10^5
 * 1 <= nums[i][j] <= 10^9
 * There at most 10^5 elements in nums.
 *
 */

public class DiagonalTraverseII {
    public static int[] findDiagonalOrder(List<List<Integer>> nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        int totalSize = 0;
        int[] result;

        for(int i = 0 ; i < nums.size(); i++) {
            totalSize += nums.get(i).size();
            for(int j = 0; j < nums.get(i).size(); j++) {
                int index = i + j;

                map.putIfAbsent(index, new ArrayList<>());
                map.get(index).add(nums.get(i).get(j));
            }
        }

        result = new int[totalSize];

        int itr = 0;
        for(int i = 0; i < map.size(); i++) {
            List<Integer> temp = map.get(i);

            for(int j = temp.size() - 1; j >= 0; j--) {
                result[itr++] = temp.get(j);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3,4,5},{6,7},{8},{9,10,11},{12,13,14,15,16}};
        List<List<Integer>> nums = new ArrayList<>();

        for (int [] row : matrix) {
            List<Integer> temp = new ArrayList<>();
            for(int i : row)
                temp.add(i);
            nums.add(temp);
        }

        System.out.println(Arrays.toString(findDiagonalOrder(nums)));
    }
}
