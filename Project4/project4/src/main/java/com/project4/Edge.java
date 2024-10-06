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
