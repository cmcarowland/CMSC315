package com.project4;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class GraphSearch {
    public static final int NodeState_visited = -1; 
    public static final int NodeState_unvisited = -2;
    public static final int NodeState_no_parent_assigned = -3;

    Graph graph;
    Integer[] visited;
    Integer[] route;
    Integer from;
    Integer to;
    boolean wasFound;

    public GraphSearch(Graph g, int f, int t) {
        graph = g;
        from = f;
        to = t;
        wasFound = false;
        visited = new Integer[graph.nodes.size()];
        Arrays.fill(visited, NodeState_unvisited);
        route = new Integer[graph.nodes.size()];
        Arrays.fill(route, NodeState_no_parent_assigned);
    }

    public boolean Result() {
        return wasFound;
    }

    public boolean PerformSearch() { return false; }

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

        Collections.reverse(path);// path.reversed();
        return path;
    }

    public String GetNamePath() {
        var path = GetPathToTarget();
        String result = "";
        for(var node : path) {
            result += graph.nodes.get(node).GetName() + " - ";
        }

        return result.substring(0, result.length() - 3);
    }
}