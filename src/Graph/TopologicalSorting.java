package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopologicalSorting {
    // Helper Edge class to describe edges in the graph
    static class Edge {
        int from, to, weight;

        public Edge(int f, int t, int w) {
            from = f;
            to = t;
            weight = w;
        }
    }

    private static int dfs(int orderingIdx, int startNode, boolean[] visited,
                           int[] ordering, Map<Integer, List<Edge>> graph) {

        visited[startNode] = true;

        List<Edge> edges = graph.get(startNode);

        if (edges != null)
            for (Edge edge : edges)
                if (!visited[edge.to])
                    orderingIdx = dfs(orderingIdx, edge.to, visited, ordering, graph);

        ordering[orderingIdx] = startNode;
        return orderingIdx - 1;
    }

    public static int[] topologicalSort(Map<Integer, List<Edge>> graph, int numNodes) {

        int[] ordering = new int[numNodes];
        boolean[] visited = new boolean[numNodes];

        int orderingIdx = numNodes - 1; // since DFS, so we start adding the nodes from the leaf

        for (int i = 0; i < numNodes; i++)
            if (!visited[i])
                orderingIdx = dfs(orderingIdx, i, visited, ordering, graph);

        return ordering;
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

        int[] ordering = topologicalSort(graph, N);

        // // Prints: [6, 0, 5, 1, 2, 3, 4]
        System.out.println(java.util.Arrays.toString(ordering));
    }
}
