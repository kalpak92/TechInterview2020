package Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kalpak
 *
 * Detect cycle in an Undirected Graph
 *
 */

public class CycleInUndirectedGraph {
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

    public static boolean hasCycleDFS(List<List<Integer>> graph, int startNode, boolean[] visited, int parent) {

        // mark the current node as discovered
        visited[startNode] = true;

        // do for every edge `v —> w`
        List<Integer> adjacencyList = graph.get(startNode);
        for (int node: adjacencyList) {
            // if `node` is not discovered
            if (!visited[node]) {
                if (hasCycleDFS(graph, node, visited, startNode)) {
                    return true;
                }
            } else if (node != parent) {  // if `w` is discovered, and `w` is not a parent
                // we found a back-edge (cycle)
                return true;
            }
        }
        // No back-edges were found in the graph
        return false;
    }

    public static void main(String[] args) {
        List<List<Integer>> graph = createGraph(6);

        addUndirectedEdge(graph, 0, 1);
        addUndirectedEdge(graph, 1, 2);
        addUndirectedEdge(graph, 0, 3);
        addUndirectedEdge(graph, 3, 4);
        addUndirectedEdge(graph, 4, 5);
        addUndirectedEdge(graph, 5, 1);

        System.out.println(hasCycleDFS(graph, 0, new boolean[6], -1));
    }
}
