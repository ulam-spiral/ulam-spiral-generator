package com.ulamspiral.generator.prime

import spock.lang.Ignore
import spock.lang.Specification

class UlamSpiralCalculatorPerfTest extends Specification {

    @Ignore('Perf test - takes a while, prints timings for manual comparison')
    def 'diagonal-formula approach vs naive spiral walk'() {
        given:
        def correctnessSizes = [1, 3, 5, 9, 51, 101]
        def benchSizes = [501, 5001, 9001]
        def repetitions = 15
        def warmupSize = 2001
        def warmupIterations = 30

        expect:
        correctnessSizes.every { size ->
            UlamSpiralCalculator.calculateUlamSpiral(size) == NaiveSpiralWalk.calculate(size)
        }

        when:
        warmupIterations.times {
            UlamSpiralCalculator.calculateUlamSpiral(warmupSize)
            NaiveSpiralWalk.calculate(warmupSize)
        }

        benchSizes.each { size ->
            def diagonalTimes = []
            def naiveTimes = []
            repetitions.times { rep ->
                if (rep % 2 == 0) {
                    diagonalTimes << timeNanos { UlamSpiralCalculator.calculateUlamSpiral(size) }
                    naiveTimes << timeNanos { NaiveSpiralWalk.calculate(size) }
                } else {
                    naiveTimes << timeNanos { NaiveSpiralWalk.calculate(size) }
                    diagonalTimes << timeNanos { UlamSpiralCalculator.calculateUlamSpiral(size) }
                }
            }
            diagonalTimes.sort()
            naiveTimes.sort()
            def diagonalMedianMs = diagonalTimes[repetitions.intdiv(2)] / 1_000_000.0
            def naiveMedianMs = naiveTimes[repetitions.intdiv(2)] / 1_000_000.0
            println "size=$size diagonal-formula=${diagonalMedianMs}ms naive-walk=${naiveMedianMs}ms"
        }

        then:
        noExceptionThrown()
    }

    private static long timeNanos(Closure c) {
        def start = System.nanoTime()
        c.call()
        return System.nanoTime() - start
    }
}
