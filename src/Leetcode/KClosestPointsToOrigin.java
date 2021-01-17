package Leetcode;

import java.util.PriorityQueue;

/**
 * @author kalpak
 *
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 *
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 *
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 *
 *
 *
 * Example 1:
 *
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 * Example 2:
 *
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 *
 *
 * Note:
 *
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 */


public class KClosestPointsToOrigin {
    public static int[][] kClosest(int[][] points, int K) {
        int[][] result = new int[K][2];
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> (b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1]));

        for(int[] p : points) {
            maxHeap.offer(p);

            if(maxHeap.size() > K)
                maxHeap.poll();
        }

        while (!maxHeap.isEmpty()) {
            result[K-1] = maxHeap.poll();
            K--;
        }

        return result;
    }

    public static void printArray(int[][] nums) {
        for(int[] i : nums)
            System.out.print(i[0] + " " + i[1] + ", ");
        System.out.println();

    }

    public static void main(String[] args) {
        int[][] points = new int[][]{{1,3},{-2,2}};
        printArray(kClosest(points, 1));

        points = new int[][]{{3,3},{5,-1},{-2,4}};
        printArray(kClosest(points, 2));
    }
}
