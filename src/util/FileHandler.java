package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class FileHandler {

    public static BufferedImage readImage(String imagePath) {
        File imageFile = new File(imagePath);
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageFile);
        } catch (Exception e) {
            Utils.showAlertDialog("Error reading file.");
        }

        return image;
    }

    public static void saveBufferedImage(BufferedImage image, String path) {
        try {
            File outfile = new File(path);
            ImageIO.write(image, "png", outfile);
        } catch (Exception e) {
            Utils.showAlertDialog("Error saving file. Try changing directory");
        }
    }
}