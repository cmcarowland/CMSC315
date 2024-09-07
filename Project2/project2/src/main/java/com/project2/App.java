package com.project2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static LinkedList<Point> points = new LinkedList<Point>();

    @FXML
    private static MyPane pane;

    @Override
    public void start(Stage stage) throws IOException {
        pane = loadFXML("primary");
        scene = new Scene(pane , 500, 500);
        scene.setOnMouseClicked(event -> onMouseClick(event));
        stage.setTitle("Raymond Rowland Project 2");
        stage.setScene(scene);
        stage.show();
    }

    public void onMouseClick(MouseEvent e) {
        if(e.getButton() == MouseButton.PRIMARY) {
            points.add(new Point(e.getX(), e.getY()));
            redraw();
        }
        
    }

    public void redraw() {
        pane.getChildren().clear(); 
        for(int i = 0; i < points.size(); i++) {
            Circle dot = new Circle(points.get(i).getX(), points.get(i).getY(), 10, Color.BLACK);
            pane.getChildren().add(dot);
            if(i > 0) {
                Line line = new Line(points.get(i - 1).getX(), points.get(i - 1).getY(), points.get(i).getX(), points.get(i).getY());
                pane.getChildren().add(line);
            }
        }
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static MyPane loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.<MyPane>load();
    }

    public static void main(String[] args) {
        launch();
    }

}