package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * You have n processes forming a rooted tree structure.
 * You are given two integer arrays pid and ppid, where pid[i] is the ID of the ith process and ppid[i] is the ID of the ith process's parent process.
 *
 * Each process has only one parent process but may have multiple children processes.
 * Only one process has ppid[i] = 0, which means this process has no parent process (the root of the tree).
 *
 * When a process is killed, all of its children processes will also be killed.
 *
 * Given an integer kill representing the ID of a process you want to kill,
 * return a list of the IDs of the processes that will be killed. You may return the answer in any order.
 *
 *
 * Example 1:
 * Input: pid = [1,3,10,5], ppid = [3,0,5,3], kill = 5
 * Output: [5,10]
 * Explanation: The processes colored in red are the processes that should be killed.
 *
 * Example 2:
 * Input: pid = [1], ppid = [0], kill = 1
 * Output: [1]
 *
 * Constraints:
 *
 * n == pid.length
 * n == ppid.length
 * 1 <= n <= 5 * 104
 * 1 <= pid[i] <= 5 * 104
 * 0 <= ppid[i] <= 5 * 104
 * Only one process has no parent.
 * All the values of pid are unique.
 * kill is guaranteed to be in pid.
 *
 */

public class KillProcess {
    public static List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        // Simulate the ppid and pid relation as a key value pair
        for(int i = 0; i < ppid.size(); i++) {
            if(ppid.get(i) > 0) {
                List<Integer> temp = map.getOrDefault(ppid.get(i), new ArrayList<>());
                temp.add(pid.get(i));   // add the process corresponding to the ppid
                map.put(ppid.get(i), temp);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        queue.offer(kill);
        while(!queue.isEmpty()) {
            int current = queue.poll();
            if(map.containsKey(current)) {
                for(int childProcess : map.get(current))
                    queue.offer(childProcess);
            }
            result.add(current);
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> pid = new ArrayList<>(Arrays.asList(new Integer[]{1, 3, 10, 5}));
        List<Integer> ppid = new ArrayList<>(Arrays.asList(new Integer[]{3, 0, 5, 3}));

        System.out.println(killProcess(pid, ppid, 5));
    }
}
