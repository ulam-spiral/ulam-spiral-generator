package com.ulamspiral.generator.examples;

import com.ulamspiral.generator.image.ImageExporter;
import com.ulamspiral.generator.image.PrimeMapToImageMapper;
import com.ulamspiral.generator.prime.SpiralMapper;
import com.ulamspiral.generator.prime.UlamSpiralCalculator;
import com.ulamspiral.generator.prime.check.DefaultPrimeChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import java.io.File;

/**
 * Simple example, small spiral, naive prime calculation strategy
 */
public class BasicSpiralExample {

    private static final Logger log = LoggerFactory.getLogger(BasicSpiralExample.class);

    public static void main(String[] args) throws Exception {
        long[][] spiral = UlamSpiralCalculator.calculateUlamSpiral(201); // side length must be odd

        boolean[][] primeMap = new SpiralMapper(new DefaultPrimeChecker()).toPrimeMap(spiral);

        Color[][] pixels = PrimeMapToImageMapper.createImage(
                primeMap, new Color(0, 145, 255), new Color(0, 0, 0));

        File outputFile = ExampleOutput.resolve("basic-spiral.png");
        ImageExporter.exportImage(pixels, outputFile);
        log.info("wrote {}", outputFile.getAbsolutePath());
    }
}
