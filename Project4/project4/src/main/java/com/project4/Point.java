/*
 * Raymond Rowland
 * Project 2
 * 9/7/24
 * 
 * Point Class
 * This class represents a point in a 2D space with x and y coordinates. 
 * It includes functionality for comparing points based on their x values, 
 * storing and retrieving a visual representation (Circle), 
 * and determining if the point is located below and to the left of another point. 
 * The class also has a boolean flag (isMaximal) to indicate if the point is considered maximal.
 */

package com.project4;

import javafx.scene.shape.Circle;

public class Point implements Comparable<Point> {

    private double x;
    private double y;
    private Circle dot;
    
    public boolean isMaximal;

    public Point(double _x, double _y) {
        x = _x;
        y = _y;
        isMaximal = true;
    }
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setDot(Circle d) {
        dot = d;
    }

    public Circle getDot() {
        return dot;
    }

    public boolean isBelowAndLeft(Point other) {
        if(other.getX() < x && other.getY() > y) {
            return true;
        }

        return false;
    }

    @Override
    public int compareTo(Point arg0) {
        if(x < arg0.getX())
            return -1;
        else if(x > arg0.getX())
            return 1;

        return 0;
    }
}
