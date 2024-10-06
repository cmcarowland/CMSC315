/*
 * Raymond Rowland
 * Project 4
 * 10/6/24
 * 
 *
 * The MyPane class extends Pane to create a custom drawing area for a 
 * JavaFX application. It sets a dark background and handles mouse click 
 * events to add vertices to the graph. When a vertex is added, it 
 * visually represents it as a yellow circle with a label. The class also 
 * provides a method to draw edges between vertices, represented as white 
 * lines connecting the corresponding vertex positions in the pane.
 */

package com.project4;

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
        setLayoutY(50);
        setPrefWidth(500);
        setPrefHeight(365);
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
        System.out.println(v.X() + " " + v.Y());
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
