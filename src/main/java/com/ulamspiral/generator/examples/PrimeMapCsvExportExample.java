package com.ulamspiral.generator.examples;

import com.ulamspiral.generator.csv.BooleanMatrixCSVUtility;
import com.ulamspiral.generator.prime.PrimeSieve;
import com.ulamspiral.generator.prime.SpiralMapper;
import com.ulamspiral.generator.prime.UlamSpiralCalculator;
import com.ulamspiral.generator.prime.check.PrimeArrayPrimeChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;

/**
 * Generate a .csv representation of the spiral, primes mapped to 1s, composites to 0s
 */
public class PrimeMapCsvExportExample {

    private static final Logger log = LoggerFactory.getLogger(PrimeMapCsvExportExample.class);

    private static final int SIZE = 301;

    public static void main(String[] args) throws Exception {
        long[][] spiral = UlamSpiralCalculator.calculateUlamSpiral(SIZE);

        long sieveLimit = (long) SIZE * SIZE + 1;
        long[] primes = PrimeSieve.primesUpTo(sieveLimit);
        var primeChecker = new PrimeArrayPrimeChecker(primes, sieveLimit);
        boolean[][] primeMap = new SpiralMapper(primeChecker).toPrimeMap(spiral);

        File outputFile = ExampleOutput.resolve("prime-map.csv");
        BooleanMatrixCSVUtility.saveToCSV(primeMap, outputFile.getPath());
        log.info("wrote {}", outputFile.getAbsolutePath());

        boolean[][] reloaded = BooleanMatrixCSVUtility.readFromCSV(outputFile.getPath());
        log.info("read back {} rows, matches original: {}", reloaded.length, Arrays.deepEquals(primeMap, reloaded));
    }
}
