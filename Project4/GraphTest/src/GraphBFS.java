
import java.util.LinkedList;
import java.util.Queue;

public class GraphBFS extends GraphSearch {

    public GraphBFS(Graph g, int f, int t) {
       super(g, f, t);
       wasFound = PerformSearch();
    }

    @Override
    public boolean PerformSearch() {
        Queue<Edge> queue = new LinkedList<Edge>();
        Edge dummy = new Edge(from, from);

        queue.offer(dummy);
        visited[from] = NodeState_visited;

        while(!queue.isEmpty()) {
            Edge next = queue.poll();
            route[next.To()] = next.From();
            
            if(next.To() == to) {
                return true;
            }
            
            for(var e : graph.edges.get(next.To())) {
                if(visited[e.To()] == NodeState_unvisited) {
                    queue.offer(e);
                    visited[e.To()] = NodeState_visited;
                }
            }
        }

        return false;
    }
}

