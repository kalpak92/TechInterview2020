package Leetcode;

import java.util.Arrays;

/**
 * @author kalpak
 *
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 *
 * The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added.
 * The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
 *
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v,
 * that represents an undirected edge connecting nodes u and v.
 *
 * Return an edge that can be removed so that the resulting graph is a tree of N nodes.
 * If there are multiple answers, return the answer that occurs last in the given 2D-array.
 * The answer edge [u, v] should be in the same format, with u < v.
 *
 * Example 1:
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 *
 * Explanation: The given undirected graph will be like this:
 *   1
 *  / \
 * 2 - 3
 *
 * Example 2:
 * Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * Output: [1,4]
 *
 * Explanation: The given undirected graph will be like this:
 * 5 - 1 - 2
 *     |   |
 *     4 - 3
 *
 * Note:
 * The size of the input 2D-array will be between 3 and 1000.
 * Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 *
 */

public class RedundantConnection {
    public static int[] findRedundantConnection(int[][] edges) {
        DisjointSet dsu = new DisjointSet(edges.length + 1);
        for(int[] edge : edges) {
            if(!dsu.union(edge[0], edge[1]))
                return edge;
        }
        return new int[0];
    }

    static public class DisjointSet {
        private int numberOfElements;   // number of elements in union find
        private int[] size;             // track size of each component
        private int[] id;               // id[i] points to the parent of i, if id[i] = i, then i is the root node.

        private int numOfComponents;

        public DisjointSet(int size) {
            if(size <= 0)
                throw new IllegalArgumentException("Size <= 0 is not allowed.");

            this.numberOfElements = numOfComponents = size;
            this.size = new int[size];
            this.id = new int[size];

            // Initialize the arrays as individual components
            for(int i = 0; i < size; i++) {
                id[i] = i;  // self root
                this.size[i] = 1;
            }
        }

        public int find(int p) {
            // find the root of the component
            int root = p;
            while(root != id[root])
                root = id[root];

            // Path Compression : Gives amortized time complexity
            while(p != root) {
                int next = id[p];   // Store the id of p and make p point to the root
                id[p] = root;       // Compress
                p = next;           // do the same for the rest.
            }

            return root;
        }

        public boolean union(int p, int q) {
            int root1 = find(p);
            int root2 = find(q);

            if(root1 == root2)
                return false;

            // Merge component with smaller size to the component with larger size.
            if(size[root1] < size[root2]) {
                size[root2] += size[root1];
                id[root1] = root2;          // Merge
            } else {
                size[root1] += size[root2];
                id[root2] = root1;
            }

            numOfComponents--;
            return true;
        }
    };

    public static void main(String[] args) {
        int[][] edges = new int[][]{{1,2},{1,3},{2,3}};
        System.out.println(Arrays.toString(findRedundantConnection(edges)));
    }
}
