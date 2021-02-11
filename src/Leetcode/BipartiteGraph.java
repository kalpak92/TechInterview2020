package Leetcode;

/**
 * @author kalpak
 *
 * Given an undirected graph, return true if and only if it is bipartite.
 * Recall that a graph is bipartite if we can split its set of nodes into two independent subsets A and B, such that every edge in the graph has one node in A and another node in B.
 *
 * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.
 * Each node is an integer between 0 and graph.length - 1. There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.
 *
 *
 * Example 1:
 * Input: graph = [[1,3],[0,2],[1,3],[0,2]]
 * Output: true
 * Explanation: We can divide the vertices into two groups: {0, 2} and {1, 3}.
 *
 * Example 2:
 * Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
 * Output: false
 * Explanation: We cannot find a way to divide the set of nodes into two independent subsets.
 *
 * Constraints:
 *
 * 1 <= graph.length <= 100
 * 0 <= graph[i].length < 100
 * 0 <= graph[i][j] <= graph.length - 1
 * graph[i][j] != i
 * All the values of graph[i] are unique.
 * The graph is guaranteed to be undirected.
 *
 */

public class BipartiteGraph {
    public static boolean isBipartite(int[][] graph) {
        // Merge the visited and color array and maintain the states
        // 0: Haven't been colored yet.
        // 1: Blue.
        // -1: Red.
        int[] vertexColors = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            //This graph might be a disconnected graph. So check each unvisited node.
            if (vertexColors[i] == 0 && !validColor(graph, vertexColors, 1, i)) {
                return false;
            }
        }
        return true;
    }

    private static boolean validColor(int[][] graph, int[] vertexColors, int color, int startNode) {
        if (vertexColors[startNode] != 0) {
            return vertexColors[startNode] == color;
        }

        vertexColors[startNode] = color;
        for (int next : graph[startNode]) {
            if (!validColor(graph, vertexColors, -color, next)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{{1,3},{0,2},{1,3},{0,2}};
        System.out.println("Is the graph Bipartite ?? " + isBipartite(graph));
    }
}
