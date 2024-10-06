import java.util.List;
import java.util.Stack;

public class GraphCycle extends GraphSearch {

    public GraphCycle(Graph g, int from, int to) {
        super(g, from, to);
        wasFound = PerformSearch();
    }

    @Override
    public boolean PerformSearch() {
        return isCyclicUtil(0, NodeState_no_parent_assigned);
    }

    boolean isCyclicUtil(int node, int parent) {
      
        // Mark the current node as visited
        visited[node] = NodeState_visited;

        // Recur for all the vertices
        // adjacent to this vertex
        for (Edge i : graph.edges.get(node)) {
          
            // If an adjacent vertex is not visited,
            // then recur for that adjacent
            if (visited[i.To()] != NodeState_visited) {
                if (isCyclicUtil(i.To(), node))
                    return true;
            }
          
            // If an adjacent vertex is visited and
            // is not parent of current vertex,
            // then there exists a cycle in the graph.
            else if (i.To() != parent)
                return true;
        }
        
        return false;
    }

    // // Returns true if the graph contains
    // // a cycle, else false.
    // static boolean isCyclic(int V, List<List<Integer>> adj) {
      
    //     // Mark all the vertices as not visited
    //     boolean[] visited = new boolean[V];

    //     // Call the recursive helper function
    //     // to detect cycle in different DFS trees
    //     for (int u = 0; u < V; u++) {
          
    //         // Don't recur for u if it is already visited
    //         if (!visited[u]) {
    //             if (isCyclicUtil(u, adj, visited, -1))
    //                 return true;
    //         }
    //     }
    //     return false;
    // }
}
