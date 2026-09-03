package com.ulamspiral.generator.tools;

import com.ulamspiral.generator.prime.PrimeSieve;
import com.ulamspiral.generator.csv.LongArrayCSVUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Generates a one-row .csv containing all primes, ordered low-to-high, up to SPIRAL_SIZE^2
 * Can be used to pre-generate prime lookup arrays for large spirals.
 */
public class GeneratePrimeArrayUtil {

    private static final Logger log = LoggerFactory.getLogger(GeneratePrimeArrayUtil.class);
    private static final int SPIRAL_SIZE = 5001;

    public static void main(String[] args) throws IOException {
        long sieveLimit = (long) SPIRAL_SIZE * SPIRAL_SIZE;

        log.info("sieving primes...");
        long start = System.currentTimeMillis();
        var primes = PrimeSieve.primesUpTo(sieveLimit);
        long elapsed = System.currentTimeMillis() - start;
        log.info("found {} primes in {} ms", primes.length, elapsed);

        log.info("saving to primeArray.csv...");
        LongArrayCSVUtility.saveToCSV(primes, "primeArray.csv");

        log.info("done.");
    }
}
