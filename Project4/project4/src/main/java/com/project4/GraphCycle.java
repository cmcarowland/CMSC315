/*
 * Raymond Rowland
 * Project 4
 * 10/6/24
 * 
 * The GraphCycle class implements an algorithm to detect cycles in a 
 * graph using depth-first search (DFS). It extends the GraphSearch class 
 * and initializes the search with a graph. The PerformSearch() method 
 * iterates through each node, recursively checking for cycles. 
 * The algorithm tracks visited nodes and 
 * their parents to avoid false positives, 
 * returning true if a cycle is found and false otherwise.
 */


package com.project4;

import java.util.Arrays;

public class GraphCycle extends GraphSearch {

    public GraphCycle(Graph g, int from, int to) {
        super(g, from, to);
        wasFound = PerformSearch();
    }

    @Override
    public boolean PerformSearch() {
        for(var n : graph.nodes) {
            visited = new Integer[graph.nodes.size()];
            Arrays.fill(visited, NodeState_unvisited);
            if(checkCyclic(n.Index(), NodeState_no_parent_assigned))
                return true;
        }

        return false;
    }

    boolean checkCyclic(int node, int parent) {
        visited[node] = NodeState_visited;

        for (Edge i : graph.edges.get(node)) {
            if (visited[i.To()] != NodeState_visited) {
                if (checkCyclic(i.To(), node))
                    return true;
            } else if (i.To() != parent)
                return true;
        }
        
        return false;
    }
}
