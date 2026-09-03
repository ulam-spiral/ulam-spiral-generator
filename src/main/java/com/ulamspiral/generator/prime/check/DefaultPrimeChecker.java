package com.ulamspiral.generator.prime.check;

/**
 * Simple (and naive) primality check.
 */
public class DefaultPrimeChecker implements PrimeChecker {

    @Override
    public boolean isPrime(long n) {
        if (n <= 1) return false;
        if (n <= 3) return true;          // 2, 3
        if ((n & 1L) == 0L) return false; // even

        for (long d = 3; d <= n / d; d += 2) {
            if (n % d == 0) return false;
        }
        return true;
    }
}
