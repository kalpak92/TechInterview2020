package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * You are given an array of variable pairs equations and an array of real numbers values,
 * where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i].
 *
 * Each Ai or Bi is a string that represents a single variable.
 *
 * You are also given some queries,
 * where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
 *
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 *
 * Note: The input is always valid.
 * You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
 *
 *
 *
 * Example 1:
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 *
 *
 * Example 2:
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 *
 *
 * Example 3:
 * Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * Output: [0.50000,2.00000,-1.00000,-1.00000]
 *
 *
 * Constraints:
 *
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 */

public class EvaluateDivision {
    static class Node {
        String key;
        double value;

        public Node(String key, double value) {
            this.key = key;
            this.value = value;
        }
    }

    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Node>> graph = new HashMap<>();
        double[] result = new double[queries.size()];

        buildGraph(graph, equations, values);

        for(int i = 0; i < queries.size(); i++) {
            result[i] = queryDFS(graph, queries.get(i).get(0), queries.get(i).get(1), new HashSet<>());
        }
        return result;
    }

    private static double queryDFS(Map<String, List<Node>> graph, String source, String destination, Set<String> isVisited) {
        // Check if either source or destination is present in the graph or not.
        // If not, return 0;
        if(!graph.containsKey(source) || !graph.containsKey(destination))
            return -1.0;

        // Check if source is equal to destination.
        // Return 1.0
        if(source.equals(destination))
            return 1.0;

        // Mark source as visited
        isVisited.add(source);

        for(Node node : graph.get(source)) {
            if(!isVisited.contains(node.key)) {
                double answer = queryDFS(graph, node.key, destination, isVisited);

                if(answer != -1.0)
                    return answer*node.value;
            }
        }
        return -1.0;
    }

    private static void buildGraph(Map<String, List<Node>> graph, List<List<String>> equations, double[] values) {
        for(int i = 0; i < values.length; i++) {
            String from = equations.get(i).get(0);
            String to = equations.get(i).get(1);

            graph.putIfAbsent(from, new ArrayList<>());
            graph.putIfAbsent(to, new ArrayList<>());

            graph.get(from).add(new Node(to, values[i]));
            graph.get(to).add(new Node(from, 1/values[i])); // Reciprocal for the reverse direction
        }
    }

    public static void main(String[] args) {
        String[][] eq = new String[][]{{"a","b"},{"b","c"}};
        double[] values = new double[]{2.0, 3.0};
        String[][] qr = new String[][]{{"a","c"},{"b","a"},{"a","e"},{"a","a"},{"x","x"}};

        List<List<String>> equations = new ArrayList<>();
        for(String[] equation : eq)
            equations.add(Arrays.asList(equation));

        List<List<String>> queries = new ArrayList<>();
        for(String[] q : qr) {
            queries.add(Arrays.asList(q));
        }

        System.out.println(Arrays.toString(calcEquation(equations, values, queries)));

    }
}
