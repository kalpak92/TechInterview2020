package Leetcode;

import java.util.Arrays;

/**
 * @author kalpak
 *
 * There are N cities numbered from 1 to N.
 *
 * You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.
 * (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)
 *
 * Return the minimum cost so that for every pair of cities,
 * there exists a path of connections (possibly of length 1) that connects those two cities together.
 *
 * The cost is the sum of the connection costs used.
 * If the task is impossible, return -1.
 *
 *
 * Example 1:
 * Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
 * Output: 6
 * Explanation:
 * Choosing any 2 edges will connect all cities so we choose the minimum 2.
 *
 *
 * Example 2:
 * Input: N = 4, connections = [[1,2,3],[3,4,4]]
 * Output: -1
 * Explanation:
 * There is no way to connect all cities even if all edges are used.
 *
 *
 * Note:
 *
 * 1 <= N <= 10000
 * 1 <= connections.length <= 10000
 * 1 <= connections[i][0], connections[i][1] <= N
 * 0 <= connections[i][2] <= 10^5
 * connections[i][0] != connections[i][1]
 */

public class ConnectingCitiesWithMinimumCost {
    static private int[] id;
    static private int[] size;
    static private int numberOfElements;

    public static int minimumCost(int N, int[][] connections) {
        int result = 0;

        numberOfElements = N;
        id = new int[numberOfElements + 1];
        size = new int[numberOfElements + 1];

        for(int i = 0; i <= N; i++) {
            id[i] = i;      // self root
            size[i] = 1;
        }

        Arrays.sort(connections, (a, b) -> (a[2] - b[2]));

        for(int[] c : connections) {
            int city1 = c[0];
            int city2 = c[1];

            if(find(city1) != find(city2)) {
                result += c[2];
                union(city1, city2);
            }
        }

        return numberOfElements == 1 ? result : -1;
    }

    private static int find(int p) {
        // find the root of p
        int root = p;
        while(root != id[root])
            root = id[root];

        // Path compression
        while(p != root) {
            int next = id[p];
            id[p] = root;
            p = next;
        }

        return root;
    }

    private static void union(int p, int q) {
        int root1 = find(p);
        int root2 = find(q);

        if(root1 == root2)
            return;

        if(size[root1] < size[root2]) {
            size[root2] += size[root1];
            id[root1] = root2;
        } else {
            size[root1] += size[root2];
            id[root2] = root1;
        }

        numberOfElements--;
        return;
    }

    public static void main(String[] args) {
        int[][] connections = new int[][]{{1,2,5}, {1,3,6}, {2,3,1}};
        int numOfCities = 3;

        System.out.println(minimumCost(numOfCities, connections));
    }
}
