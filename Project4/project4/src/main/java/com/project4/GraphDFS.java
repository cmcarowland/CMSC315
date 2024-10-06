/*
 * Raymond Rowland
 * Project 4
 * 10/6/24
 * 
 * The GraphDFS class implements the depth-first search (DFS) algorithm 
 * for traversing a graph. It extends the GraphSearch class, initializing 
 * the search with a graph and specified start and target vertices. The 
 * PerformSearch() method uses a stack to explore nodes, marking them as 
 * visited and tracking the route. If the target vertex is found during 
 * traversal, it returns true; otherwise, it completes without finding 
 * the target and returns false.
 */

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
