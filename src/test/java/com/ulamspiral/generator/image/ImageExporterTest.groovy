package com.ulamspiral.generator.image

import spock.lang.Specification
import spock.lang.TempDir

import javax.imageio.ImageIO
import java.awt.Color

class ImageExporterTest extends Specification {

    @TempDir
    File tempDir

    def 'should export pixel data as a readable PNG file with matching dimensions and colors'() {
        given:
        Color[][] pixelData = [
                [new Color(255, 0, 0), new Color(0, 255, 0)] as Color[],
                [new Color(0, 0, 255), new Color(255, 255, 255)] as Color[],
        ]
        def outputFile = new File(tempDir, 'out.png')

        when:
        ImageExporter.exportImage(pixelData, outputFile)

        then:
        outputFile.exists()

        and:
        def image = ImageIO.read(outputFile)
        image.getWidth() == 2
        image.getHeight() == 2
        new Color(image.getRGB(0, 0)) == new Color(255, 0, 0)
        new Color(image.getRGB(1, 0)) == new Color(0, 255, 0)
        new Color(image.getRGB(0, 1)) == new Color(0, 0, 255)
        new Color(image.getRGB(1, 1)) == new Color(255, 255, 255)
    }

    def 'should throw for null pixel data'() {
        when:
        ImageExporter.exportImage(null, new File(tempDir, 'out.png'))

        then:
        thrown(IllegalArgumentException)
    }

    def 'should throw for empty pixel data'() {
        when:
        ImageExporter.exportImage(new Color[0][0], new File(tempDir, 'out.png'))

        then:
        thrown(IllegalArgumentException)
    }
}
