package com.project2;

public class Point implements Comparable<Point> {

    private double x;
    private double y;

    public Point(double _x, double _y) {
        x = _x;
        y = _y;
    }
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
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
