/*
 * Raymond Rowland
 * Project 4
 * 10/6/24
 * 
 * The Edge class represents a directed edge in a graph, defined by 
 * a starting vertex ('from') and an ending vertex ('to'). It provides 
 * getter and setter methods for both endpoints, allowing for easy 
 * manipulation of edge data. The class includes constructors for creating 
 * edges, with a default constructor initializing the vertices to an 
 * invalid state.
 */


package com.project4;

public class Edge {
    private int from;
    private int to;

    public int From() {
        return from;
    }

    public void SetFrom(int _from) {
        from = _from;
    }

    public int To() {
        return to;
    }

    public void SetTo(int _to) {
        to = _to;
    }

    public Edge() {
        from = Vertex.INVALID_VERTEX;
        to = Vertex.INVALID_VERTEX;
    }
  
    public Edge(int _from, int _to) {
        from = _from;
        to = _to;
    }
}
