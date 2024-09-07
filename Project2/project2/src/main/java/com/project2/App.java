package com.project2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Point[] points;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(new MyPane(points), 500, 500);
        stage.setTitle("Raymond Rowland Project 2");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        try {
            String[] items = null;
            List<String> fileLines = Files.readAllLines(Paths.get("/Volumes/Hard Drive/Documents/College/CMSC315/Project2/points.txt"));
            points = new Point[fileLines.size()];
            for(int i = 0; i < fileLines.size(); i++) {
                items = fileLines.get(i).split(" ");
                points[i] = new Point(Double.parseDouble(items[0]), Double.parseDouble(items[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        launch();
    }

}