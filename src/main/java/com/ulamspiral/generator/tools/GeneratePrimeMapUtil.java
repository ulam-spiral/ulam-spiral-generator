package com.ulamspiral.generator.tools;

import com.ulamspiral.generator.prime.PrimeSieve;
import com.ulamspiral.generator.prime.SpiralMapper;
import com.ulamspiral.generator.prime.UlamSpiralCalculator;
import com.ulamspiral.generator.prime.check.PrimeArrayPrimeChecker;
import com.ulamspiral.generator.csv.BooleanMatrixCSVUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Generates a .csv file with the prime map of a spiral with edge length SIZE.
 * Primes are represented as 1s and composites as 0s in the output csv.
 * Can be used to pre-generate prime maps for large spirals.
 */
public class GeneratePrimeMapUtil {

    private static final Logger log = LoggerFactory.getLogger(GeneratePrimeMapUtil.class);

    private static final int SIZE = 5001;

    public static void main(String[] args) throws IOException {
        long limit = (long) SIZE * SIZE + 1;
        log.info("sieving primality up to {}...", limit);
        long sieveStart = System.currentTimeMillis();
        var primeArray = PrimeSieve.primesUpTo(limit);
        var primeChecker = new PrimeArrayPrimeChecker(primeArray, limit);
        log.info("sieved in {} ms", System.currentTimeMillis() - sieveStart);

        log.info("calculating the spiral...");
        var spiral = UlamSpiralCalculator.calculateUlamSpiral(SIZE);

        log.info("mapping to prime map...");
        long mapStart = System.currentTimeMillis();
        var primeMap = new SpiralMapper(primeChecker).toPrimeMap(spiral);
        log.info("mapped in {} ms", System.currentTimeMillis() - mapStart);

        log.info("saving to primemap.csv...");
        BooleanMatrixCSVUtility.saveToCSV(primeMap, "primemap.csv");

        log.info("done.");
    }
}
