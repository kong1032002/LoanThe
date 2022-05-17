package com.LT.Model.Items;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pointer extends Item {


    public Pointer(int col, int row) {
        this.col = col;
        this.row = row;
        y = row * 34;
        x = col * 34;
    }

    @Override
    public void changeStatus() {
        selected = !selected;
    }

    @Override
    public void move(String keyCode) {
        super.move(keyCode);
        row = (row < 0) ? 7 : (row > 7) ? 0 : row;
        col = (col < 0) ? 7 : (col > 7) ? 0 : col;
        if (keyCode.equals("SPACE")) {
            changeStatus();
        }
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        if (!selected) {
            graphicsContext.setFill(Color.AQUA);
        } else {
            graphicsContext.setFill(Color.CORAL);
        }
        graphicsContext.fillRect(x, 7 * 34 - y, 34, 34);
    }

    @Override
    public boolean update() {
        if (row * 34 == y && col * 34 == x) {
            return false;
        }
        if (row * 34 < y) {
            y = (y - 10);
        } else if (row * 34 > y) {
            y = (y + 10);
        }
        if (col * 34 < x) {
            x = (x - 10);
        } else if (col * 34 > x) {
            x = (x + 10);
        }
        x = x - ((x % 34) < 10 ? x % 34 : 0);
        y = y - ((y % 34) < 10 ? y % 34 : 0);
        return true;
    }
}
