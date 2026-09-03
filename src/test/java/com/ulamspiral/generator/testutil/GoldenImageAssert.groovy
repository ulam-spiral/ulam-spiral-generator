package com.ulamspiral.generator.testutil

import javax.imageio.ImageIO
import java.awt.Color
import java.awt.image.BufferedImage

class GoldenImageAssert {

    static void assertPixelPerfect(File goldenFile, Color[][] actualPixelData) {
        assertPixelPerfect(ImageIO.read(goldenFile), toBufferedImage(actualPixelData))
    }

    static void assertPixelPerfect(BufferedImage golden, BufferedImage actual) {
        assert golden.getWidth() == actual.getWidth(): "width mismatch: golden=${golden.getWidth()} actual=${actual.getWidth()}"
        assert golden.getHeight() == actual.getHeight(): "height mismatch: golden=${golden.getHeight()} actual=${actual.getHeight()}"

        def mismatches = []
        for (int y = 0; y < golden.getHeight(); y++) {
            for (int x = 0; x < golden.getWidth(); x++) {
                int expectedRgb = golden.getRGB(x, y) & 0xFFFFFF
                int actualRgb = actual.getRGB(x, y) & 0xFFFFFF
                if (expectedRgb != actualRgb) {
                    mismatches << "($x,$y): golden=$expectedRgb actual=$actualRgb"
                }
            }
        }
        assert mismatches.isEmpty(): "found ${mismatches.size()} pixel mismatches (of ${golden.getWidth() * golden.getHeight()}), first few: ${mismatches.take(5)}"
    }

    private static BufferedImage toBufferedImage(Color[][] pixelData) {
        int height = pixelData.length
        int width = pixelData[0].length
        def image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                image.setRGB(x, y, pixelData[y][x].getRGB())
            }
        }
        return image
    }
}
