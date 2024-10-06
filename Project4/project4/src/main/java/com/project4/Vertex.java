package com.project4;

public class Vertex {
    public static int INVALID_VERTEX = -1;

    private int x;
    private int y;
    private int index;
    private String name;

    public Vertex(int _x, int _y, int _index) {
        x = _x;
        y = _y;
        SetIndex(_index);
    }

    public Vertex(int _x, int _y) {
        x = _x;
        y = _y;
        index = INVALID_VERTEX;
    }

    public int X() {
        return x;
    }

    public int Y() {
        return y;
    }

    public int Index() {
        return index;
    }

    public String GetName() {
        return name;
    }

    private String getName() {
        if(index == INVALID_VERTEX)
            return "";

        if(index < 26)
            return "" + (char)(0x41 + index);
        else {
            int rem = index % 26;
            return ("" + (char)(0x41 + rem)).repeat(index / 26 + 1);
        }
    }

    public void SetIndex(int i) {
        index = i;
        name = getName();
    }
}
