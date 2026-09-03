package com.ulamspiral.generator.image

import spock.lang.Specification
import spock.lang.Unroll

import java.awt.Color

class ColorInterpolationUtilTest extends Specification {

    @Unroll
    def 'should interpolate two colors'() {
        when:
        def result = ColorInterpolationUtil.interpolate(color1, color2, fraction as double)

        then:
        result.getRed() == expected.getRed()
        result.getGreen() == expected.getGreen()
        result.getBlue() == expected.getBlue()

        where:
        color1                   | color2               | fraction || expected
        new Color(255, 255, 255) | new Color(0, 0, 0)   | 0.5      || new Color(127, 127, 127)
        new Color(0, 255, 0)     | new Color(0, 0, 255) | 0.5      || new Color(0, 127, 127)
        new Color(0, 255, 0)     | new Color(0, 0, 255) | 1        || new Color(0, 0, 255)
        new Color(0, 255, 0)     | new Color(0, 0, 255) | 0        || new Color(0, 255, 0)
        new Color(255, 255, 0)   | new Color(0, 0, 255) | 0.5      || new Color(127, 127, 127)
    }

}
