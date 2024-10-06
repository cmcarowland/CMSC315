/*
 * Raymond Rowland
 * Project 4
 * 10/6/24
 * 
 * The GraphSearch class serves as a base for implementing various search 
 * algorithms on a graph. It defines constants for node states (visited, 
 * unvisited, and no parent assigned) and initializes essential attributes 
 * such as the graph, visited nodes, and the route between nodes. The 
 * constructor sets up the search parameters, while the Result() method 
 * checks if the target was found. The PerformSearch() method is meant 
 * to be overridden by derived classes. Additionally, the class provides 
 * methods to retrieve the path to the target node and to format the 
 * path as a string.
 */


package com.project4;

import java.util.Arrays;
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

    public LinkedList<Vertex> GetPathToTarget() {
        LinkedList<Vertex> path = new LinkedList<>();
        
        if(!wasFound || to < 0)
            return path;

        int nd = to;
        path.add(graph.GetNode(nd));
        while(nd != from) {
            nd = route[nd];
            path.add(graph.GetNode(nd));
        }

        Collections.reverse(path);
        return path;
    }

    public String GetNamePath() {
        var path = GetPathToTarget();
        String result = "";
        for(var node : path) {
            result += node.GetName() + " - ";
        }

        return result.substring(0, result.length() - 3);
    }
}