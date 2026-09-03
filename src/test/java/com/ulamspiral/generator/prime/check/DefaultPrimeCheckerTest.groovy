package com.ulamspiral.generator.prime.check


import spock.lang.Specification

class DefaultPrimeCheckerTest extends Specification {

    def objectUnderSpec = new DefaultPrimeChecker()

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

}
