package Leetcode;

import java.util.Arrays;

/**
 * @author kalpak
 *
 * In this problem, a rooted tree is a directed graph such that,
 * there is exactly one node (the root) for which all other nodes are descendants of this node,
 * plus every node has exactly one parent, except for the root node which has no parents.
 *
 * The given input is a directed graph that started as a rooted tree with n nodes (with distinct values from 1 to n),
 * with one additional directed edge added. The added edge has two different vertices chosen from 1 to n,
 * and was not an edge that already existed.
 *
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [ui, vi]
 * that represents a directed edge connecting nodes ui and vi, where ui is a parent of child vi.
 *
 * Return an edge that can be removed so that the resulting graph is a rooted tree of n nodes.
 * If there are multiple answers, return the answer that occurs last in the given 2D-array.
 *
 *
 * Example 1:
 * Input: edges = [[1,2],[1,3],[2,3]]
 * Output: [2,3]
 *
 *
 * Example 2:
 * Input: edges = [[1,2],[2,3],[3,4],[4,1],[1,5]]
 * Output: [4,1]
 *
 *
 * Constraints:
 *
 * n == edges.length
 * 3 <= n <= 1000
 * edges[i].length == 2
 * 1 <= ui, vi <= n
 *
 */

public class RedundantConnectionII {
    public static int[] findRedundantDirectedConnection(int[][] edges) {
        int[] indegrees = new int[edges.length + 1];
        int hasTwoInDegrees = -1;

        for(int[] edge : edges) {
            indegrees[edge[1]]++;

            if(indegrees[edge[1]] == 2) {
                hasTwoInDegrees = edge[1];
                break;
            }
        }

        if(hasTwoInDegrees == -1)
            return detectCycles(edges.length + 1, edges, null);

        for(int i = edges.length - 1; i >= 0; i--) {
            if (edges[i][1] == hasTwoInDegrees) {
                if(detectCycles(edges.length + 1, edges, edges[i]) == null)
                    return edges[i];
            }
        }
        return new int[0];

    }

    private static int[] detectCycles(int n, int[][] edges, int[] skipEdges) {
        RedundantConnection.DisjointSet dsu = new RedundantConnection.DisjointSet(n);
        for(int[] edge : edges) {
            if(Arrays.equals(edge, skipEdges))
                continue;

            if(!dsu.union(edge[0], edge[1]))
                return edge;
        }

        return null;
    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{{1,2},{1,3},{2,3}};
        System.out.println(Arrays.toString(findRedundantDirectedConnection(edges)));

        edges = new int[][]{{1,2},{2,3},{3,4},{4,1},{1,5}};
        System.out.println(Arrays.toString(findRedundantDirectedConnection(edges)));
    }
}
