package com.project4;

import java.util.LinkedList;

public class Graph {
    LinkedList<Vertex> nodes;
    LinkedList<LinkedList<Edge>> edges;
    
    private int nextNode;

    public Graph() {
        nextNode = 0;
        nodes = new LinkedList<Vertex>();
        edges = new LinkedList<LinkedList<Edge>>();
    }

    public Vertex GetNode(int index) {
        if(index > nodes.size() || index < 0)
            return null;

        if(index < nodes.size()) {
            var n = nodes.get(index);
            if(n.Index() != Vertex.INVALID_VERTEX)
                return n;
        }

        
        return null;
    }

    public Vertex GetNodeByName(String s) {
        for(var n : nodes) {
            if(n.GetName().equals(s)) {
                return n;
            }
        }

        return null;
    }

    public Edge GetEdge(int from, int to) {
        for(var edge : edges.get(from)) {
            if(edge.To() == to) {
                return edge;
            }
        }

        return null;
    }

    public Vertex GetNextFreeNode() {
        for(var node : nodes) {
            if(node.Index() == Vertex.INVALID_VERTEX) {
                return node;
            }
        }

        return null;
    }

    public int AddNode(Vertex node) {
        Vertex v = GetNextFreeNode();
        if(v == null) {
            node.SetIndex(nextNode++);
            nodes.add(node);
            edges.add(new LinkedList<>());
        } else {
            int i = 0;
            for(var n : nodes) {
                if(n == v) {
                    break;
                }
                i++;
            }
            node.SetIndex(i);
            nodes.set(i, node);
        }

        return node.Index();
    } 

    public void RemoveNode(int i) {
        nodes.get(i).SetIndex(Vertex.INVALID_VERTEX);
        edges.get(i).clear();
        for(var e : edges) {
            for(var edge : e) {
                if(edge.To() == i)
                    e.remove(edge);
            }
        }
    }

    public void AddEdge(Edge edge) {
        edges.get(edge.From()).push(edge);
        edges.get(edge.To()).push(new Edge(edge.To(), edge.From()));
    }

    public void RemoveEdge(int from, int to) {
        Edge e = GetEdge(from, to);
        if(e != null) {
            edges.get(from).remove(e);
        }
    }

    public LinkedList<Vertex> performDFS(int from, int to) {
        GraphSearch gs = new GraphDFS(this, from, to);
        gs.PerformSearch();
        return gs.GetPathToTarget();
    }

    public LinkedList<Vertex> performBFS(int from, int to) {
        GraphSearch gs = new GraphBFS(this, from, to);
        gs.PerformSearch();
        return gs.GetPathToTarget();
    }

    public boolean performConnectionCheck() {
        GraphSearch gs = new GraphConnection(this, 0, 0);
        return gs.Result();
    }

    public boolean performCyclicCheck() {
        GraphSearch gs = new GraphCycle(this, 0, 0);
        return gs.Result();
    }
}
