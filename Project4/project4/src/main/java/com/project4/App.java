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
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Point[] points;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(new MyPane(points), 500, 500);
        stage.setTitle("Raymond Rowland Project 4");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}