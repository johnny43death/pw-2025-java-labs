package pl.edu.pw.elka.prm2t.lab4;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Utility class to hold a method for image reading.
 */
public class ImageTools {

    /**
     * Reads the image from a file.
     * @param pathName the path to the image file.
     * @return two-dimensional array of pixels stored as {@code int}s.
     * @throws IOException if occurred during reading.
     */
    public static int[][] readImage(String pathName) throws IOException {
        final BufferedImage img = ImageIO.read(new File(pathName));
        final var width = img.getWidth();
        final var height = img.getHeight();
        final int[] pixels1d = img.getRaster().getPixels(0, 0, width, height, (int[]) null);

        // zamiana z tablicy 1d na 2d
        int[][] pixels = new int[height][width];
        for (var i = 0; i < height; i++) {
            System.arraycopy(pixels1d, i * width, pixels[i], 0, width);
        }
        return pixels;
    }
}
