package com.project2;
import java.util.LinkedList;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class MyPane extends Pane {

    private LinkedList<Point> points; 

    public MyPane (Point[] inPoints) {
        setOnMouseClicked(event -> reactToMouseClick(event));
        points = new LinkedList<Point>();
        for(var p : inPoints)
            points.add(p);

        redraw();
    }

    private void reactToMouseClick(MouseEvent e) {
        if(e.getButton() == MouseButton.PRIMARY) {
            points.add(new Point(e.getX(), e.getY()));
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

    private void redraw() {
        getChildren().clear(); 
        Point currentPoint = null;
        Point lastPoint = null;

        for(int i = 0; i < points.size(); i++) {
            lastPoint = currentPoint;
            currentPoint = points.get(i);
            Circle dot = new Circle(currentPoint.getX(), currentPoint.getY(), 10, Color.BLACK);
            dot.setOnMouseReleased(event -> removeClick(event));
            
            currentPoint.setDot(dot);
            getChildren().add(dot);
            
            if(lastPoint != null) {
                Line line = new Line(lastPoint.getX(), lastPoint.getY(), currentPoint.getX(), currentPoint.getY());
                getChildren().add(line);
            }
        }
    }
}
