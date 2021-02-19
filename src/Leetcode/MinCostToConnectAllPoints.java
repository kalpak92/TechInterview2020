package Leetcode;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author kalpak
 *
 * You are given an array points representing integer coordinates of some points on a 2D-plane,
 * where points[i] = [xi, yi].
 *
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them:
 * |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
 *
 * Return the minimum cost to make all points connected.
 * All points are connected if there is exactly one simple path between any two points.
 *
 *
 * Example 1:
 * Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * Output: 20
 *
 * Explanation:
 * We can connect the points as shown above to get the minimum cost of 20.
 * Notice that there is a unique path between every pair of points.
 *
 * Example 2:
 * Input: points = [[3,12],[-2,5],[-4,1]]
 * Output: 18
 *
 * Example 3:
 * Input: points = [[0,0],[1,1],[1,0],[-1,1]]
 * Output: 4
 *
 * Example 4:
 * Input: points = [[-1000000,-1000000],[1000000,1000000]]
 * Output: 4000000
 *
 * Example 5:
 * Input: points = [[0,0]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= points.length <= 1000
 * -10^6 <= xi, yi <= 10^6
 * All pairs (xi, yi) are distinct.
 *
 */



public class MinCostToConnectAllPoints {
    // Pair class
    static class Pair<U, V> {
        public final U first;       // first field of a Pair
        public final V second;      // second field of a Pair

        // Constructs a new Pair with specified values
        private Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }

        @Override
        // Checks specified object is "equal to" current object or not
        public boolean equals(Object o) {
            if (this == o)
                return true;

            if (o == null || getClass() != o.getClass())
                return false;

            Pair<?, ?> pair = (Pair<?, ?>) o;

            // call equals() method of the underlying objects
            if (!first.equals(pair.first))
                return false;
            return second.equals(pair.second);
        }

        @Override
        // Computes hash code for an object to support hash tables
        public int hashCode() {
            // use hash codes of the underlying objects
            return 31 * first.hashCode() + second.hashCode();
        }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }

        public U getKey() {
            return this.first;
        }

        public V getValue() {
            return this.second;
        }

        // Factory method for creating a Typed Pair immutable instance
        public static <U, V> Pair <U, V> of(U a, V b) {
            // calls private constructor
            return new Pair<>(a, b);
        }
    };

    static Set<Integer> visited;
    static PriorityQueue<Pair<Integer,Integer>> priorityQueue;      // <index, distance>

    public static int minCostConnectPoints(int[][] points) {
        if (points.length <= 1)
            return 0;

        visited = new HashSet<>();
        priorityQueue = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        // initialized
        enQueue(points,0);
        visited.add(0);

        // cost -> result
        int cost = 0;

        while(visited.size() != points.length) {
            Pair<Integer,Integer> newPoint =  priorityQueue.poll();

            if (visited.contains(newPoint.getKey()))
                continue;

            // add the new point with shortest edge into the tree
            visited.add(newPoint.getKey());

            // update the cost
            cost += newPoint.getValue();
            enQueue(points, newPoint.getKey());
        }
        return cost;
    }

    private static void enQueue(int[][] points, int idx){
        for (int i = 0; i < points.length; i++){
            if (visited.contains(i) || i == idx)
                continue;

            priorityQueue.add(new Pair<>(i, getDistance(idx, i, points)));
        }
    }

    // calculate the Manhattan Distance
    private static int getDistance(int idx, int i, int[][] points) {
        return Math.abs(points[idx][0] - points[i][0]) + Math.abs(points[idx][1] - points[i][1]);
    }

    public static void main(String[] args) {

    }
}
