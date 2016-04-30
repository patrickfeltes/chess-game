package chess.images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageLoader {

    /**
     * Method to load image
     * @param path Path of the image to be loaded
     * @return a BufferedImage object of the image
     */
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(ImageLoader.class.getResourceAsStream(path));
        } catch(Exception e) {
            System.out.println("Error loading image at path: " + path);
            return null;
        }
    }

}
