package com.LT.View;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Sprite {
    private static final int SkillEffect = 128;

    // Skill Effect
    public static Sprite CircleExplosion = new Sprite("/com/LT/PNG/Circle_explosion", SkillEffect);
    public static Sprite Body = new Sprite("/com/LT/PNG/Body");
    public static Sprite Explosion = new Sprite("/com/LT/PNG/Explosion", SkillEffect);
    public static Sprite ExplosionBlueCircle = new Sprite("/com/LT/PNG/Explosion_blue_circle", SkillEffect);
    public static Sprite ExplosionBlueOval = new Sprite("/com/LT/PNG/Explosion_blue_oval", SkillEffect);
    public static Sprite ExplosionGas = new Sprite("/com/LT/PNG/Explosion_gas", SkillEffect);
    public static Sprite ExplosionGasCircle = new Sprite("/com/LT/PNG/Explosion_gas_circle", SkillEffect);
    public static Sprite ExplosionTwoColor = new Sprite("/com/LT/PNG/Explosion_two_colors", SkillEffect);
    public static Sprite Fire = new Sprite("/com/LT/PNG/Fire", SkillEffect);
    public static Sprite Icons = new Sprite("/com/LT/PNG/Icons", SkillEffect);
    public static Sprite Lightning = new Sprite("/com/LT/PNG/Lightning", SkillEffect);
    public static Sprite NuclearExplosion = new Sprite("/com/LT/PNG/Nuclear_explosion", SkillEffect);
    public static Sprite Smoke = new Sprite("/com/LT/PNG/Smoke", SkillEffect);

    // Character Animation

    // Item
    public static Sprite Sword = new Sprite(SpriteSheet.spriteSheet, 6, 1, 34);
    public static Sprite Shield = new Sprite(SpriteSheet.spriteSheet, 13, 1, 34);
    public static Sprite HealingPotion = new Sprite(SpriteSheet.spriteSheet, 4, 8, 34);
    public static Sprite ManaPotion = new Sprite(SpriteSheet.spriteSheet, 4, 11, 34);
    public static Sprite Coin = new Sprite(SpriteSheet.spriteSheet, 16, 11, 34);
    public static Sprite Food = new Sprite(SpriteSheet.spriteSheet, 1, 8, 34);
    public static Sprite Exp = new Sprite(SpriteSheet.spriteSheet, 17, 5, 34);
    public static Sprite Fury = new Sprite(SpriteSheet.spriteSheet, 21, 6, 34);
    //
    private final List<Image> imageList = new ArrayList<>();

    private Sprite(String dir) {
        URL url = getClass().getResource(dir);
        Path path = Paths.get(url.getPath().substring(1));
        try {
            Stream<Path> subPath = Files.walk(path);
            subPath.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    imageList.add(new Image(filePath.toString()));
                    System.out.println(filePath);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Sprite(String dir, int size) {
        URL url = getClass().getResource(dir);
        Path path = Paths.get(url.getPath().substring(1));
        try {
            Stream<Path> subPath = Files.walk(path);
            subPath.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    Image image = new Image(filePath.toString(), size, size, true, false);
                    imageList.add(image);
                    System.out.println(filePath);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Sprite(SpriteSheet spriteSheet, int row, int col, int size) {
        PixelReader pixelReader = spriteSheet.getImage().getPixelReader();
        imageList.add(new WritableImage(pixelReader, --col * size, --row * size, size, size));
    }

    private Sprite(SpriteSheet spriteSheet, int row, int col, int width, int height) {

        PixelReader pixelReader = spriteSheet.getImage().getPixelReader();
        imageList.add(new WritableImage(pixelReader, --col * width, --row * height, width, height));
    }

    public List<Image> getImageList() {
        return imageList;
    }
}