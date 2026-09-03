package com.ulamspiral.generator.prime

import com.ulamspiral.generator.prime.check.DefaultPrimeChecker
import spock.lang.Specification

class SpiralMapperTest extends Specification {

    def primeChecker = new DefaultPrimeChecker()
    def objectUnderSpec = new SpiralMapper(primeChecker)

    @SuppressWarnings('GroovyPointlessBoolean')
    def 'should properly map spiral'() {
        given:
        def input = UlamSpiralCalculator.calculateUlamSpiral(3)

        when:
        def result = objectUnderSpec.toPrimeMap(input)

        then:
        result[0][0] == true
        result[0][1] == false
        result[0][2] == true
        result[1][0] == false
        result[1][1] == false
        result[1][2] == true
        result[2][0] == true
        result[2][1] == false
        result[2][2] == false
    }

    @SuppressWarnings('GroovyPointlessBoolean')
    def 'should properly map spiral with distance'() {
        given:
        def input = UlamSpiralCalculator.calculateUlamSpiral(3)

        when:
        def result = objectUnderSpec.toPrimeMapWithDistance(input, 2)

        then:
        result[0][0] == true
        result[0][1] == false
        result[0][2] == true
        result[1][0] == false
        result[1][1] == false
        result[1][2] == false
        result[2][0] == true
        result[2][1] == false
        result[2][2] == false
    }

    @SuppressWarnings('GroovyPointlessBoolean')
    def 'should properly map spiral with distance 5x5'() {
        given:
        def input = UlamSpiralCalculator.calculateUlamSpiral(5)

        when:
        def result = objectUnderSpec.toPrimeMapWithDistance(input, 2)

        then:
        result[0][0] == true    // 17
        result[0][1] == false   // 16
        result[0][2] == false   // 15
        result[0][3] == false   // 14
        result[0][4] == true    // 13

        result[1][0] == false   // 18
        result[1][1] == true    // 5
        result[1][2] == false   // 4
        result[1][3] == true    // 3
        result[1][4] == false   // 12

        result[2][0] == true   // 19
        result[2][1] == false   // 6
        result[2][2] == false   // 1
        result[2][3] == false   // 2
        result[2][4] == true   // 11

        result[3][0] == false   // 20
        result[3][1] == true    // 7
        result[3][2] == false   // 8
        result[3][3] == false   // 9
        result[3][4] == false   // 10

        result[4][0] == false   // 21
        result[4][1] == false   // 22
        result[4][2] == false   // 23
        result[4][3] == false   // 24
        result[4][4] == false   // 25
    }
}
