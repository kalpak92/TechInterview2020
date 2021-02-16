package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.
 *
 * Now given all the cities and flights, together with starting city src and the destination dst,
 * your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.
 *
 * Example 1:
 * Input:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * Output: 200
 *
 * Example 2:
 * Input:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * Output: 500
 *
 * Constraints:
 * The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
 * The size of flights will be in range [0, n * (n - 1) / 2].
 * The format of each flight will be (src, dst, price).
 * The price of each flight will be in the range [1, 10000].
 * k is in the range of [0, n - 1].
 * There will not be any duplicated flights or self cycles.
 *
 */

public class CheapestFlightsWithinKStops {
    static class Edge {
        int destination;
        int cost;

        Edge(int destination, int cost) {
            this.destination = destination;
            this.cost = cost;
        }
    }

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);      // {cost, city, stopsRemaining}

        buildGraph(graph, flights);

        minHeap.offer(new int[]{0, src, K+1});  // K+1 because K stops inbetween and the last one is for the destination.

        while(!minHeap.isEmpty()) {
            int[] currentPosition = minHeap.poll();
            int price = currentPosition[0];
            int position = currentPosition[1];
            int stopsRemaining = currentPosition[2];

            if(position == dst)
                return price;

            if(stopsRemaining > 0) {
                // Find neighbors of position
                List<Edge> neighbors = graph.get(position);
                if (neighbors != null) {
                    for(Edge neighbor : neighbors) {
                        minHeap.offer(new int[]{price + neighbor.cost, neighbor.destination, stopsRemaining - 1});
                    }
                }
            }
        }
        return -1;
    }

    private static void buildGraph(Map<Integer, List<Edge>> graph, int[][] flights) {
        for(int[] flightDetails : flights) {
            graph.putIfAbsent(flightDetails[0], new ArrayList<>());
            graph.get(flightDetails[0]).add(new Edge(flightDetails[1], flightDetails[2]));
        }
    }

    public static void main(String[] args) {
        int[][] flights = new int[][]{{0,1,100},{1,2,100},{0,2,500}};

        System.out.println(findCheapestPrice(3, flights, 0, 2, 1));
    }
}
