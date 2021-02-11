package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
 * Each person may dislike some other people, and they should not go into the same group.
 *
 * Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
 * Return true if and only if it is possible to split everyone into two groups in this way.
 *
 *
 * Example 1:
 * Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
 * Output: true
 * Explanation: group1 [1,4], group2 [2,3]
 *
 *
 * Example 2:
 * Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
 * Output: false
 *
 *
 * Example 3:
 * Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= N <= 2000
 * 0 <= dislikes.length <= 10000
 * dislikes[i].length == 2
 * 1 <= dislikes[i][j] <= N
 * dislikes[i][0] < dislikes[i][1]
 * There does not exist i != j for which dislikes[i] == dislikes[j].
 */

public class PossibleBipartition {
    public static boolean possibleBipartition(int N, int[][] dislikes) {
        int[] vertexColors = new int[N + 1];
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] edge : dislikes) {
            graph.computeIfAbsent(edge[0], l -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], l -> new ArrayList<>()).add(edge[0]);
        }

        for (int i = 1; i <= N; i++) {
            if (vertexColors[i] == 0 && !dfsPossibleBipartition(graph, vertexColors, 1, i)) {
                return false;
            }
        }
        return true;
    }

    private static boolean dfsPossibleBipartition(Map<Integer, List<Integer>> graph, int[] vertexColors, int color, int startNode) {
        if (vertexColors[startNode] != 0) {
            return vertexColors[startNode] == color;
        }

        vertexColors[startNode] = color;

        for (int next : graph.getOrDefault(startNode, Collections.emptyList())) {
            if (!dfsPossibleBipartition(graph, vertexColors, -color, next)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] dislikes = new int[][]{{1,2},{1,3},{2,4}};
        System.out.println(possibleBipartition(4, dislikes));
        dislikes = new int[][]{{1,2},{2,3},{3,4},{4,5},{1,5}};
        System.out.println(possibleBipartition(5, dislikes));
    }
}
