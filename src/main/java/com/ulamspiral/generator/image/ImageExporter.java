package com.ulamspiral.generator.image;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class ImageExporter {

    private ImageExporter() {}

    public static void exportImage(Color[][] pixelData, File file) throws IOException {
        validateInput(pixelData);
        int size = pixelData.length;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        iterateOverMatrix(pixelData, size, image);
        ImageIO.write(image, "png", file);
    }

    private static void validateInput(Color[][] pixelData) {
        if (pixelData == null || pixelData.length == 0 || pixelData[0].length == 0) {
            throw new IllegalArgumentException("Empty pixel data");
        }
    }

    private static void iterateOverMatrix(Color[][] pixelData, int size, BufferedImage image) {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                image.setRGB(x, y, pixelData[y][x].getRGB());
            }
        }
    }
}
