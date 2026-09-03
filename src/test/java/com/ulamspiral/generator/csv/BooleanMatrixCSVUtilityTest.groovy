package com.ulamspiral.generator.csv

import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Paths

class BooleanMatrixCSVUtilityTest extends Specification {

    def 'test saveToCSV and readFromCSV methods'() {
        given:
        // Sample boolean array
        boolean[][] sampleArray = [
                [true, false, true],
                [false, true, false],
                [true, true, false]
        ]
        def filePath = 'test_output.csv'

        when:
        // Save the array to a CSV file
        BooleanMatrixCSVUtility.saveToCSV(sampleArray, filePath)

        then:
        // Check if the file was created
        Files.exists(Paths.get(filePath))

        when:
        // Read the array back from the CSV file
        boolean[][] loadedArray = BooleanMatrixCSVUtility.readFromCSV(filePath)

        then:
        // Verify that the loaded array matches the original array
        loadedArray.length == sampleArray.length
        for (int i = 0; i < sampleArray.length; i++) {
            assert loadedArray[i].length == sampleArray[i].length
            for (int j = 0; j < sampleArray[i].length; j++) {
                assert loadedArray[i][j] == sampleArray[i][j]
            }
        }

        cleanup:
        // Delete the test file
        Files.deleteIfExists(Paths.get(filePath))
    }
}
