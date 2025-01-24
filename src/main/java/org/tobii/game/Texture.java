package org.tobii.game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Texture {
    public static Texture wood = new Texture("src/main/resources/wood.png", 16);
    public static Texture brick = new Texture("src/main/resources/brick.png", 16);

    public int[] pixels;
    private String loc;
    public final int SIZE;

    public Texture(String loc, int size) {
        this.loc = loc;
        SIZE = size;
        pixels = new int[SIZE*SIZE];
        load();
    }

    private void load() {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(loc));
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            bufferedImage.getRGB(0,0,width,height,pixels,0,width);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
