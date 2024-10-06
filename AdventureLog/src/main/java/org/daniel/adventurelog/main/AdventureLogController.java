package org.daniel.adventurelog.main;

import javafx.scene.paint.Color;

public class AdventureLogController {
    public static Color themeColor = Color.web("#6533d1");

    public static String toHex(Color color) {
        // Get red, green, blue, and opacity values as integers (0-255)
        int red = (int) (color.getRed() * 255);
        int green = (int) (color.getGreen() * 255);
        int blue = (int) (color.getBlue() * 255);
        int opacity = (int) (color.getOpacity() * 255);

        // Return hex string in the format #RRGGBBAA
        return String.format("#%02X%02X%02X%02X", red, green, blue, opacity);
    }
}