package com.ulamspiral.generator.prime

import com.ulamspiral.generator.prime.check.DefaultPrimeChecker
import spock.lang.Specification
import spock.lang.Unroll

class PrimeSieveTest extends Specification {

    def 'should find all primes below 10'() {
        expect:
        PrimeSieve.primesUpTo(10) == [2L, 3L, 5L, 7L] as long[]
    }

    def 'should find the first primes below 30'() {
        expect:
        PrimeSieve.primesUpTo(30) == [2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L, 23L, 29L] as long[]
    }

    @Unroll
    def 'should return no primes for n <= 2'() {
        expect:
        PrimeSieve.primesUpTo(n) == new long[0]

        where:
        n << [-1, 0, 1, 2]
    }

    def 'should return the single prime below 3'() {
        expect:
        PrimeSieve.primesUpTo(3) == [2L] as long[]
    }

    def 'should throw a clear exception when asked to sieve beyond Integer.MAX_VALUE'() {
        given:
        def n = Long.MAX_VALUE

        when:
        PrimeSieve.primesUpTo(n)

        then:
        def ex = thrown(IllegalArgumentException)
        ex.getMessage() == "Cannot sieve up to $n - exceeds the maximum supported limit of ${Integer.MAX_VALUE}."
        ex.getCause() instanceof ArithmeticException
    }

    def 'should agree with trial-division primality checking over a wider range'() {
        given:
        def trialDivisionChecker = new DefaultPrimeChecker()
        def limit = 10_000

        when:
        def sieved = PrimeSieve.primesUpTo(limit) as Set

        then: "every number the sieve calls prime is actually prime"
        sieved.every { trialDivisionChecker.isPrime(it) }

        and: "every number the sieve leaves out is not prime"
        (0..<limit).every { n -> trialDivisionChecker.isPrime(n) == sieved.contains((long) n) }
    }
}
