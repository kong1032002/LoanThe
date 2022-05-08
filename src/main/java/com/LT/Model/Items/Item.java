package com.LT.Model.Items;

import com.LT.View.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public abstract class Item implements Cloneable{
    private Sprite sprite;
    protected double x;
    protected double y;
    protected int row;
    protected int col;
    private int animate = 0;
    private int index = 0;

    public Item() {
    }

    public Item(Sprite sprite, int col) {
        this.sprite = sprite;
        this.col = col;
        row = 8;
        y = row * 34;
        x = col * 34;
    }

    public boolean update() {
        animate++;
        if (animate % 10 == 0) {
            index = (index + 1) % sprite.getImageList().size();
        }
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

    public void move(String dir) {
        switch (dir) {
            case "UP" -> row++;
            case "DOWN" -> row--;
            case "LEFT" -> col--;
            case "RIGHT" -> col++;
        }
    }

    public Image getFxImage() {
        return sprite.getImageList().get(index);
    }

    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(getFxImage(), x, 8 * 34 - y);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getAnimate() {
        return animate;
    }

    public void setAnimate(int animate) {
        this.animate = animate;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public Item clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (Item) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
