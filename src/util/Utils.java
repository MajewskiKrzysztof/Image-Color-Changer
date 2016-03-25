package util;

import javax.swing.*;
import java.awt.*;

public class Utils {

    public static double getColorDistance(Color c1, Color c2) {
        int r = (c1.getRed() - c2.getRed()) * (c1.getRed() - c2.getRed());
        int g = (c1.getGreen() - c2.getGreen()) * (c1.getGreen() - c2.getGreen());
        int b = (c1.getBlue() - c2.getBlue()) * (c1.getBlue() - c2.getBlue());

        return Math.sqrt(r + g + b);
    }


    public static void showAlertDialog(String message) {
        JOptionPane.showMessageDialog(null,
                message,
                "WARNING",
                JOptionPane.WARNING_MESSAGE);
    }
}