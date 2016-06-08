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
        image = ImageUtil.loadImage(path);
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

    /**
     * Method to draw the sprite centered at some square cell
     * @param g the graphics object being used to draw
     * @param topLeftX x coordinate of the top left corner of the cell
     * @param topLeftY y coordinate of the top left corner of the cell
     * @param cellSize the dimension of the cell, width and height
     */
    public void drawOnCenterOfCell(Graphics2D g, int topLeftX, int topLeftY, int cellSize) {
        draw(g, topLeftX + cellSize / 2 - getWidth() / 2, topLeftY + cellSize / 2 - getHeight() / 2);
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

}
