package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kalpak
 *
 * DFS Traversal on a Graph - Recursive
 */
public class DepthFirstSearchRecursive {
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

    public static void dfsRecursive(int start, boolean[] isVisited, Map<Integer, List<Edge>> graph) {
        if(isVisited[start])
            return;

        isVisited[start] = true;
        System.out.print(start + " ");

        List<Edge> adjacencyList = graph.get(start);
        if(adjacencyList != null) {
            for(Edge edge : adjacencyList) {
                dfsRecursive(edge.to, isVisited, graph);
            }
        }
        return;
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

        dfsRecursive(0, new boolean[numNodes], graph);
    }
}
