package de.kruemmling.jar2app.javafx.jar2appapplered1408javafx;

import de.kruemmling.jar2app.javafx.jar2appapplered1408javafx.gui.GUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        new GUI(primaryStage); // Erstelle ein GUI-Objekt und übergebe die Stage
    }
}
