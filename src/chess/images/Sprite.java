package chess.images;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {

    // information about Sprite
    private BufferedImage image;
    private int width, height;

    /**
     * Constructor for a Sprite
     * @param image BufferedImage for the sprite
     */
    public Sprite(BufferedImage image) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
    }

    /**
     * Constructor for a Sprite
     * @param path location of the file
     */
    public Sprite(String path) {
        image = ImageLoader.loadImage(path);
        width = image.getWidth();
        height = image.getHeight();
    }

    /**
     * Method to draw the sprite at a specified location
     * @param g Graphics2D object used to draw the sprite
     * @param x x position of the sprite
     * @param y y position of the sprite
     */
    public void draw(Graphics2D g, int x, int y) {
        g.drawImage(image, x, y, width, height, null);
    }

}
