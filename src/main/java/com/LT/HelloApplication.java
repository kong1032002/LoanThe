package com.LT;

import com.LT.Controller.Grid;
import com.LT.Controller.HandleKeyEvent;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class HelloApplication extends Application {

    VBox vBox = new VBox();
    Canvas canvas = new Canvas(272, 272);
    GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
    Scene scene = new Scene(vBox, 960, 720);

    @Override
    public void start(Stage stage) {
        vBox.setAlignment(Pos.CENTER);
        AtomicReference<Grid> grid = new AtomicReference<>(new Grid());
        AnimationTimer animationTimer = new AnimationTimer() {
            long time;

            @Override
            public void handle(long l) {
//                if (l - time > 500000000) {
                render();
                grid.get().update();
                grid.get().render(graphicsContext);
                time = l;
                scene.setOnKeyPressed(e -> {
                    if (e.getCode() == KeyCode.A) {
                        grid.set(new Grid());
                    }
                    HandleKeyEvent handleKeyEvent = new HandleKeyEvent(grid.get());
                    handleKeyEvent.handle(e);
                });
            }
        };

        animationTimer.start();
        vBox.getChildren().add(canvas);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void render() {
        ;
        graphicsContext.clearRect(0, 0, scene.getWidth(), scene.getHeight());
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}