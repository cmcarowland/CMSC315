package com.project4;

public class Vertex {
    public static int INVALID_VERTEX = -1;

    private int x;
    private int y;
    private int index;

    public Vertex(int _x, int _y, int _index) {
        x = _x;
        y = _y;
        index = _index;
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

    public char GetName() {
        if(index == INVALID_VERTEX)
            return 0;

        return (char)(0x41 + index);
    }

    public void SetIndex(int i) {
        index = i;
    }
}
