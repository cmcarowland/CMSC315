/*
 * Raymond Rowland
 * Project 4
 * 10/6/24
 * 
 * The MouseClickedInterface defines a contract for handling mouse click 
 * events in a JavaFX application. It declares a single method, execute(), 
 * which takes a MouseEvent as a parameter. Implementing this interface 
 * allows for customizable responses to mouse click actions in GUI components.
 */


package com.project4;

import javafx.scene.input.MouseEvent;

public interface MouseClickedInterface {
    void execute(MouseEvent me);
}
