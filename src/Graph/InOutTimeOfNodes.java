package Graph;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author kalpak
 *
 * Given a graph, find the arrival and departure time of its vertices.
 */

public class InOutTimeOfNodes {
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

    public static int computeInOutTimeDFS(List<List<Integer>> graph, int startNode, boolean[] visited, int[] arrival, int[] departure, int time) {

        // set the arrival time of vertex `v`
        arrival[startNode] = ++time;

        // mark vertex as discovered
        visited[startNode] = true;

        List<Integer> adjacencyList = graph.get(startNode);

        for (int i: adjacencyList) {
            if (!visited[i]) {
                time = computeInOutTimeDFS(graph, i, visited, arrival, departure, time);
            }
        }

        // set departure time of vertex `v`
        departure[startNode] = ++time;
        return time;
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

        int[] arrival = new int[numNodes];
        int[] departure = new int[numNodes];

        computeInOutTimeDFS(graph, 0, new boolean[numNodes], arrival, departure, 0);

        System.out.println(Arrays.toString(arrival));
        System.out.println(Arrays.toString(departure));
    }
}
