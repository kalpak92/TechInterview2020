package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections
 * forming a network where connections[i] = [a, b] represents a connection between servers a and b.
 *
 * Any server can reach any other server directly or indirectly through the network.
 *
 * A critical connection is a connection that, if removed, will make some server unable to reach some other server.
 *
 * Return all critical connections in the network in any order.
 *
 * Example 1:
 * Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output: [[1,3]]
 * Explanation: [[3,1]] is also accepted.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 * n-1 <= connections.length <= 10^5
 * connections[i][0] != connections[i][1]
 * There are no repeated connections.
 *
 */

public class CriticalConnectionsInANetwork {
    static int time = 0;
    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();

        buildGraph(graph, connections);

        int[] parents = new int[n];
        int[] discovery = new int[n];
        int[] low = new int[n];

        Arrays.fill(parents, -1);
        Arrays.fill(discovery, -1);
        Arrays.fill(low, -1);

        for(int i = 0; i < n; i++) {
            if(discovery[i] == -1) {
                doTraverseGraphDFS(i, parents, discovery, low, graph, result);
            }
        }

        return result;
    }

    private static void doTraverseGraphDFS(int source, int[] parents, int[] discovery, int[] low, Map<Integer, List<Integer>> graph, List<List<Integer>> result) {
        if(discovery[source] != -1)
            return;

        low[source] = discovery[source] = time;
        time++;

        for(int destination : graph.get(source)) {
            if(discovery[destination] == -1) {
                parents[destination] = source;
                doTraverseGraphDFS(destination, parents, discovery, low, graph, result);

                low[source] = Math.min(low[source], low[destination]);

                if(low[destination] > discovery[source])                    // Only 1 way possible
                    result.add(Arrays.asList(source, destination));

            } else if(parents[source] != destination) {                     // Check for back edge
                low[source] = Math.min(low[source], discovery[destination]);
            }
        }
    }

    private static void buildGraph(Map<Integer, List<Integer>> graph, List<List<Integer>> connections) {
        for(List<Integer> connection : connections) {
            int from = connection.get(0);
            int to = connection.get(1);

            graph.putIfAbsent(from, new ArrayList<>());
            graph.putIfAbsent(to, new ArrayList<>());

            graph.get(from).add(to);
            graph.get(to).add(from);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> connections = new ArrayList<>();
        connections.add(Arrays.asList(0, 1));
        connections.add(Arrays.asList(1, 2));
        connections.add(Arrays.asList(2, 0));
        connections.add(Arrays.asList(1, 3));

        System.out.println(criticalConnections(4, connections));
    }
}
