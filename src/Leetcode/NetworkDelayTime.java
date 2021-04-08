package Leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author kalpak
 *
 * You are given a network of n nodes, labeled from 1 to n.
 * You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi),
 * where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.
 *
 * We will send a signal from a given node k. Return the time it takes for all the n nodes to receive the signal.
 * If it is impossible for all the n nodes to receive the signal, return -1.
 *
 *
 * Example 1:
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * Output: 2
 *
 *
 * Example 2:
 * Input: times = [[1,2,1]], n = 2, k = 1
 * Output: 1
 *
 *
 * Example 3:
 * Input: times = [[1,2,1]], n = 2, k = 2
 * Output: -1
 *
 * Constraints:
 * 1 <= k <= n <= 100
 * 1 <= times.length <= 6000
 * times[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 0 <= wi <= 100
 * All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
 *
 */

public class NetworkDelayTime {
    public static int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, Map<Integer,Integer>> map = new HashMap<>();
        int result = 0;
        boolean[] isVisited = new boolean[n + 1];

        for(int[] time : times){
            map.putIfAbsent(time[0], new HashMap<>());
            map.get(time[0]).put(time[1], time[2]);
        }

        //distance, node into minHeap
        Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        minHeap.add(new int[]{0, k});

        while (!minHeap.isEmpty()) {
            int[] current = minHeap.remove();
            int currentNode = current[1];
            int currentTime = current[0];

            if(isVisited[currentNode])
                continue;

            isVisited[currentNode] = true;
            result = currentTime;
            n--;

            if(map.containsKey(currentNode)){
                for(int next : map.get(currentNode).keySet()){
                    minHeap.add(new int[]{currentTime + map.get(currentNode).get(next), next});
                }
            }
        }
        return n == 0 ? result : -1;
    }

    public static void main(String[] args) {
        int[][] times = new int[][]{{2,1,1},{2,3,1},{3,4,1}};

        System.out.println("The network Delay time is : " + networkDelayTime(times, 4, 2));
    }
}
