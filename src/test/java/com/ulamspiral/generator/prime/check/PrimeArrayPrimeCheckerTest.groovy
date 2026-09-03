package com.ulamspiral.generator.prime.check

import com.ulamspiral.generator.prime.PrimeSieve
import spock.lang.Specification

class PrimeArrayPrimeCheckerTest extends Specification {

    def static objectUnderSpec

    def 'setupSpec'() {
        var array = PrimeSieve.primesUpTo(5100 * 5100)
        objectUnderSpec = new PrimeArrayPrimeChecker(array, 5100 * 5100)
    }

    def 'check if is prime'() {
        expect:
        objectUnderSpec.isPrime(number) == expected

        where:
        number   || expected
        -1       || false
        0        || false
        1        || false
        2        || true
        3        || true
        4        || false
        5        || true
        6        || false
        7        || true
        8        || false
        9        || false
        10       || false
        11       || true
        12       || false
        13       || true
        97       || true
        100      || false
        101      || true
        200      || false
        1009     || true
        1021     || true
        1031     || true
        1051     || true
        2047     || false
        4099     || true
        8191     || true
        99991    || true
        100000   || false
        25010001 || false       // 5001^2
    }

    def 'should properly check for primes with distance 2'() {
        expect:
        objectUnderSpec.isPrimeWithDistance(number, distance) == expected

        where:
        number | distance || expected
        3      | 2        || true
        5      | 2        || true
        7      | 2        || true
        11     | 2        || true
        13     | 2        || true
        17     | 2        || true
        19     | 2        || true
        23     | 2        || false // not twin prime
        24     | 2        || false // not prime
        37     | 2        || false // not twin prime
        67     | 2        || false // not twin prime
        79     | 2        || false // not twin prime
        97     | 2        || false // not twin prime
        9929   | 2        || true // twin
    }

    def 'should not throw for a composite number sitting right at the top of the sieved range, even when no prime is nearby'() {
        given: 'a sieve where the numbers just below the limit are all composite - 8 and 9 below limit 10'
        def checker = new PrimeArrayPrimeChecker(PrimeSieve.primesUpTo(10), 10)

        expect:
        checker.isPrime(8) == false
        checker.isPrime(9) == false
    }

    def 'should throw only once truly out of the sieved range, not merely past the largest prime found'() {
        given:
        def checker = new PrimeArrayPrimeChecker(PrimeSieve.primesUpTo(10), 10)

        when:
        checker.isPrime(10)

        then:
        thrown(IllegalArgumentException)
    }

    def 'should throw for an empty primeArray'() {
        when:
        new PrimeArrayPrimeChecker(new long[0], 10)

        then:
        thrown(IllegalArgumentException)
    }

    def 'should throw a clear exception when checkableLimit exceeds Integer.MAX_VALUE'() {
        given:
        def checkableLimit = Long.MAX_VALUE

        when:
        new PrimeArrayPrimeChecker([2L] as long[], checkableLimit)

        then:
        def ex = thrown(IllegalArgumentException)
        ex.getMessage() == "Cannot check primality up to $checkableLimit - exceeds the maximum supported limit of ${Integer.MAX_VALUE}."
        ex.getCause() instanceof ArithmeticException
    }

}
