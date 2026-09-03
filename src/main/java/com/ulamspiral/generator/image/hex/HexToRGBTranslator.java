package com.ulamspiral.generator.image.hex;

import java.awt.*;

public final class HexToRGBTranslator {

    private HexToRGBTranslator() {}

    public static Color hexStringToRGB(String hex) {
        var withoutHash = hex.startsWith("#") ? hex.substring(1) : hex;
        if (withoutHash.length() != 6) {
            throw new HexConversionException("Invalid hex color: '" + hex + "'");
        }
        try {
            int r = Integer.parseInt(withoutHash.substring(0, 2), 16);
            int g = Integer.parseInt(withoutHash.substring(2, 4), 16);
            int b = Integer.parseInt(withoutHash.substring(4, 6), 16);
            return new Color(r, g, b);
        } catch (NumberFormatException e) {
            throw new HexConversionException("Invalid hex color: '" + hex + "'", e);
        }
    }
}
