package com.LT.Controller;

import com.LT.Model.Items.Item;
import com.LT.Model.Items.Pointer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class HandleKeyEvent {
    private Grid grid;

    public HandleKeyEvent(Grid grid) {
        this.grid = grid;
    }

    public void handle(KeyEvent event) {
        KeyCode code = event.getCode();
        if (grid.getPointer().isSelected()) {
            grid.swap(grid.getPointer().getRow(), grid.getPointer().getCol(), code.toString());
        } else {
            grid.getPointer().move(code.toString());
        }
    }
}
