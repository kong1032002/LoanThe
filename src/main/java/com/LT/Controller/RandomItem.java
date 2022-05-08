package com.LT.Controller;

import com.LT.Model.Items.*;

import java.util.Random;

public class RandomItem {
    public static Item nextItem(int col) {
        switch (new Random().nextInt(8)) {
            case 0 -> {
                return new Coin(col);
            }
            case 1 -> {
                return new Exp(col);
            }
            case 2 -> {
                return new Food(col);
            }
            case 3 -> {
                return new Fury(col);
            }
            case 4 -> {
                return new HealingPotion(col);
            }
            case 5 -> {
                return new ManaPotion(col);
            }
            case 6 -> {
                return new Shield(col);
            }
            case 7 -> {
                return new Sword(col);
            }
            default -> {
                return null;
            }
        }
    }
}
