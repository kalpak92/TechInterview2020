package Leetcode;

import java.util.Arrays;

/**
 * @author kalpak
 *
 * Given a rectangular cake with height h and width w, and two arrays of integers horizontalCuts and verticalCuts
 * where horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly,
 * verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
 *
 * Return the maximum area of a piece of cake after you cut at each horizontal and vertical position
 * provided in the arrays horizontalCuts and verticalCuts.
 * Since the answer can be a huge number, return this modulo 10^9 + 7.
 *
 *
 * Example 1:
 * Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
 * Output: 4
 * Explanation: The figure above represents the given rectangular cake.
 * Red lines are the horizontal and vertical cuts. After you cut the cake, the green piece of cake has the maximum area.
 *
 * Example 2:
 * Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
 * Output: 6
 * Explanation: The figure above represents the given rectangular cake.
 * Red lines are the horizontal and vertical cuts.
 * After you cut the cake, the green and yellow pieces of cake have the maximum area.
 *
 * Example 3:
 * Input: h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
 * Output: 9
 *
 *
 * Constraints:
 *
 * 2 <= h, w <= 10^9
 * 1 <= horizontalCuts.length < min(h, 10^5)
 * 1 <= verticalCuts.length < min(w, 10^5)
 * 1 <= horizontalCuts[i] < h
 * 1 <= verticalCuts[i] < w
 * It is guaranteed that all elements in horizontalCuts are distinct.
 * It is guaranteed that all elements in verticalCuts are distinct.
 *
 */

public class MaxAreaOfPieceOfCake {
    public static int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        int maxH = 0;
        int maxV = 0;

        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);

        for (int i = 0; i < horizontalCuts.length; i++) {
            maxH = Math.max(maxH, i == 0 ? horizontalCuts[i] : horizontalCuts[i] - horizontalCuts[i - 1]);
        }
        maxH = Math.max(maxH, h - horizontalCuts[horizontalCuts.length - 1]);

        for (int i = 0; i < verticalCuts.length; i++) {
            maxV = Math.max(maxV, i == 0 ? verticalCuts[i] : verticalCuts[i] - verticalCuts[i - 1]);
        }
        maxV = Math.max(maxV, w - verticalCuts[verticalCuts.length - 1]);

        return (int)(maxH % (1e9 + 7) * maxV % (1e9 + 7));
    }

    public static void main(String[] args) {
        System.out.println("The maximum area of a piece of cake after the cuts are : " +
                maxArea(5, 4, new int[]{1, 2, 4}, new int[]{1, 3}));
    }
}
