package com.LT.Controller;

import com.LT.Model.Items.Item;
import com.LT.Model.Items.Pointer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Grid {
    private final Item[][] itemsGrid = new Item[10][10];
    private final Pointer pointer = new Pointer(0, 0);

    public Grid() {
        for (int j = 0; j < 8; j++) {
            itemsGrid[8][j] = RandomItem.nextItem(j);
        }
    }

    public void update() {
        pointer.update();
        if (!drop()) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                        mark(i, j);
                }
            }
        }
    }

    public void render(GraphicsContext graphicsContext) {
        pointer.render(graphicsContext);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isNullItem(i, j)) {
                    continue;
                }
                if (itemsGrid[i][j].isSelected()) {
                    graphicsContext.setFill(Color.AZURE);
                    graphicsContext.fillRect(itemsGrid[i][j].getX(), 7 * 34 - itemsGrid[i][j].getY(), 34, 34);
                }
                itemsGrid[i][j].render(graphicsContext);
            }
        }
    }

    public boolean drop() {
        boolean isDrop = false;
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isNullItem(i, j)) {
                    if (i == 8) {
                        itemsGrid[i][j] = RandomItem.nextItem(j);
                        continue;
                    } else if (itemsGrid[i + 1][j] != null) {
                        itemsGrid[i][j] = itemsGrid[i + 1][j];
                        itemsGrid[i + 1][j] = null;
                        itemsGrid[i][j].setRow(i);
                        itemsGrid[i][j].update();
                    }
                    isDrop = true;
                } else {
                    if (itemsGrid[i][j].update()) {
                        isDrop = true;
                    }
                }
            }
        }
        return isDrop;
    }
    
    public void mark(int row, int col) {
        if (isNullItem(row,col)) {
            return;
        }
        boolean mRow = matchRow(row, col);
        boolean mCol = matchCol(row, col);
        if (mRow || mCol) {
            itemsGrid[row][col].changeStatus();
        }
    }

    public boolean matchRow(int row, int col) {
        if (col > 0 && col < 7
                && !isNullItem(row, col + 1)
                && !isNullItem(row, col - 1)
                && itemsGrid[row][col].getClass() == itemsGrid[row][col + 1].getClass()
                && itemsGrid[row][col].getClass() == itemsGrid[row][col - 1].getClass()) {
            for (int i = col + 1; i < 8 && !isNullItem(row, i)
                    && itemsGrid[row][i].getClass() == itemsGrid[row][col].getClass(); i++) {
                itemsGrid[row][i].changeStatus();
            }
            for (int i = col - 1;  i >= 0 && !isNullItem(row, i)
                    && itemsGrid[row][i].getClass() == itemsGrid[row][col].getClass(); i--) {
                itemsGrid[row][i].changeStatus();
            }
            return true;
        }
        return false;
    }

    public boolean matchCol(int row, int col) {
        if (row > 0 && row < 7
                && !isNullItem(row - 1, col)
                && !isNullItem(row + 1, col)
                && itemsGrid[row][col].getClass() == itemsGrid[row - 1][col].getClass()
                && itemsGrid[row][col].getClass() == itemsGrid[row + 1][col].getClass()) {
            if (itemsGrid[row][col].getClass() == itemsGrid[row - 1][col].getClass()
                    && itemsGrid[row][col].getClass() == itemsGrid[row - 1][col].getClass()) {
                for (int i = row + 1; i < 8 && !isNullItem(i, col)
                        && itemsGrid[i][col].getClass() == itemsGrid[row][col].getClass(); i++) {
                    itemsGrid[i][col].changeStatus();
                }
                for (int i = row - 1; i >= 0 && !isNullItem(i, col)
                        && itemsGrid[i][col].getClass() == itemsGrid[row][col].getClass(); i--) {
                    itemsGrid[i][col].changeStatus();
                }
            }
            return true;
        }
        return false;
    }

    public void swap(int row, int col, String dir) {
        switch (dir) {
            case "DOWN" -> {
                if (row > 0) {
                    swapVertical(row, col);
                }
            }
            case "UP" -> {
                if (row < 7) {
                    swapVertical(row + 1, col);
                }
            }
            case "LEFT" -> {
                if (col > 0) {
                    swapHorizontal(row, col);
                }
            }
            case "RIGHT" -> {
                if (col < 7) {
                    swapHorizontal(row, col + 1);
                }
            }
        }
        getPointer().changeStatus();
    }

    public void swapHorizontal(int row, int col) {
        Item tmp = itemsGrid[row][col].clone();
        itemsGrid[row][col] = itemsGrid[row][col - 1];
        itemsGrid[row][col - 1] = tmp;
        itemsGrid[row][col].move("RIGHT");
        itemsGrid[row][col - 1].move("LEFT");
    }

    public void swapVertical(int row, int col) {
        Item tmp = itemsGrid[row][col].clone();
        itemsGrid[row][col] = itemsGrid[row - 1][col];
        itemsGrid[row - 1][col] = tmp;
        itemsGrid[row][col].move("UP");
        itemsGrid[row - 1][col].move("DOWN");
    }

    public boolean isNullItem(int row, int col) {
        return (itemsGrid[row][col] == null);
    }
}
