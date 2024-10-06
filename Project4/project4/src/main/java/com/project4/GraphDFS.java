package com.project4;

import java.util.Stack;

public class GraphDFS extends GraphSearch {
    public GraphDFS(Graph g, int f, int t) {
        super(g, f, t);
        wasFound = PerformSearch();
    }

    @Override
    public boolean PerformSearch() {
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
}
