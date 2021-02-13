package Leetcode;

import java.util.*;

/**
 * @author kalpak
 *
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
 * reconstruct the itinerary in order.
 *
 * All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
 *
 * Note:
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
 *
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 * One must use all the tickets once and only once.
 *
 *
 * Example 1:
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 *
 *
 * Example 2:
 * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 *              But it is larger in lexical order.
 */

public class ReconstructItinerary {
    public static List<String> findItinerary(List<List<String>> tickets) {
        List<String> result = new ArrayList<>();
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        Deque<String> stack = new ArrayDeque<>();

        buildGraph(graph, tickets);
        buildItineraryDFS(graph, stack, "JFK");

        // Stack has the result. Unpack it
        while(!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;

    }

    private static void buildItineraryDFS(Map<String, PriorityQueue<String>> graph, Deque<String> stack, String startFrom) {
        PriorityQueue<String> destinations = graph.get(startFrom);

        while(destinations != null && !destinations.isEmpty())
            buildItineraryDFS(graph, stack, destinations.poll());

        stack.push(startFrom);
    }

    private static void buildGraph(Map<String, PriorityQueue<String>> graph, List<List<String>> tickets) {
        for(List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);

            if(!graph.containsKey(from))
                graph.put(from, new PriorityQueue<>());

            graph.get(from).offer(to);
        }
    }

    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();

        tickets.add(new ArrayList<String>(Arrays.asList(new String[]{"MUC", "LHR"})));
        tickets.add(new ArrayList<String>(Arrays.asList(new String[]{"JFK", "MUC"})));
        tickets.add(new ArrayList<String>(Arrays.asList(new String[]{"SFO", "SJC"})));
        tickets.add(new ArrayList<String>(Arrays.asList(new String[]{"LHR", "SFO"})));

        System.out.println(findItinerary(tickets));

    }
}
