package Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kalpak
 *
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 *
 * Example 1:
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 *
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4].
 * Therefore its length is 4.
 *
 * Example 2:
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 104
 * -10^9 <= nums[i] <= 10^9
 *
 *
 * Follow up: Could you implement the O(n) solution?
 *
 */

public class LongestConsecutiveSequence {
    public static int longestConsecutive(int[] nums) {
        DisjointSet dsu = new DisjointSet(nums.length);
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i]))
                continue;

            // number not present in the map.
            // Check if nums[i-1] and nums[i+1] is present.
            // If so, union them.
            // Remember we do the Union Find on indices.
            if(map.containsKey(nums[i] - 1))
                dsu.union(i, map.get(nums[i] - 1));

            if(map.containsKey(nums[i] + 1))
                dsu.union(i, map.get(nums[i] + 1));

            map.put(nums[i], i);
        }

        return dsu.getMaximumSizeOfComponent();

    }

    static private class DisjointSet {
        private int[] size;             // track size of each component
        private int[] id;               // id[i] points to the parent of i, if id[i] = i, then i is the root node.


        public DisjointSet(int size) {
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

            return true;
        }

        public int getMaximumSizeOfComponent() {
            int max = 0;
            for(int i = 0; i < id.length; i++) {
                if(id[i] == i && size[i] > max)
                    max = size[i];
            }
            return max;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 100, 101, 1, 102, 4};
        System.out.println(longestConsecutive(nums));
    }
}
