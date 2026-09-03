package com.ulamspiral.generator.prime.check

import com.ulamspiral.generator.prime.PrimeSieve
import com.ulamspiral.generator.prime.SpiralMapper
import com.ulamspiral.generator.prime.UlamSpiralCalculator
import spock.lang.Ignore
import spock.lang.Specification

class PrimeCheckerPerfTest extends Specification {

    def defaultPrimeChecker = new DefaultPrimeChecker()
    def arrayPrimeChecker = new PrimeArrayPrimeChecker(PrimeSieve.primesUpTo(5100 * 5100), 5100 * 5100)

    @Ignore('Perf test - takes a long time!')
    def 'translate to prime map with distance 2'() {
        given:
        def spiral = UlamSpiralCalculator.calculateUlamSpiral(5001)
        def defaultMapper = new SpiralMapper(defaultPrimeChecker)
        def arrayMapper = new SpiralMapper(arrayPrimeChecker)

        when:
        println("calculating with default checker")
        def startDefault = System.currentTimeMillis()
        defaultMapper.toPrimeMapWithDistance(spiral, 2)
        def endDefault = System.currentTimeMillis()

        println("calculating with array based checker")
        def startArray = System.currentTimeMillis()
        arrayMapper.toPrimeMapWithDistance(spiral, 2)
        def endArray = System.currentTimeMillis()

        def defaultElapsed = endDefault - startDefault
        def arrayElapsed = endArray - startArray

        then:
        println("Default elapsed: $defaultElapsed")
        println("Array elapsed: $arrayElapsed")
        noExceptionThrown()
    }

}
