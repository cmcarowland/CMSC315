package com.project4;

import java.util.Arrays;
import java.util.Stack;

public class GraphConnection extends GraphSearch {
    public GraphConnection(Graph g, int f, int t) {
        super(g, f, t);
        wasFound = PerformSearch();
    }

    @Override
    public boolean PerformSearch() {
        if(graph.nodes.size() == 0)
            return false;

        Stack<Edge> stack = new Stack<>();
        Edge dummy = new Edge(0, 0);

        stack.push(dummy);

        while(!stack.isEmpty()) {
            Edge next = stack.pop();
            route[next.To()] = next.From();
            visited[next.To()] = NodeState_visited;

            if(Arrays.stream(visited).distinct().count() == 1) {
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
}
