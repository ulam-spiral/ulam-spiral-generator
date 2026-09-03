package com.ulamspiral.generator.examples;

import com.ulamspiral.generator.image.ImageExporter;
import com.ulamspiral.generator.image.PrimeMapToImageMapper;
import com.ulamspiral.generator.prime.PrimeSieve;
import com.ulamspiral.generator.prime.SpiralMapper;
import com.ulamspiral.generator.prime.UlamSpiralCalculator;
import com.ulamspiral.generator.prime.check.PrimeArrayPrimeChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import java.io.File;

/**
 * Marks twin primes (at distance=2) then colors the result by local density
 */
public class TwinPrimeDensitySpiralExample {

    private static final Logger log = LoggerFactory.getLogger(TwinPrimeDensitySpiralExample.class);

    private static final int SIZE = 501;
    private static final int DISTANCE = 2;
    private static final int DENSITY_RADIUS = 5;

    public static void main(String[] args) throws Exception {
        long[][] spiral = UlamSpiralCalculator.calculateUlamSpiral(SIZE);

        long sieveLimit = (long) SIZE * SIZE + DISTANCE + 1;
        long[] primes = PrimeSieve.primesUpTo(sieveLimit);
        var primeChecker = new PrimeArrayPrimeChecker(primes, sieveLimit);
        boolean[][] gapMap = new SpiralMapper(primeChecker).toPrimeMapWithDistance(spiral, DISTANCE);

        var pixels = PrimeMapToImageMapper.createImageWithDensity(
                gapMap, new Color(0xff, 0xc3, 0x00), new Color(0x0a, 0x0a, 0x14), DENSITY_RADIUS);

        File outputFile = ExampleOutput.resolve("twin-prime-density-spiral.png");
        ImageExporter.exportImage(pixels, outputFile);
        log.info("wrote {}", outputFile.getAbsolutePath());
    }
}
