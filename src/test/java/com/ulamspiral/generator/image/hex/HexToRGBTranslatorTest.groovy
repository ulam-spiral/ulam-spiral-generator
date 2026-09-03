package com.ulamspiral.generator.image.hex


import spock.lang.Specification

import java.awt.Color

class HexToRGBTranslatorTest extends Specification {

    def "test hexToRGB conversion"() {
        expect:
        // Expected RGB values for the given hex string
        HexToRGBTranslator.hexStringToRGB(hex) == new Color(expectedRed, expectedGreen, expectedBlue)

        where:
        hex       | expectedRed | expectedGreen | expectedBlue
        "#34A2EB" | 52          | 162           | 235
        "#FFFFFF" | 255         | 255           | 255
        "#000000" | 0           | 0             | 0
        "#FF5733" | 255         | 87            | 51
        "#1a4526" | 26          | 69            | 38
    }

    def "test hexToRGB without # symbol"() {
        expect:
        HexToRGBTranslator.hexStringToRGB("34A2EB") == new Color(52, 162, 235)
    }

    def "test invalid hex string throws exception"() {
        when:
        HexToRGBTranslator.hexStringToRGB("G12EB3") // Invalid hex string with non-hex character

        then:
        thrown(HexConversionException)
    }

    def "should reject hex strings of the wrong length instead of silently truncating"() {
        when:
        HexToRGBTranslator.hexStringToRGB(hex)

        then:
        thrown(HexConversionException)

        where:
        hex << ["#e194560", "#e1945", "e194560", "e1945", "#", ""]
    }
}
