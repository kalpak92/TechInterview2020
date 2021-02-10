package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to find the number of connected components in an undirected graph.
 *
 * Example 1:
 * Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
 *
 *      0          3
 *      |          |
 *      1 --- 2    4
 *
 * Output: 2
 *
 *
 * Example 2:
 * Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
 *
 *      0           4
 *      |           |
 *      1 --- 2 --- 3
 *
 * Output:  1
 * Note:
 * You can assume that no duplicate edges will appear in edges.
 * Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */

public class ConnectedComponentsUndirectedGraph {
    public static int countComponents(int n, int[][] edges) {
        if(n <= 1)
            return n;

        Map<Integer, List<Integer>> graph = new HashMap<>();
        // Initialize the graph
        for(int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        // Put the edges into the graph.
        // Put the edges both ways since the graph is undirected
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        Set<Integer> visited = new HashSet<>();
        int numberOfComponents = 0;

        for(int i = 0; i < n; i++) {
            if(!visited.contains(i)) {
                dfsRecursive(i, visited, graph);
                numberOfComponents++;
            }
        }
        return numberOfComponents;
    }

    private static void dfsRecursive(int startNode, Set<Integer> visited, Map<Integer, List<Integer>> graph) {
        visited.add(startNode);

        List<Integer> adjacencyList = graph.get(startNode);

        if(adjacencyList != null) {
            for(int edge : adjacencyList) {
                if(!visited.contains(edge))
                    dfsRecursive(edge, visited, graph);
            }
        }
        return;
    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{{0, 1}, {1, 2}, {3, 4}};
        System.out.println("The number of connected components are : " + countComponents(5, edges));
    }
}
