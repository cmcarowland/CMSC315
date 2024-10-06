/*
 * Raymond Rowland
 * Project 2
 * 9/7/24
 * 
 * App Class
 * The App class is the entry point for the JavaFX application. 
 * It initializes the application by loading a set of points from a file and 
 * creates a Scene containing a MyPane instance populated with these points. 
 * The start method sets up the primary stage, configures its title, and displays the scene. 
 * The main method reads point data from a file, parses it into Point objects, and launches the application.
 */

package com.project4;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.function.BiFunction;
import java.util.function.Function;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Point[] points = new Point[0];
    public static Graph graph;
    private static TextField tf;

    @Override
    public void start(Stage stage) throws IOException {
        MyPane mp = new MyPane(points);
        mp.getChildren().add(createButton("Add Edge", 150, 15, this::Bye));
        mp.getChildren().add(createButton("Is Connected?", 25, 400, this::OnConnectedClicked));
        mp.getChildren().add(createButton("Has Cycles?", 125, 400, this::Bye));
        mp.getChildren().add(createButton("Depth First Search", 220, 400, this::Bye));
        mp.getChildren().add(createButton("Breadth First Search", 350, 400, this::Bye));
        tf = new TextField();
        tf.setLayoutX(25);
        tf.setLayoutY(450);
        tf.setMinWidth(450);
        mp.getChildren().add(tf);

        scene = new Scene(mp, 500, 500);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.setTitle("Raymond Rowland Project 4");
        stage.setScene(scene);
        stage.show();
        graph.AddNode(new Vertex(200, 200));
        graph.AddNode(new Vertex(220,220));
        graph.AddEdge(new Edge(0, 1));
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

    void OnConnectedClicked(MouseEvent me) {
        GraphSearch gs = new GraphConnection(graph, 0, 0);
        System.out.println(gs.Result());
        tf.setText(gs.Result() ? "This graph is connected" : "This graph is not connected");
    }

    void Bye(MouseEvent me) {
        System.out.println("Bye");
    }
}