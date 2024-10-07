/*
 * Raymond Rowland
 * Project 4
 * 10/6/24
 * 
 * The App class extends JavaFX Application to create a GUI for graph 
 * operations. It initializes UI components like buttons and text fields 
 * for adding edges, checking connectivity, and performing searches (DFS/BFS). 
 * The application manages user input, validates vertex names, and displays 
 * results in a text field. It interacts with a Graph object to execute 
 * graph-related functionalities.
 */

package com.project4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.LinkedList;

/*
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static Graph graph;
    private static TextField output;
    private static TextField vertex1;
    private static TextField vertex2;
    private static MyPane mp;

    @Override
    public void start(Stage stage) throws IOException {
        Pane p = new Pane();
        BackgroundFill bgf = new BackgroundFill(Color.valueOf("#141414"), null, p.getInsets());
        Background bg = new Background(bgf);
        p.setBackground(bg);
        
        mp = new MyPane();
        p.getChildren().add(mp);
        p.getChildren().add(createButton("Add Edge", 50, 15, this::AddEdgeClicked));
        p.getChildren().add(createButton("Is Connected?", 25, 435, this::OnConnectedClicked));
        p.getChildren().add(createButton("Has Cycles?", 125, 435, this::OnCyclicClicked));
        p.getChildren().add(createButton("Depth First Search", 220, 435, this::OnDFSClicked));
        p.getChildren().add(createButton("Breadth First Search", 350, 435, this::OnBFSClicked));
        output = createTextField(25, 465, 450);
        p.getChildren().add(output);
        vertex1 = createTextField(200, 15, 50);
        p.getChildren().add(vertex1);
        vertex2 = createTextField(325,15,50);
        p.getChildren().add(vertex2);
        p.getChildren().add(createLabel("Vertex 1", 150, 20));
        p.getChildren().add(createLabel("Vertex 2", 275, 20));

        scene = new Scene(p, 500, 500);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.setTitle("Raymond Rowland Project 4");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        graph = new Graph();
        launch();
    }

    Button createButton(String title, double x, double y, MouseClickedInterface mci) {
        var b1 = new Button(title);
        b1.setLayoutX(x);
        b1.setLayoutY(y);
        b1.setOnMouseClicked(e -> mci.execute(e));
        return b1;
    }

    TextField createTextField(double x, double y, double width) {
        var tf = new TextField();
        tf.setLayoutX(x);
        tf.setLayoutY(y);
        tf.setMinWidth(width);
        tf.setMaxWidth(width);
        return tf;
    }

    Label createLabel(String title, double x, double y) {
        var label = new Label(title);
        label.setTextFill(Color.WHITE);
        label.setLayoutX(x);
        label.setLayoutY(y);
        return label;
    }

    void OnConnectedClicked(MouseEvent me) {
        output.setText(graph.performConnectionCheck() ? "This graph is connected" : "This graph is not connected");
    }
    
    void OnCyclicClicked(MouseEvent me) {
        output.setText(graph.performCyclicCheck() ? "This graph contains cycles" : "This graph does not contain cycles");
    }
    
    void OnDFSClicked(MouseEvent me) {
        Vertex from = null;
        Vertex to = null;

        if(validateNodeInput(vertex1)) {
            from = graph.GetNodeByName(vertex1.getText());
        } else {
            if(vertex1.getText() == "")
                from = graph.GetNode(0);
            else {
                output.setText("Vertex 1 Invalid Input");
                return;
            }
        }

        if(validateNodeInput(vertex2)) {
            to = graph.GetNodeByName(vertex2.getText());
        } else {
            if(vertex2.getText() == "")
                to = graph.GetNode(graph.nodes.size() - 1);
            else {
                output.setText("Vertex 2 Invalid Input");
                return;
            }
        }

        if(from == null && to == null) {
            output.setText("Graph contains no nodes, search canceled");
            return;
        }

        var verts = graph.performDFS(from.Index(), to.Index());
        output.setText(formatList(verts));
    }
    
    void OnBFSClicked(MouseEvent me) {
        Vertex from = null;
        Vertex to = null;

        if(validateNodeInput(vertex1)) {
            from = graph.GetNodeByName(vertex1.getText());
        } else {
            if(vertex1.getText() == "")
                from = graph.GetNode(0);
            else {
                output.setText("Vertex 1 Invalid Input");
                return;
            }
        }

        if(validateNodeInput(vertex2)) {
            to = graph.GetNodeByName(vertex2.getText());
        } else {
            if(vertex2.getText() == "")
                to = graph.GetNode(graph.nodes.size() - 1);
            else {
                output.setText("Vertex 2 Invalid Input");
                return;
            }
        }

        if(from == null && to == null) {
            output.setText("Graph contains no nodes, search canceled");
            return;
        }

        var verts = graph.performBFS(from.Index(), to.Index());
        output.setText(formatList(verts));
    }

    String formatList(LinkedList<Vertex> verts) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if(verts.size() == 0)
            return sb.append(" ]").toString();

        for(var vert : verts) {
            sb.append(vert.GetName() + ", ");
        }
        return sb.toString().substring(0, sb.length() - 2) + "]";
    }

    boolean validateNodeInput(TextField tf) {
        String v1 = tf.getText();

        if(v1 != ""){
            if(graph.GetNodeByName(v1) == null) {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    void AddEdgeClicked(MouseEvent me) {
        Vertex from;
        Vertex to;

        if(validateNodeInput(vertex1)) {
            from = graph.GetNodeByName(vertex1.getText());
        } else {
            output.setText("Vertex 1 Invalid Input");
            return;
        }

        if(validateNodeInput(vertex2)) {
            to = graph.GetNodeByName(vertex2.getText());
        } else {
            output.setText("Vertex 2 Invalid Input");
            return;
        }

        if(to == from) {
            output.setText("Cannot add edge from itself to itself");
            return;
        }

        if(graph.GetEdge(from.Index(), to.Index()) == null) {
            Edge e = new Edge(from.Index(), to.Index());
            graph.AddEdge(e);
            mp.drawEdge(e);
        } else {
            output.setText("Edge already exists between the two verticies");
        }
    }

    public static void setOutput(String text) {
        output.setText(text);
    }
}