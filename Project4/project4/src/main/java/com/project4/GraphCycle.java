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
