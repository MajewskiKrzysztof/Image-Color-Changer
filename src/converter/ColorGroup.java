package converter;

import java.awt.*;
import java.math.BigDecimal;

public class ColorGroup {

    private Color center;

    private BigDecimal redSum;
    private BigDecimal greenSum;
    private BigDecimal blueSum;

    private int colorsCount;

    public ColorGroup() {
        this.center = new Color(0, 0, 0);
        clear();
    }

    public void add(Color color) {
        redSum = redSum.add(new BigDecimal(color.getRed()));
        greenSum = greenSum.add(new BigDecimal(color.getGreen()));
        blueSum = blueSum.add(new BigDecimal(color.getBlue()));
        colorsCount++;
        calculateCenter();
    }

    public void calculateCenter() {
        if (colorsCount == 0)
            return;

        center = new Color((int) (redSum.doubleValue() / colorsCount),
                (int) (greenSum.doubleValue() / colorsCount),
                (int) (blueSum.doubleValue() / colorsCount));
    }

    public void clear() {
        colorsCount = 0;

        redSum = new BigDecimal(0);
        greenSum = new BigDecimal(0);
        blueSum = new BigDecimal(0);
    }

    public Color getCenter() {
        return center;
    }
}