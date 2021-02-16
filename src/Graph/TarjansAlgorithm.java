package Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TarjansAlgorithm {
    static int index = 0;
    static int count = 0; // SCC count

    // Nodes in adjacency lists are numbered from 0 to N - 1
    public static List< List< Integer >> getSCCcount(List< List< Integer >> adjacencyLists) {
        index = 0; // reset index to 0
        count = 0; // reset count to 0
        int len = adjacencyLists.size();

        int[] discoveryTime = new int[len];
        int[] earliestDiscoveredNodeReachable = new int[len];

        Deque< Integer > sccStack = new ArrayDeque<>();
        boolean[] onStack = new boolean[len]; // to handle cross-edge scenario
        int[] id = new int[len]; // id[v] = id of strong component containing v
        // id[v] = 0 means v does not belong to any SCC.
        // We would make sure that component ids start from 1
        // id[] is initialized with all 0s

        for (int i = 0; i < adjacencyLists.size(); i++) {
            if (discoveryTime[i] == 0) { // indices[i] == 0 when vertex i is not already visited
                dfs(adjacencyLists, id, discoveryTime, earliestDiscoveredNodeReachable, sccStack, onStack, i);
            }
        }

        System.out.println("Number of SCCs: " + count);

        List< List< Integer >> strongComponents = new ArrayList<>();

        for (int i = 0; i < count; i++) { // initialization
            strongComponents.add(new ArrayList< Integer >());
        }

        for (int vertex = 0; vertex < len; vertex++) {
            int componentId = id[vertex];
            List< Integer > strongComponent = strongComponents.get(componentId - 1);
            strongComponent.add(vertex);
        }

        return strongComponents;
    }

    private static void dfs(List< List< Integer >> adjacencyLists, int[] id, int[] discoveryTime,
                            int[] earliestDiscoveredNodeReachable, Deque< Integer > strongComponentStack,
                            boolean[] onStack, int currentNode) {
        index++;
        discoveryTime[currentNode] = index; // array is zero based index, nodes are numbered from 0 to N - 1
        earliestDiscoveredNodeReachable[currentNode] = index;
        strongComponentStack.push(currentNode);
        onStack[currentNode] = true;

        for (int adjVertex : adjacencyLists.get(currentNode)) {
            //adjacencyList of vertex i is stored at index i in arraylist
            if (discoveryTime[adjVertex] == 0) { // discoveryTime[v] == 0 when vertex v is not already visited
                dfs(adjacencyLists, id, discoveryTime, earliestDiscoveredNodeReachable, strongComponentStack, onStack, adjVertex);

                earliestDiscoveredNodeReachable[currentNode] =
                        Math.min(earliestDiscoveredNodeReachable[currentNode], earliestDiscoveredNodeReachable[adjVertex]);
            } else { // adjacentVertex is already visited
                if (!onStack[adjVertex]) {
                    // the adjacent vertex is already visited BUT it is not in the stack
                    // this means the current edge is just a cross edge to an SCC
                    // which has already been found and processed.
                    // Cross edge always points to a vertex
                    // which has already been visited.
                    continue;
                } else {
                    // adjacent vertex is in stack, so must be in the same SCC
                    earliestDiscoveredNodeReachable[currentNode] =
                            Math.min(earliestDiscoveredNodeReachable[currentNode], discoveryTime[adjVertex]);
                    // NOTE: Because adjacentVertex is on the stack already, the edge (currentNode, adjacentVertex)
                    // is a back-edge in the DFS tree and
                    // therefore adjacentVertex is not in the subtree of currentNode.
                    // Because earliestDiscoveredNodeReachable[i]
                    // takes into account nodes reachable only through the nodes in the subtree of currentNode and we
                    // must stop at adjacentVertex and use discoveryTime[adjacentVertex]
                    // instead of earliestDiscoveredNodeReachable[adjacentVertex]. One back-edge is okay, but not more than one.
                }
            }
        }
        // now that DFS on currentNode is done lets see
        // if we got any SCC before backtracking
        if (earliestDiscoveredNodeReachable[currentNode] == discoveryTime[currentNode]) {
            count++;
            int top = 0;
            do {
                top = strongComponentStack.pop();
                id[top] = count;
                onStack[top] = false;
            } while (top != currentNode);
        }
    }

    public static void main(String[] args) {
    /*
       Given Graph:
       0<-----1
             _
        \    /|
        _\| /
           2
          / \
           |
           3
           |
          \ /
           6 _
          / |\
        |/_   \
        4----->5

        SCC => 0 - 1 - 2
        SCC => 4 - 5 - 6
        SCC => 3
     */
        List<List< Integer >> adjLists = new ArrayList<>();
        adjLists.add(new ArrayList< Integer >() {{ add(2); }});         // for node 0
        adjLists.add(new ArrayList< Integer >() {{ add(0); }});         // for node 1
        adjLists.add(new ArrayList< Integer >() {{ add(1); }});         // for node 2
        adjLists.add(new ArrayList< Integer >() {{ add(2); add(6); }}); // for node 3
        adjLists.add(new ArrayList< Integer >() {{ add(5); }});         // for node 4
        adjLists.add(new ArrayList< Integer >() {{ add(6); }});         // for node 5
        adjLists.add(new ArrayList< Integer >() {{ add(4); }});         // for node 6

        TarjansAlgorithm tarjan = new TarjansAlgorithm();
        List< List< Integer >> scc = tarjan.getSCCcount(adjLists);
        for (List< Integer > component : scc) {
            for (int i : component) {
                System.out.print(i + "  ");
            }
            System.out.println();
        }

    /*
       Given Graph:

       0<-------1
              _
        \     /|
        _\|  /
           2
           |
          \ /
           3
            _
         / |\
       |/_   \
       4----->5

        SCC => 0 - 1 - 2
        SCC => 3 - 4 - 5
     */
        adjLists = new ArrayList<>();
        adjLists.add(new ArrayList< Integer >() {{ add(2); }}); // for node 0
        adjLists.add(new ArrayList< Integer >() {{ add(0); }}); // for node 1
        adjLists.add(new ArrayList< Integer >() {{ add(1); add(3); }}); // for node 2
        adjLists.add(new ArrayList< Integer >() {{ add(4); }}); // for node 3
        adjLists.add(new ArrayList< Integer >() {{ add(5); }}); // for node 4
        adjLists.add(new ArrayList< Integer >() {{ add(3); }}); // for node 5

        scc = tarjan.getSCCcount(adjLists);
        for (List< Integer > component : scc) {
            for (int i : component) {
                System.out.print(i + "  ");
            }
            System.out.println();
        }


    /*
        Given Graph:
            0

        SCC: 0
     */
        adjLists = new ArrayList<>();
        adjLists.add(new ArrayList< Integer >()); // for node 0

        scc = tarjan.getSCCcount(adjLists);
        for (List< Integer > component : scc) {
            for (int i : component) {
                System.out.print(i + "  ");
            }
            System.out.println();
        }

     /*
        0------>1
        ^       |
        |_______|

        SCC: 0, 1
     */
        adjLists = new ArrayList<>();
        adjLists.add(new ArrayList<Integer>(){{add(1);}}); // for node 0
        adjLists.add(new ArrayList<Integer>(){{add(0);}}); // for node 1

        scc = tarjan.getSCCcount(adjLists);
        for (List<Integer> component : scc) {
            for (int i : component) {
                System.out.print(i + "  ");
            }
            System.out.println();
        }

    }
}
