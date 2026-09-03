package com.ulamspiral.generator.prime


import spock.lang.Specification

class UlamSpiralCalculatorTest extends Specification {

    void 'single-cell spiral'() {
        when:
        var spiral = UlamSpiralCalculator.calculateUlamSpiral(1)

        then:
        spiral == [[1L] as long[]] as long[][]
    }

    void 'basic spiral test'() {
        when:
        var spiral = UlamSpiralCalculator.calculateUlamSpiral(3)

        then:
        printSpiral(spiral)
        spiral[0][0] == 5
        spiral[0][1] == 4
        spiral[0][2] == 3
        spiral[1][0] == 6
        spiral[1][1] == 1
        spiral[1][2] == 2
        spiral[2][0] == 7
        spiral[2][1] == 8
        spiral[2][2] == 9
    }

    void 'larger spiral random test'() {
        given:
        var size = 101
        var mid = (int)(size/2)

        when:
        var spiral = UlamSpiralCalculator.calculateUlamSpiral(size)

        then:
        spiral[0][0] == 10001
        spiral[0][mid] == 9951
        spiral[size-1][mid] == 10151
        spiral[size-1][size-1] == size*size
        spiral[size-1][size-2] == (size*size) - 1
        spiral[49][100] == 9852
        spiral[96][37] == 8590
        spiral[96][36] == 8589
    }

    def 'should throw for a size smaller than 1'() {
        when:
        UlamSpiralCalculator.calculateUlamSpiral(size)

        then:
        def ex = thrown(MatrixValidationException)
        ex.getMessage() == "Spiral size must be at least 1."

        where:
        size << [0, -1, -5]
    }

    def 'should throw for an even size'() {
        when:
        UlamSpiralCalculator.calculateUlamSpiral(size)

        then:
        def ex = thrown(MatrixValidationException)
        ex.getMessage() == "Spiral size (side length) must be an odd number."

        where:
        size << [2, 10, 100]
    }

    def 'should not throw for valid odd, positive sizes'() {
        when:
        UlamSpiralCalculator.calculateUlamSpiral(size)

        then:
        noExceptionThrown()

        where:
        size << [1, 3, 5, 101]
    }

    private static void printSpiral(long[][] spiral) {
        for (final def i in spiral) {
            for (final def j in i) {
                print j + ','
            }
            println ''
        }
    }
}
