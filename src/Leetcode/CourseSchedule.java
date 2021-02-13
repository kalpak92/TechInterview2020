package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 *
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 *
 *
 * Example 2:
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1.
 * So it is impossible.
 *
 *
 * Constraints:
 *
 * 1 <= numCourses <= 105
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * All the pairs prerequisites[i] are unique.
 *
 */

public class CourseSchedule {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegree = new int[numCourses];
        Deque<Integer> queue = new LinkedList<>();

        // Initialize the graph
        for(int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }

        // Put the edges into the graph.
        // Put the edges  ways as directed
        // Update the in-degree
        for(int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]);
            indegree[edge[0]]++;
        }


        // Add all nodes with indegree == 0
        for(int i = 0; i < numCourses; i++) {
            if(indegree[i] == 0)
                queue.offer(i);
        }

        // Run BFS
        while(!queue.isEmpty()) {
            int current = queue.poll();

            List<Integer> adjList = graph.get(current);
            for(int i : adjList) {
                indegree[i]--;
                if(indegree[i] == 0)
                    queue.offer(i);
            }
            numCourses--;
        }
        return numCourses == 0;
    }

    public static void main(String[] args) {
        int[][] prereq = new int[][]{{1,0},{0,1}};
        System.out.println(canFinish(2, prereq));
    }
}
