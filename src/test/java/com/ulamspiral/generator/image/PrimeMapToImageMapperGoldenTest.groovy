package com.ulamspiral.generator.image

import com.ulamspiral.generator.prime.PrimeSieve
import com.ulamspiral.generator.prime.SpiralMapper
import com.ulamspiral.generator.prime.UlamSpiralCalculator
import com.ulamspiral.generator.prime.check.PrimeArrayPrimeChecker
import com.ulamspiral.generator.testutil.GoldenImageAssert
import spock.lang.Specification
import spock.lang.Unroll

import java.awt.Color

class PrimeMapToImageMapperGoldenTest extends Specification {

    def primeColor = new Color(0xff, 0x00, 0x00)
    def compositeColor = new Color(0x00, 0x00, 0xff)

    @Unroll
    def 'should exactly match the golden image for size=#size radius=#radius'() {
        given: 'the exact same real prime data pipeline used to generate the golden image'
        long sieveLimit = (long) size * size + 1
        def checker = new PrimeArrayPrimeChecker(PrimeSieve.primesUpTo(sieveLimit), sieveLimit)
        def spiral = UlamSpiralCalculator.calculateUlamSpiral(size)
        def primeMap = new SpiralMapper(checker).toPrimeMap(spiral)

        when: 'the current (SummedAreaTable-backed) implementation renders the same request'
        def freshImageData = PrimeMapToImageMapper.createImageWithDensity(primeMap, primeColor, compositeColor, radius)

        then: 'every single pixel matches the golden PNG generated before the change - not a sample, not a visual check, a full diff'
        def goldenFile = new File("src/test/resources/golden/density_size${size}_radius${radius}.png")
        GoldenImageAssert.assertPixelPerfect(goldenFile, freshImageData)

        where:
        size | radius
        11   | 1
        51   | 3
        101  | 10
        301  | 5
        501  | 1
        501  | 10
    }
}
