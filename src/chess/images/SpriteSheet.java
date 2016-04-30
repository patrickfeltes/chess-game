package chess.images;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage spriteSheet;
    private Sprite[] sprites;

    private int numCols, numRows, spriteWidth, spriteHeight;

    /**
     * SpriteSheet constructor, assumes that all sprites are same size
     * @param path the path of the specified sprite sheet
     * @param numRows the number of rows in the sheet
     * @param numCols the number of columns in the sheet
     */
    public SpriteSheet(String path, int numRows, int numCols) {
        spriteSheet = ImageLoader.loadImage(path);
        this.numCols = numCols;
        this.numRows = numRows;
        this.spriteWidth = spriteSheet.getWidth() / numCols;
        this.spriteHeight = spriteSheet.getHeight() / numRows;
        sprites = new Sprite[numRows * numCols];
        splitUpSheet();
    }

    /**
     * Method to split up the sheet and store the Sprites in the sprites array
     */
    private void splitUpSheet() {
        for(int r = 0; r < numRows; r++) {
            for(int c = 0; c < numCols; c++) {
                int index = r * numCols + c;
                sprites[index] = new Sprite(spriteSheet.getSubimage(c * spriteWidth, r * spriteHeight, spriteWidth, spriteHeight));
            }
        }
    }

    public Sprite[] getSprites() {
        return sprites;
    }

}
