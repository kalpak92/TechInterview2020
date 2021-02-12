package Graph;

import java.util.*;

public class BreadthFirstSearchIterative {
    // Helper method to setup graph
    private static void addUndirectedEdge(List<List<Integer>> graph, int from, int to) {
        graph.get(from).add(to);
        graph.get(to).add(from);
    }

    public static void bfsIterative(List<List<Integer>> graph, Deque<Integer> queue, boolean[] isVisited, int[] distance) {
        while(!queue.isEmpty()) {
            int currentNode = queue.poll();

            // This node is already visited.
            if (isVisited[currentNode])
                continue;

            // Visit this node.
            isVisited[currentNode] = true;

            // Add all neighbors to queue.
            List<Integer> neighbors = graph.get(currentNode);

            if (neighbors != null) {
                for (int next : neighbors) {
                    if (!isVisited[next]) {
                        queue.add(next);
                        distance[next] = distance[currentNode] + 1;
                    }
                }
            }
        }

        System.out.println(Arrays.toString(distance));
    }

    public static void main(String[] args) {
        int n = 14;
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        addUndirectedEdge(graph, 0, 1);
        addUndirectedEdge(graph, 0, 2);
        addUndirectedEdge(graph, 0, 3);
        addUndirectedEdge(graph, 2, 9);
        addUndirectedEdge(graph, 8, 2);
        addUndirectedEdge(graph, 3, 4);
        addUndirectedEdge(graph, 10, 11);
        addUndirectedEdge(graph, 12, 13);
        addUndirectedEdge(graph, 3, 5);
        addUndirectedEdge(graph, 5, 7);
        addUndirectedEdge(graph, 5, 6);
        addUndirectedEdge(graph, 0, 10);
        addUndirectedEdge(graph, 11, 12);

        Deque<Integer> queue = new LinkedList<>();
        queue.offer(0);

        int[] distance = new int[n];
        distance[0] = 1;

        bfsIterative(graph, queue, new boolean[n], distance);
    }
}
