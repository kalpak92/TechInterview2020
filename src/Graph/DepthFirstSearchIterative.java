package Graph;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;

/**
 * @author kalpak
 *
 * DFS Traversal on a Graph - Iterative
 *
 */

public class DepthFirstSearchIterative {
    static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    // Helper method to setup graph
    private static void addDirectedEdge(Map<Integer, List<Edge>> graph, int from, int to, int cost) {
        List<Edge> list = graph.get(from);
        if (list == null) {
            list = new ArrayList<Edge>();
            graph.put(from, list);
        }
        list.add(new Edge(from, to, cost));
    }

    public static void dfsIterative(int start, boolean[] isVisited, Map<Integer, List<Edge>> graph) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);

        while(!stack.isEmpty()) {
            int currentNode = stack.pop();
            if (!isVisited[currentNode]) {
                isVisited[currentNode] = true;
                System.out.print(currentNode + " ");

                List<Edge> edges = graph.get(currentNode);
                if (edges != null) {
                    for (Edge edge : edges) {
                        if (!isVisited[edge.to]) {
                            stack.push(edge.to);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int numNodes = 5;
        Map<Integer, List<Edge>> graph = new HashMap<>();

        addDirectedEdge(graph, 0, 1, 4);
        addDirectedEdge(graph, 0, 2, 5);
        addDirectedEdge(graph, 1, 2, -2);
        addDirectedEdge(graph, 1, 3, 6);
        addDirectedEdge(graph, 2, 3, 1);
        addDirectedEdge(graph, 2, 2, 10); // Self loop

        dfsIterative(0, new boolean[numNodes], graph);
    }
}
