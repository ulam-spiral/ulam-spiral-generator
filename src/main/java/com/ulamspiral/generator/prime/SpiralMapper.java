package com.ulamspiral.generator.prime;

import com.ulamspiral.generator.prime.check.PrimeChecker;

import java.util.function.Predicate;

/**
 * Maps a matrix of raw numbers into boolean[][] representing primes (true=prime).
 * Also supports distance/gap mapping.
 */
@SuppressWarnings("ClassCanBeRecord")
public class SpiralMapper {

    private final PrimeChecker primeChecker;

    public SpiralMapper(PrimeChecker primeChecker) {
        this.primeChecker = primeChecker;
    }

    public boolean[][] toPrimeMap(long[][] spiral) {
        return iterate(spiral, primeChecker::isPrime);
    }

    public boolean[][] toPrimeMapWithDistance(long[][] spiral, int distance) {
        return iterate(spiral, (n) -> primeChecker.isPrimeWithDistance(n, distance));
    }

    private boolean[][] iterate(long[][] primeSpiral, Predicate<Long> mappingPredicate) {
        final var primeMap = new boolean[primeSpiral.length][primeSpiral.length];
        for (int i = 0; i < primeSpiral.length; i++) {
            for (int j = 0; j < primeSpiral[i].length; j++) {
                primeMap[i][j] = mappingPredicate.test(primeSpiral[i][j]);
            }
        }
        return primeMap;
    }
}
