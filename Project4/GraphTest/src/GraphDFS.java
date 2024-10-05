import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class GraphDFS {
    public static final int NodeState_visited = -1; 
    public static final int NodeState_unvisited = -2;
    public static final int NodeState_no_parent_assigned = -3;

    Graph graph;
    Integer[] visited;
    Integer[] route;
    Integer from;
    Integer to;
    boolean wasFound;

    public GraphDFS(Graph g, int f, int t) {
        graph = g;
        from = f;
        to = t;
        wasFound = false;
        visited = new Integer[graph.nodes.size()];
        Arrays.fill(visited, NodeState_unvisited);
        route = new Integer[graph.nodes.size()];
        Arrays.fill(route, NodeState_no_parent_assigned);
    }
    
    public boolean Found() {
        return wasFound;
    }
    
    public void Search() {
        wasFound = PerformDFS();
    }

    private boolean PerformDFS() {
        Stack<Edge> stack = new Stack<>();
        Edge dummy = new Edge(from, from);

        stack.push(dummy);

        while(!stack.isEmpty()) {
            Edge next = stack.pop();
            route[next.To()] = next.From();
            visited[next.To()] = NodeState_visited;

            if(next.To() == to) {
                return true;
            }
            
            for(var e : graph.edges.get(next.To())) {
                if(visited[e.To()] == NodeState_unvisited) {
                    stack.push(e);
                }
            }
        }

        return false;
    }

    public LinkedList<Integer> GetPathToTarget() {
        LinkedList<Integer> path = new LinkedList<>();
        
        if(!wasFound || to < 0)
            return path;

        int nd = to;
        path.add(nd);
        while(nd != from) {
            nd = route[nd];
            path.add(nd);
        }

        return path.reversed();
    }
}
