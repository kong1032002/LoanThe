package com.LT.Controller;

import com.LT.Model.Items.Item;
import com.LT.Model.Items.Pointer;
import javafx.scene.canvas.GraphicsContext;

public class Grid {
    private Item[][] itemsGrid = new Item[10][10];
    Pointer pointer;

    public Grid() {
        pointer = new Pointer(0, 0);
        for (int j = 0; j < 8; j++) {
            itemsGrid[8][j] = RandomItem.nextItem(j);
        }
    }

    public void update() {
        boolean match = true;
        pointer.update();
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (itemsGrid[i][j] == null) {
                    if (i == 8) {
                        itemsGrid[i][j] = RandomItem.nextItem(j);
                    } else if (itemsGrid[i + 1][j] != null) {
//                        swap(i,j,"DOWN");
                        itemsGrid[i][j] = itemsGrid[i + 1][j];
                        itemsGrid[i + 1][j] = null;
                        itemsGrid[i][j].setRow(i);
                        itemsGrid[i][j].update();
                        match = false;
                    }
                } else {
                    if (itemsGrid[i][j].update()) {
                        match = false;
                    }
                }
            }
        }
        if (match) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                        match(i, j);
                }
            }
        }
    }

    public  void print() {
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (itemsGrid[i][j]!= null) {
                    System.out.print(1 + " ");
                } else {
                    System.out.print(0 + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void render(GraphicsContext graphicsContext) {
        pointer.render(graphicsContext);
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                if (itemsGrid[i][j] != null) {
                    itemsGrid[i][j].render(graphicsContext);
                }
            }
        }
    }

    public void match(int row, int col) {
        boolean matched = false;
        if (itemsGrid[row][col] == null) {
            return;
        }
        if (col > 0 && col < 8
                && itemsGrid[row][col + 1] != null
                && itemsGrid[row][col - 1] != null
                && itemsGrid[row][col].getClass() == itemsGrid[row][col + 1].getClass()
                && itemsGrid[row][col].getClass() == itemsGrid[row][col - 1].getClass()) {
            for (int i = col + 1; itemsGrid[row][i] != null
                    && itemsGrid[row][i].getClass() == itemsGrid[row][col].getClass() && i < 8; i++) {
                itemsGrid[row][i] = null;
            }
            for (int i = col - 1; itemsGrid[row][i] != null
                    && itemsGrid[row][i].getClass() == itemsGrid[row][col].getClass() && i > 0; i--) {
                itemsGrid[row][i] = null;
            }
            matched = true;
        }
        if (row > 0 && row < 8
                && itemsGrid[row - 1][col] != null
                && itemsGrid[row + 1][col] != null
                && itemsGrid[row][col].getClass() == itemsGrid[row - 1][col].getClass()
                && itemsGrid[row][col].getClass() == itemsGrid[row + 1][col].getClass()) {
            if (itemsGrid[row][col].getClass() == itemsGrid[row - 1][col].getClass()
                    && itemsGrid[row][col].getClass() == itemsGrid[row - 1][col].getClass()) {
                for (int i = row + 1; itemsGrid[i][col].getClass() == itemsGrid[row][col].getClass() && i < 8; i++) {
                    itemsGrid[i][col] = null;
                }
                for (int i = row - 1; itemsGrid[i][col].getClass() == itemsGrid[row][col].getClass() && i > 0; i--) {
                    itemsGrid[i][col] = null;
                }
            }
            matched = true;
        }
        if (matched) {
            itemsGrid[row][col] = null;
        }
    }

    public Pointer getPointer() {
        return pointer;
    }

    public void swap(int row, int col, String dir) {
        Item tmp = itemsGrid[row][col].clone();
        switch (dir) {
            case "DOWN" -> {
                if (row == 0) {
                    break;
                }
                itemsGrid[row][col] = itemsGrid[row - 1][col];
                itemsGrid[row - 1][col] = tmp;
                itemsGrid[row][col].move("UP");
                itemsGrid[row - 1][col].move("DOWN");
            }
            case "UP" -> {
                if (row == 7) {
                    break;
                }
                itemsGrid[row][col] = itemsGrid[row + 1][col];
                itemsGrid[row + 1][col] = tmp;
                itemsGrid[row][col].move("DOWN");
                itemsGrid[row + 1][col].move("UP");
            }
            case "LEFT" -> {
                if (col == 0) {
                    break;
                }
                itemsGrid[row][col] = itemsGrid[row][col - 1];
                itemsGrid[row][col - 1] = tmp;
                itemsGrid[row][col].move("RIGHT");
                itemsGrid[row][col - 1].move("LEFT");
            }
            case "RIGHT" -> {
                if (col == 7) {
                    break;
                }
                itemsGrid[row][col] = itemsGrid[row][col + 1];
                itemsGrid[row][col + 1] = tmp;
                itemsGrid[row][col].move("LEFT");
                itemsGrid[row][col + 1].move("RIGHT");
            }
        }
        getPointer().changeStatus();
    }
}
