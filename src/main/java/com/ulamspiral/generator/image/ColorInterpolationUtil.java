package com.ulamspiral.generator.image;

import java.awt.*;

public final class ColorInterpolationUtil {

    private ColorInterpolationUtil(){}

    /**
     * linear interpolation of two colors in sRGB space.
     * color1 at fraction=0 and color2 at fraction=1
     * alpha discarded
     */
    public static Color interpolate(Color color1, Color color2, double fraction) {
        fraction = Math.max(0, Math.min(1, fraction));

        int red = (int) (color1.getRed() + fraction * (color2.getRed() - color1.getRed()));
        int green = (int) (color1.getGreen() + fraction * (color2.getGreen() - color1.getGreen()));
        int blue = (int) (color1.getBlue() + fraction * (color2.getBlue() - color1.getBlue()));

        return new Color(red, green, blue);
    }

}
