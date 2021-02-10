package Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Count the number of connected components in an Undirected Graph.
 *
 */

public class ConnectedComponentsDFS {
    public static List<List<Integer>> createGraph(int numNodes) {
        List<List<Integer>> graphAdjList = new ArrayList<>(numNodes);
        for (int i = 0; i < numNodes; i++)
            graphAdjList.add(new ArrayList<>());

        return graphAdjList;
    }

    public static void addUndirectedEdge(List<List<Integer>> graph, int from, int to) {
        graph.get(from).add(to);
        graph.get(to).add(from);
    }

    public static int getNumberOfConnectedComponents(List<List<Integer>> graph, int numNodes) {
        int countComponents = 0;
        boolean[] isVisited = new boolean[numNodes];

        for (int i = 0; i < numNodes; i++) {
            if(!isVisited[i]) {
                dfsRecursive(i, isVisited, graph);
                countComponents++;
            }
        }
        return countComponents;
    }

    private static void dfsRecursive(int start, boolean[] isVisited, List<List<Integer>> graph) {
        isVisited[start] = true;
        // System.out.print(start + " ");

        List<Integer> adjacencyList = graph.get(start);
        if(adjacencyList != null) {
            for(int edge : adjacencyList) {
                if(!isVisited[edge])
                    dfsRecursive(edge, isVisited, graph);
            }
        }
        return;
    }

    public static void main(String[] args) {
        final int numNodes = 11;
        List<List<Integer>> graph = createGraph(numNodes);

        // Setup a graph with five connected components:
        // {0,1,7}, {2,5}, {4,8}, {3,6,9}, {10}
        addUndirectedEdge(graph, 0, 1);
        addUndirectedEdge(graph, 1, 7);
        addUndirectedEdge(graph, 7, 0);
        addUndirectedEdge(graph, 2, 5);
        addUndirectedEdge(graph, 4, 8);
        addUndirectedEdge(graph, 3, 6);
        addUndirectedEdge(graph, 6, 9);

        System.out.println("The number of connected components in the given graph is " +
                getNumberOfConnectedComponents(graph, numNodes));
    }
}
