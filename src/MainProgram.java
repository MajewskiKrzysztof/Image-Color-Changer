import converter.Converter;
import util.Utils;
import util.FileHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class MainProgram {

    private Converter converter;
    private String imagePath;

    private BufferedImage image;
    private int groupCount;
    private Set<Color> colors;
    private ArrayList<Color> centers;

    public static void main(String[] args) {
        MainProgram mainProgram = new MainProgram("C:\\Users\\Krzysztof\\Desktop\\image.jpg", 16);
        mainProgram.run();
    }

    public MainProgram(String imagePath, int groupCount) {
        this.imagePath = imagePath;
        this.groupCount = groupCount;
        colors = new HashSet<>();
    }

    public void run() {
        readImage();
        convertImageToPixelColors();
        initializeConverter();
        centers = converter.getCenters();
        transformImage();
    }

    private void readImage() {
        image = FileHandler.readImage(imagePath);
    }

    private void convertImageToPixelColors() {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color c = new Color(image.getRGB(i, j), true);
                colors.add(c);
            }
        }
    }

    private void initializeConverter() {
        ArrayList<Color> list = new ArrayList<>();
        list.addAll(colors);
        converter = new Converter(groupCount, list);
    }

    private void transformImage() {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color color = new Color(image.getRGB(i, j), true);
                ArrayList<Double> distances = new ArrayList<>();
                for (Color center : centers)
                    distances.add(Utils.getColorDistance(color, center));
                int minIndex = distances.indexOf(Collections.min(distances));
                Color newColor = centers.get(minIndex);
                image.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public void saveNewImage(String path) {
        FileHandler.saveBufferedImage(image, path);

        System.out.println("New image saved");
    }
}