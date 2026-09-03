package com.ulamspiral.generator.prime.check;

public interface PrimeChecker {

    boolean isPrime(long number);

    default boolean isPrimeWithDistance(long number, int distance) {
        if (!isPrime(number)) {
            return false;
        }
        long a = number - distance;
        if (a > 1 && isPrime(a)) {
            return true;
        }
        long b = number + distance;
        return b > 1 && isPrime(b);
    }
}
