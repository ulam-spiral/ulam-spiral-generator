package com.ulamspiral.generator.image;

import java.awt.*;

public final class PrimeMapToImageMapper {

    private PrimeMapToImageMapper() {}

    /**
     * Given boolean[][] input convert to Color[][] matrix where true values are mapped to primeColor and false to compositeColor
     * Requires a square input - primeMap.length is used for both dimensions
     */
    public static Color[][] createImage(boolean[][] primeMap, Color primeColor, Color compositeColor) {
        var result = new Color[primeMap.length][primeMap.length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = primeMap[i][j] ? primeColor : compositeColor;
            }
        }
        return result;
    }

    /**
     * Given boolean[][] input convert to Color[][] matrix. Applies local density color grading using lookup table
     */
    public static Color[][] createImageWithDensity(boolean[][] primeMap, Color primeColor,
                                            Color compositeColor, int densityRadius) {
        int height = primeMap.length;
        int width  = (height == 0) ? 0 : primeMap[0].length;

        var sat = new SummedAreaTable(primeMap);
        int[][] densityMap = new int[height][width];
        int maxDensity = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int n = sat.countTrueInWindow(i, j, densityRadius);
                densityMap[i][j] = n;
                if (n > maxDensity) maxDensity = n;
            }
        }

        Color[] lookupTable = new Color[maxDensity + 1];
        for (int d = 0; d <= maxDensity; d++) {
            double fraction = (double) d / maxDensity;
            lookupTable[d] = ColorInterpolationUtil.interpolate(primeColor, compositeColor, 1 - fraction);
        }

        Color[][] result = new Color[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result[i][j] = lookupTable[densityMap[i][j]];
            }
        }
        return result;
    }
}
