public class App {
    static private Graph CreateGraph() {
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
        // g.AddEdge(new Edge(4, 6));

        return g;
    }

    public static void main(String[] args) throws Exception {
        Graph g = CreateGraph();
        GraphBFS b = new GraphBFS(g, 4, 2);
        GraphDFS d = new GraphDFS(g, 4, 2);
        GraphSearch c = new GraphConnection(g, 4, 2);
        GraphSearch cyc = new GraphCycle(g, -1, -1);

        System.out.println("BFS " + b.GetNamePath());
        System.out.println("DFS " + d.GetNamePath());
        System.out.println("Connected " + c.Result());
        System.out.println("Is Cyclic " + cyc.Result());
    }
}
