package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1, and return them in any order.
 *
 * The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
 *
 * Example 1:
 * Input: graph = [[1,2],[3],[3],[]]
 * Output: [[0,1,3],[0,2,3]]
 * Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 *
 *
 * Example 2:
 * Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
 * Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 *
 *
 * Example 3:
 * Input: graph = [[1],[]]
 * Output: [[0,1]]
 *
 *
 * Example 4:
 * Input: graph = [[1,2,3],[2],[3],[]]
 * Output: [[0,1,2,3],[0,2,3],[0,3]]
 *
 *
 * Example 5:
 * Input: graph = [[1,3],[2],[3],[]]
 * Output: [[0,1,2,3],[0,3]]
 *
 *
 * Constraints:
 *
 * n == graph.length
 * 2 <= n <= 15
 * 0 <= graph[i][j] < n
 * graph[i][j] != i (i.e., there will be no self-loops).
 * The input graph is guaranteed to be a DAG.
 *
 */

public class AllPathsInDAG {
    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        path.add(0);

        backtrack(result, path, graph, 0);
        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> path, int[][] graph, int currentIdx) {
        if(currentIdx == graph.length - 1) {
            result.add(new ArrayList<>(path));
            return;
        }

        for(int nextNode : graph[currentIdx]) {
            path.add(nextNode);
            backtrack(result, path, graph, nextNode);
            path.remove(path.size() - 1);
        }
    }

    // Time Complexity: O(2^n)
    public static List<List<Integer>> allPathsSourceTargetBFS(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<List<Integer>> queue = new LinkedList<>();

        // Add the source to the queue.
        queue.offer(Arrays.asList(0));
        int destination = graph.length - 1;     // Given

        // Perform BFS
        while(!queue.isEmpty()) {
            List<Integer> currentPath = queue.poll();
            int currentNode = currentPath.get(currentPath.size() - 1); // Get the last node in the current path

            // Check if currentNode == destination
            if(currentNode == destination) {
                result.add(new ArrayList(currentPath));
            }

            // Check for the neighbors of the currentNode
            for(int neighbor : graph[currentNode]) {
                // Create a new path and add the neighbor
                List<Integer> newPath = new ArrayList<>(currentPath);
                newPath.add(neighbor);
                queue.offer(newPath);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{{4,3,1},{3,2,4},{3},{4},{}};
        System.out.println(allPathsSourceTarget(graph));
    }
}
