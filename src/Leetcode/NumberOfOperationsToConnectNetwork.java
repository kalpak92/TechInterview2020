package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * There are n computers numbered from 0 to n-1 connected by ethernet cables connections
 * forming a network where connections[i] = [a, b] represents a connection between computers a and b.
 *
 * Any computer can reach any other computer directly or indirectly through the network.
 *
 * Given an initial computer network connections. You can extract certain cables between two directly connected computers,
 * and place them between any pair of disconnected computers to make them directly connected.
 *
 * Return the minimum number of times you need to do this in order to make all the computers connected.
 * If it's not possible, return -1.
 * Example 1:
 * Input: n = 4, connections = [[0,1],[0,2],[1,2]]
 * Output: 1
 * Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.
 *
 * Example 2:
 * Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
 * Output: 2
 *
 * Example 3:
 * Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
 * Output: -1
 * Explanation: There are not enough cables.
 *
 *
 * Example 4:
 * Input: n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 * 1 <= connections.length <= min(n*(n-1)/2, 10^5)
 * connections[i].length == 2
 * 0 <= connections[i][0], connections[i][1] < n
 * connections[i][0] != connections[i][1]
 * There are no repeated connections.
 * No two computers are connected by more than one cable.
 *
 */

public class NumberOfOperationsToConnectNetwork {
    public static int makeConnected(int n, int[][] connections) {
        if(connections.length < n - 1)
            return -1;

        Map<Integer, List<Integer>> graph = new HashMap<>();
        // Initialize the graph
        for(int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        // Put the edges into the graph.
        // Put the edges both ways since the graph is undirected
        for(int[] edge : connections) {
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
        return numberOfComponents - 1;
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
        int[][] connections = new int[][]{{0, 1}, {0, 2}, {1, 2}};
        System.out.println(makeConnected(4, connections));
    }
}
