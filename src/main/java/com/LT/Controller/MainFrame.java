package com.LT.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class MainFrame {
    @FXML
    public Canvas canvas = new Canvas(272, 272);
    GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

    Scene scene;
    FXMLLoader fxmlLoader;



    public void update() {
        System.out.println(1);
    }

    public void handleKeyPress() {

    }
}
