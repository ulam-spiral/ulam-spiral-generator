package com.ulamspiral.generator.image

import spock.lang.Specification

import java.awt.Color

class PrimeMapToImageMapperTest extends Specification {

    def primeColor = new Color(255, 0, 0)
    def compositeColor = new Color(0, 0, 255)

    def 'should map prime cells to primeColor and composite cells to compositeColor'() {
        given:
        boolean[][] primeMap = [
                [true, false, true],
                [false, true, false],
                [true, false, false],
        ]

        when:
        def result = PrimeMapToImageMapper.createImage(primeMap, primeColor, compositeColor)

        then:
        result[0][0] == primeColor
        result[0][1] == compositeColor
        result[0][2] == primeColor
        result[1][0] == compositeColor
        result[1][1] == primeColor
        result[1][2] == compositeColor
        result[2][0] == primeColor
        result[2][1] == compositeColor
        result[2][2] == compositeColor
    }

    def 'createImage should preserve matrix dimensions'() {
        given:
        boolean[][] primeMap = new boolean[5][5]

        when:
        def result = PrimeMapToImageMapper.createImage(primeMap, primeColor, compositeColor)

        then:
        result.length == 5
        result[0].length == 5
    }

    def 'createImageWithDensity should color the cell with the most true neighbours closest to primeColor'() {
        given: "a single prime in the center of a 5x5 map, too far from the corners to reach them at radius 1"
        boolean[][] primeMap = new boolean[5][5]
        primeMap[2][2] = true

        when:
        def result = PrimeMapToImageMapper.createImageWithDensity(primeMap, primeColor, compositeColor, 1)

        then: "the center cell (highest density) is pure primeColor"
        result[2][2] == primeColor

        and: "the far corners (density 0, out of reach at radius 1) are pure compositeColor"
        result[0][0] == compositeColor
        result[0][4] == compositeColor
        result[4][0] == compositeColor
        result[4][4] == compositeColor
    }

    def 'createImageWithDensity should collapse to black when the prime map has no true cells at all'() {
        given:
        boolean[][] primeMap = new boolean[3][3]

        when:
        def result = PrimeMapToImageMapper.createImageWithDensity(primeMap, primeColor, compositeColor, 1)

        then:
        result.every { row -> row.every { it == new Color(0, 0, 0) } }
    }
}
