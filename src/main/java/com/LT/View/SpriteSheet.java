package com.LT.View;

import javafx.scene.image.Image;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SpriteSheet {
    public static final SpriteSheet spriteSheet = new SpriteSheet("/com/LT/PNG/SpriteSheet");

    private List<Image> imageList = new ArrayList<>();

    public SpriteSheet(String dir) {
        URL url = getClass().getResource(dir);
        assert url != null;
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

    public Image getImage() {
        return imageList.get(0);
    }
}
