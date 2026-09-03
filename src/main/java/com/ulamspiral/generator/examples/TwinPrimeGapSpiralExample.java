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
 * Instead of marking plain primes, marks cells whose value n has another prime at n +/- distance.
 * Distance=2 marks the twin primes (3 & 5, 5 & 7, 11 & 13, ...).
 */
public class TwinPrimeGapSpiralExample {

    private static final Logger log = LoggerFactory.getLogger(TwinPrimeGapSpiralExample.class);

    private static final int SIZE = 301;
    private static final int DISTANCE = 2;

    public static void main(String[] args) throws Exception {
        long[][] spiral = UlamSpiralCalculator.calculateUlamSpiral(SIZE);

        // the distance lookup can probe up to DISTANCE past the largest spiral value, so sieve that far too
        long sieveLimit = (long) SIZE * SIZE + DISTANCE + 1;
        long[] primes = PrimeSieve.primesUpTo(sieveLimit);
        var primeChecker = new PrimeArrayPrimeChecker(primes, sieveLimit);
        boolean[][] gapMap = new SpiralMapper(primeChecker).toPrimeMapWithDistance(spiral, DISTANCE);

        var pixels = PrimeMapToImageMapper.createImage(
                gapMap, new Color(0xff, 0xd7, 0x00), new Color(0x0d, 0x0d, 0x0d));

        File outputFile = ExampleOutput.resolve("twin-prime-gap-spiral.png");
        ImageExporter.exportImage(pixels, outputFile);
        log.info("wrote {}", outputFile.getAbsolutePath());
    }
}
