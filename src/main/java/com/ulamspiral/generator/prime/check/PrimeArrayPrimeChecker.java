package com.ulamspiral.generator.prime.check;

/**
 * Lookup-based primality check.
 */
public class PrimeArrayPrimeChecker implements PrimeChecker {

    private final boolean[] isPrime;

    /**
     * @param primeArray every known prime, from a sieve run up to (but not including) checkableLimit
     * @param checkableLimit exclusive upper bound of numbers possible to check.
     *                       E.g. if sieve was run up to 100, largest prime would be 97.
     *                       However, this method should be callable on 99.
     */
    public PrimeArrayPrimeChecker(long[] primeArray, long checkableLimit) {
        if (primeArray.length == 0) {
            throw new IllegalArgumentException("primeArray must not be empty.");
        }
        int limit;
        try {
            limit = Math.toIntExact(checkableLimit);
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException(
                    "Cannot check primality up to " + checkableLimit + " - exceeds the maximum supported limit of " + Integer.MAX_VALUE + ".", e);
        }
        this.isPrime = new boolean[limit];
        for (long l : primeArray) {
            isPrime[(int) l] = true;
        }
    }

    @Override
    public boolean isPrime(long number) {
        verifyNumberToCheck(number);
        if (number < 2) {
            return false;
        }
        return isPrime[(int) number];
    }

    private void verifyNumberToCheck(long number) {
        if (number >= isPrime.length) {
            throw new IllegalArgumentException("Requested number to check too high. Sieved up to " + isPrime.length);
        }
    }
}
