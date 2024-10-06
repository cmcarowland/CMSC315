/*
 * Raymond Rowland
 * Project 4
 * 10/6/24
 * 
 * The GraphConnection class implements an algorithm to check if a graph 
 * is fully connected using a depth-first search (DFS) approach. It extends 
 * the GraphSearch class, initializing the search with a graph. The 
 * PerformSearch() method utilizes a stack to explore nodes, marking 
 * them as visited. It determines connectivity by checking if all nodes 
 * are reachable from the starting node, returning true if the graph is 
 * connected and false otherwise.
 */

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
