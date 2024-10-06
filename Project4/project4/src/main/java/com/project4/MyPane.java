/*
 * Raymond Rowland
 * Project 2
 * 9/7/24
 * 
 * MyPane Class
 * MyPane extends Pane to provide a custom graphical user interface component that manages and displays a collection of Point objects. 
 * It is initialized with an array of points and sets up a dark background. 
 * Users can add new points by clicking with the primary mouse button and 
 * remove existing points by clicking on their visual representation with the secondary mouse button. 
 * The pane computes and visualizes maximal points using lines and circles, updating the display whenever points are added or removed. 
 * It also supports interactive visual feedback by highlighting maximal points.
 */

package com.project4;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class MyPane extends Pane {
    public MyPane () {
        BackgroundFill bgf = new BackgroundFill(Color.valueOf("#202020"), null, getInsets());
        Background bg = new Background(bgf);
        setBackground(bg);
        setOnMouseClicked(event -> reactToMouseClick(event));
    }

    private void reactToMouseClick(MouseEvent e) {
        if(e.getButton() == MouseButton.PRIMARY) {
            Vertex v = new Vertex((int)e.getX(), (int)e.getY());
            App.graph.AddNode(v);
            drawNode(v);
        }
    }

    private void drawNode(Vertex v) {
        Circle dot = new Circle(v.X(), v.Y(), 5, Color.YELLOW);
        Label label = new Label(v.GetName() + "");
        label.setTextFill(Color.WHITE);
        label.setLayoutX(v.X() - 5);
        label.setLayoutY(v.Y() - 20);
        getChildren().add(dot);
        getChildren().add(label);
    }
    
    public void drawEdge(Edge e) {
        Vertex f = App.graph.GetNode(e.From());
        Vertex t = App.graph.GetNode(e.To());
        Line line = new Line(f.X(), f.Y(), t.X(), t.Y());
        line.setStroke(Color.WHITE);
        getChildren().add(line);
    }
}
