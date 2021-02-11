package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
 *
 * Example 1:
 *
 * Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
 * Output: true
 * Example 2:
 *
 * Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
 * Output: false
 * Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.
 *
 */

public class GraphValidTree {
    public static boolean validTree(int n, int[][] edges) {
        // Check if the number of edges equals to number of nodes - 1
        if(edges.length != n - 1)
            return false;

        // Now Check for the number of connected components in the graph
        // Number of Connected Components should be 1
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        int numberOfComponents = 0;

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

        for(int i = 0; i < n; i++) {
            if(!visited.contains(i)) {
                dfsRecursive(i, visited, graph);
                numberOfComponents++;
            }
        }

        if(numberOfComponents > 1)
            return false;
        return true;
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
        int[][] edges = new int[][]{{0,1}, {1,2}, {2,3}, {1,3}, {1,4}};
        System.out.println(validTree(5, edges));
    }
}
