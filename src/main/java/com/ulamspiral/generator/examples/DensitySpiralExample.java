package com.ulamspiral.generator.examples;

import com.ulamspiral.generator.image.ImageExporter;
import com.ulamspiral.generator.image.PrimeMapToImageMapper;
import com.ulamspiral.generator.image.hex.HexToRGBTranslator;
import com.ulamspiral.generator.prime.PrimeSieve;
import com.ulamspiral.generator.prime.SpiralMapper;
import com.ulamspiral.generator.prime.UlamSpiralCalculator;
import com.ulamspiral.generator.prime.check.PrimeArrayPrimeChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * A bigger spiral, colored by local prime density instead of a flat two-color map
 */
public class DensitySpiralExample {

    private static final Logger log = LoggerFactory.getLogger(DensitySpiralExample.class);

    private static final int SIZE = 501;
    private static final int DENSITY_RADIUS = 5;

    public static void main(String[] args) throws Exception {
        long[][] spiral = UlamSpiralCalculator.calculateUlamSpiral(SIZE);

        long sieveLimit = (long) SIZE * SIZE + 1;
        long[] primes = PrimeSieve.primesUpTo(sieveLimit);
        var primeChecker = new PrimeArrayPrimeChecker(primes, sieveLimit);
        boolean[][] primeMap = new SpiralMapper(primeChecker).toPrimeMap(spiral);

        var primeColor = HexToRGBTranslator.hexStringToRGB("#e94560");
        var compositeColor = HexToRGBTranslator.hexStringToRGB("#1a1a2e");

        var pixels = PrimeMapToImageMapper.createImageWithDensity(primeMap, primeColor, compositeColor, DENSITY_RADIUS);

        File outputFile = ExampleOutput.resolve("density-spiral.png");
        ImageExporter.exportImage(pixels, outputFile);
        log.info("wrote {}", outputFile.getAbsolutePath());
    }
}
