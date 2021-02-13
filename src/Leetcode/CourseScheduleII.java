package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * There are a total of n courses you have to take labelled from 0 to n - 1.
 *
 * Some courses may have prerequisites,
 * for example, if prerequisites[i] = [ai, bi] this means you must take the course bi before the course ai.
 *
 * Given the total number of courses numCourses and a list of the prerequisite pairs,
 * return the ordering of courses you should take to finish all courses.
 *
 * If there are many valid answers, return any of them.
 *
 * If it is impossible to finish all courses, return an empty array.
 *
 *
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0.
 * So the correct course order is [0,1].
 *
 * Example 2:
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2.
 * Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 *
 * Example 3:
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 *
 * Constraints:
 *
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * a_i != b_i
 * All the pairs [ai, bi] are distinct.
 *
 */

public class CourseScheduleII {
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegree = new int[numCourses];
        Deque<Integer> queue = new LinkedList<>();
        int[] result = new int[numCourses];
        int index = 0;

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

        // Add all nodes with in-degree == 0
        // Also, add them to the order array
        for(int i = 0; i < numCourses; i++) {
            if(indegree[i] == 0) {
                queue.offer(i);
                result[index] = i;
                index++;
            }
        }

        // Run BFS
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int c : graph.get(current)) {
                indegree[c]--;
                if (indegree[c] == 0) {
                    queue.add(c);
                    result[index] = c;
                    index++;
                }
            }
        }
        return (index == numCourses) ? result : new int[]{};
    }

    public static void main(String[] args) {
        int[][] prereqs = new int[][]{{1,0},{2,0},{3,1},{3,2}};
        System.out.println(Arrays.toString(findOrder(4, prereqs)));
    }
}
