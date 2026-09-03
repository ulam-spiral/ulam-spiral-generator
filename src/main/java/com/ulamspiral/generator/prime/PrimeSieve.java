package com.ulamspiral.generator.prime;

import java.util.ArrayList;

public final class PrimeSieve {

    private PrimeSieve() {}

    /**
     * Returns every prime less than n, in ascending order, via a Sieve of Eratosthenes.
     */
    public static long[] primesUpTo(long n) {
        int limit;
        try {
            limit = Math.toIntExact(n);
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException(
                    "Cannot sieve up to " + n + " - exceeds the maximum supported limit of " + Integer.MAX_VALUE + ".", e);
        }
        if (limit <= 2) {
            return new long[0];
        }

        var composite = new boolean[limit];
        var primes = new ArrayList<Long>();
        for (int i = 2; i < limit; i++) {
            if (!composite[i]) {
                primes.add((long) i);
                for (long j = (long) i * i; j < limit; j += i) {
                    composite[(int) j] = true;
                }
            }
        }

        var result = new long[primes.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = primes.get(i);
        }
        return result;
    }
}
