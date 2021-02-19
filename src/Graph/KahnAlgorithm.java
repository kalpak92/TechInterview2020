package Graph;

import java.util.*;

/**
 * @author kalpak
 *
 * Implement Kahn's Algorithm
 *
 */

public class KahnAlgorithm {
    // Helper Edge class to describe edges in the graph
    static class Edge {
        int from, to, weight;

        public Edge(int f, int t, int w) {
            from = f;
            to = t;
            weight = w;
        }
    };

    public static List<Integer> kahnsTopologicalOrder(Map<Integer, List<Edge>> graph) {
        // Calculate the in-degree of each node.
        int[] inDegree = new int[graph.size()];
        for (Integer edges : graph.keySet()) {
            for (Edge edge : graph.get(edges)) {
                inDegree[edge.to]++;
            }
        }

        // queue always contains the set nodes with no incoming edges.
        Queue<Integer> queue = new LinkedList<>();

        // Find all start nodes.
        for (int i = 0; i < inDegree.length; i++)
            if (inDegree[i] == 0)
                queue.offer(i);

        List<Integer> topologicalOrder = new ArrayList<>();

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            topologicalOrder.add(currentNode);
            for (Edge edge : graph.get(currentNode)) {
                inDegree[edge.to]--;
                if (inDegree[edge.to] == 0) {
                    queue.offer(edge.to);
                }
            }
        }

        if (topologicalOrder.size() != graph.size()) {
            throw new IllegalArgumentException("Graph is not acyclic! Detected a cycle.");
        }
        return topologicalOrder;
    }
    public static void main(String[] args) {
        // Graph setup
        final int N = 7;
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < N; i++)
            graph.put(i, new ArrayList<>());

        graph.get(0).add(new Edge(0, 1, 3));
        graph.get(0).add(new Edge(0, 2, 2));
        graph.get(0).add(new Edge(0, 5, 3));
        graph.get(1).add(new Edge(1, 3, 1));
        graph.get(1).add(new Edge(1, 2, 6));
        graph.get(2).add(new Edge(2, 3, 1));
        graph.get(2).add(new Edge(2, 4, 10));
        graph.get(3).add(new Edge(3, 4, 5));
        graph.get(5).add(new Edge(5, 4, 7));

        System.out.println(kahnsTopologicalOrder(graph));
    }
}
