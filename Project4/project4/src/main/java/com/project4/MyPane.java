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

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class MyPane extends Pane {

    private LinkedList<Point> points; 

    public MyPane (Point[] inPoints) {
        BackgroundFill bgf = new BackgroundFill(Color.valueOf("#202020"), null, getInsets());
        Background bg = new Background(bgf);
        setBackground(bg);
        setOnMouseClicked(event -> reactToMouseClick(event));
        points = new LinkedList<Point>();
        for(var p : inPoints)
            points.add(p);

        redraw();
    }

    private void reactToMouseClick(MouseEvent e) {
        if(e.getButton() == MouseButton.PRIMARY) {
            points.add(new Point(e.getX(), e.getY()));
            App.graph.AddNode(new Vertex((int)e.getX(), (int)e.getY()));
            redraw();
        }
    }

    private void removeClick(MouseEvent e) {
        if(e.getButton() == MouseButton.SECONDARY) {
            if(e.getSource() instanceof Circle) {
                Circle source = (Circle)e.getSource();
                points.removeIf(x -> x.getDot() == source);
                redraw();
            }
        }
    }

    //O(n * n)
    private void computeMaximal() {
        Point currentPoint = null;
        Point otherPoint = null;
        for(int i = 0; i < points.size(); i++) {
            currentPoint = points.get(i);
            if(!currentPoint.isMaximal)
                continue;
            for(int j = 0; j < points.size(); j++) {
                if(i == j)
                    continue;

                otherPoint = points.get(j);
                if(otherPoint.isMaximal && currentPoint.isBelowAndLeft(otherPoint)) {
                    otherPoint.isMaximal = false;
                }
            }
        }
    }

    private void redraw() {
        for(var c : getChildren()) {
            
        }
        Vertex currentPoint = null;        
        // for(int i = 0; i < max.size(); i++) {
        //     currentPoint = max.get(i);
        //     if(i == 0) {
        //         Line line = new Line(currentPoint.getX(), 500, currentPoint.getX(), currentPoint.getY());
        //         line.setStroke(Color.RED);
        //         getChildren().add(line);
        //         if(i + 1 < max.size() - 1) {
                    
        //         }
        //     } 
            
        //     if(i == max.size() - 1) {
        //         Line line = new Line(0, currentPoint.getY(), currentPoint.getX(), currentPoint.getY());
        //         line.setStroke(Color.RED);
        //         getChildren().add(line);
        //         continue;
        //     }
            
        //     if(i < max.size() - 1) {
        //         lastPoint = max.get(i + 1);
        //         Line line = new Line(currentPoint.getX(), currentPoint.getY(), lastPoint.getX(), currentPoint.getY());
        //         line.setStroke(Color.RED);
        //         getChildren().add(line);
        //         line = new Line(lastPoint.getX(), lastPoint.getY(), lastPoint.getX(), currentPoint.getY());
        //         line.setStroke(Color.RED);
        //         getChildren().add(line);
        //     }
        // }
        
        for(int i = 0; i < App.graph.nodes.size(); i++) {
            currentPoint = App.graph.nodes.get(i);
            Circle dot = new Circle(currentPoint.X(), currentPoint.Y(), 5, Color.LIGHTBLUE);
            
            getChildren().add(dot);
        }

        for(int i = 0; i < App.graph.edges.size(); i++) {
            for(int j = 0; j < App.graph.edges.get(i).size(); j++) {
                Vertex f = App.graph.GetNode(App.graph.edges.get(i).get(j).From());
                Vertex t = App.graph.GetNode(App.graph.edges.get(i).get(j).To());
                Line line = new Line(f.X(), f.Y(), t.X(), t.Y());
                line.setStroke(Color.RED);
                getChildren().add(line);                
            }
        }
    }
}
