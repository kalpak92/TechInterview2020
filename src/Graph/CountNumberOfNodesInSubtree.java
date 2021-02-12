package Graph;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author kalpak
 *
 *
 */
public class CountNumberOfNodesInSubtree {
    public static List<List<Integer>> createGraph(int numNodes) {
        List<List<Integer>> graphAdjList = new ArrayList<>();
        for (int i = 0; i < numNodes; i++)
            graphAdjList.add(new ArrayList<>());

        return graphAdjList;
    }

    public static void addUndirectedEdge(List<List<Integer>> graph, int from, int to) {
        graph.get(from).add(to);
        graph.get(to).add(from);
    }

    public static void getNumberOfNodesInSubtree(List<List<Integer>> graph, int numNodes) {
        int[] count = new int[numNodes];
        boolean[] isVisited = new boolean[numNodes];

        dfsCountNodesInSubtreeRecursive(graph, isVisited, count, 1);

        System.out.println(Arrays.toString(count));
    }

    private static int dfsCountNodesInSubtreeRecursive(List<List<Integer>> graph, boolean[] isVisited, int[] count, int startNode) {
        isVisited[startNode] = true;
        int current_size = 1;

        List<Integer> adjacencyList = graph.get(startNode);

        for(int child : adjacencyList) {
            if(!isVisited[child]) {
                current_size += dfsCountNodesInSubtreeRecursive(graph, isVisited, count, child);
            }
        }
        count[startNode] = current_size;
        return current_size;
    }

    public static void main(String[] args) {
        int numNodes = 8;
        List<List<Integer>> graph = createGraph(numNodes);

        addUndirectedEdge(graph, 0, 1);
        addUndirectedEdge(graph, 1, 2);
        addUndirectedEdge(graph, 2, 3);
        addUndirectedEdge(graph, 2, 4);
        addUndirectedEdge(graph, 3, 7);
        addUndirectedEdge(graph, 4, 5);
        addUndirectedEdge(graph, 4, 6);

        getNumberOfNodesInSubtree(graph, numNodes);
    }
}
