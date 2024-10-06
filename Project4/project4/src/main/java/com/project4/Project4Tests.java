package com.project4;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Project4Tests {
    private Graph CreateGraph() {
        Graph g = new Graph();
        g.AddNode(new Vertex(0, 3));
        g.AddNode(new Vertex(-1, 2));
        g.AddNode(new Vertex(2, 2));
        g.AddNode(new Vertex(1, 1));
        g.AddNode(new Vertex(0, 0));
        g.AddNode(new Vertex(1, -1));
        g.AddNode(new Vertex(5, 5));

        g.AddEdge(new Edge(0, 1));
        g.AddEdge(new Edge(0, 2));
        g.AddEdge(new Edge(1, 4));
        g.AddEdge(new Edge(2, 3));
        g.AddEdge(new Edge(3, 4));
        g.AddEdge(new Edge(3, 5));
        g.AddEdge(new Edge(4, 5));
        g.AddEdge(new Edge(4, 6));

        return g;
    }
   
    private Graph CreatePDFGraph() {
        Graph g = new Graph();
        g.AddNode(new Vertex(0, 0));
        g.AddNode(new Vertex(1, -1));
        g.AddNode(new Vertex(-1, -2));
        g.AddNode(new Vertex(1, -2));
        g.AddNode(new Vertex(1, 1));
        g.AddNode(new Vertex(-1, 1));

        g.AddEdge(new Edge(0, 5));
        g.AddEdge(new Edge(0, 4));
        g.AddEdge(new Edge(0, 1));
        g.AddEdge(new Edge(1, 3));
        g.AddEdge(new Edge(3, 2));

        return g;
    }

    private Graph CreatePDFGraphDisconnected() {
        Graph g = new Graph();
        g.AddNode(new Vertex(0, 0));
        g.AddNode(new Vertex(1, -1));
        g.AddNode(new Vertex(-1, -2));
        g.AddNode(new Vertex(1, -2));
        g.AddNode(new Vertex(1, 1));
        g.AddNode(new Vertex(-1, 1));

        g.AddEdge(new Edge(0, 5));
        g.AddEdge(new Edge(0, 4));
        g.AddEdge(new Edge(0, 1));
        g.AddEdge(new Edge(1, 3));

        return g;
    }

    @Test 
    public void TestGraph() {
        Graph g = CreateGraph();

        assertEquals(false, g.GetEdge(0, 2) == null);
        assertEquals(true, g.GetEdge(0, 5) == null);
        assertEquals(false, g.GetNode(0) == null);
        assertEquals(true, g.GetNode(7) == null);
        g.RemoveNode(3);
        assertEquals(true, g.GetNode(3) == null);
        assertEquals(true, g.GetEdge(3, 4) == null);
        assertEquals(true, g.GetEdge(5, 3) == null);
    }

    @Test
    public void TestSearch() {
        Graph g = CreateGraph();
        GraphDFS dfs = new GraphDFS(g, 4, 2);
        assertEquals(true, dfs.Result());
        assertEquals("[4, 1, 0, 2]", dfs.GetPathToTarget().toString());
    }
    
    @Test
    public void TestPDFSearch() {
        Graph g = CreatePDFGraph();
        GraphDFS dfs = new GraphDFS(g, 5, 2);
        assertEquals("F - A - B - D - C", dfs.GetNamePath());
        assertEquals(true, dfs.Result());
    }

    @Test
    public void TestBFS() {
        Graph g = CreateGraph();
        GraphBFS bfs = new GraphBFS(g, 4, 2);
        assertEquals(true, bfs.Result());
        assertEquals("[4, 3, 2]", bfs.GetPathToTarget().toString());
    }


    @Test
    public void TestPDFBFS() {
        Graph g = CreatePDFGraph();
        GraphBFS bfs = new GraphBFS(g, 5, 2);
        assertEquals(true, bfs.Result());
        assertEquals("F - A - B - D - C", bfs.GetNamePath());
    }

    @Test
    public void TestPDFCycle() {
        Graph g = CreatePDFGraph();
        GraphSearch bfs = new GraphCycle(g, 0, 0);
        assertEquals(false, bfs.Result());
    }
    
    @Test
    public void TestCycle() {
        Graph g = CreateGraph();
        GraphSearch bfs = new GraphCycle(g, 0, 0);
        assertEquals(true, bfs.Result());
    }

    @Test
    public void TestPDFDisconnected() {
        Graph g = CreatePDFGraphDisconnected();
        GraphSearch bfs = new GraphConnection(g, 0, 0);
        assertEquals(false, bfs.Result());
    }
    @Test
    public void TestPDFConnected() {
        Graph g = CreatePDFGraph();
        GraphSearch bfs = new GraphConnection(g, 0, 0);
        assertEquals(true, bfs.Result());
    }
    @Test
    public void TestConnected() {
        Graph g = CreateGraph();
        GraphSearch bfs = new GraphConnection(g, 0, 0);
        assertEquals(true, bfs.Result());
    }
}
