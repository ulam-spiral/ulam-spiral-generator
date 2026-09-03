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
 * The correct way to handle large spirals using prime sieve strategy
 */
public class LargeSpiralExample {

    private static final Logger log = LoggerFactory.getLogger(LargeSpiralExample.class);

    private static final int SIZE = 5001;

    public static void main(String[] args) throws Exception {
        long[][] spiral = UlamSpiralCalculator.calculateUlamSpiral(SIZE);

        long sieveLimit = (long) SIZE * SIZE + 1;
        long[] primes = PrimeSieve.primesUpTo(sieveLimit);
        var primeChecker = new PrimeArrayPrimeChecker(primes, sieveLimit);
        boolean[][] primeMap = new SpiralMapper(primeChecker).toPrimeMap(spiral);

        var pixels = PrimeMapToImageMapper.createImage(
                primeMap, new Color(0, 70, 255), new Color(0, 0, 0));

        File outputFile = ExampleOutput.resolve("large-spiral.png");
        ImageExporter.exportImage(pixels, outputFile);
        log.info("wrote {}", outputFile.getAbsolutePath());
    }
}
