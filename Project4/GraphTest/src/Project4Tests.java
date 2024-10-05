
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Project4Tests {
    private Graph CreateGraph() {
        Graph g = new Graph();
        int index = g.AddNode(new Vertex(0, 3));
        g.AddEdge(new Edge(0, 1));
        g.AddEdge(new Edge(0, 2));

        g.AddNode(new Vertex(-1, 2));
        g.AddEdge(new Edge(1, 4));
        g.AddEdge(new Edge(1, 0));

        g.AddNode(new Vertex(2, 2));
        g.AddEdge(new Edge(2, 0));
        g.AddEdge(new Edge(2, 3));

        g.AddNode(new Vertex(1, 1));
        g.AddEdge(new Edge(3, 2));
        g.AddEdge(new Edge(3, 4));
        g.AddEdge(new Edge(3, 5));

        g.AddNode(new Vertex(0, 0));
        g.AddEdge(new Edge(4, 1));
        g.AddEdge(new Edge(4, 3));
        g.AddEdge(new Edge(4, 5));

        g.AddNode(new Vertex(1, -1));
        g.AddEdge(new Edge(5, 4));
        g.AddEdge(new Edge(5, 3));

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
        dfs.Search();       
        assertEquals(true, dfs.Found());
        System.out.println(dfs.GetPathToTarget());
    }
}
