package com.project4;

public class GraphCycle extends GraphSearch {

    public GraphCycle(Graph g, int from, int to) {
        super(g, from, to);
        wasFound = PerformSearch();
    }

    @Override
    public boolean PerformSearch() {
        return checkCyclic(0, NodeState_no_parent_assigned);
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
