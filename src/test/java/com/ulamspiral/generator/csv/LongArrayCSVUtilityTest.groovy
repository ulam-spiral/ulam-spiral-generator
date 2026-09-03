package com.ulamspiral.generator.csv

import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Path

class LongArrayCSVUtilityTest extends Specification {

    def "saveToCSV should save long array to CSV file"() {
        given: "A long array and a temporary file"
        long[] arrayToSave = [123456789L, 987654321L, 1122334455L, 5566778899L]
        Path tempFile = Files.createTempFile("test", ".csv")

        when: "Saving the array to CSV"
        LongArrayCSVUtility.saveToCSV(arrayToSave, tempFile.toString())

        then: "The file should exist and contain the correct data"
        String content = Files.readString(tempFile)
        content == "123456789,987654321,1122334455,5566778899\r\n"

        cleanup:
        Files.deleteIfExists(tempFile) // Clean up the temporary file
    }

    def "readFromCSV should read long array from CSV file"() {
        given: "A temporary CSV file with long values"
        Path tempFile = Files.createTempFile("test", ".csv")
        Files.writeString(tempFile, "123456789,987654321,1122334455,5566778899\n")

        when: "Reading the long array from CSV"
        long[] resultArray = LongArrayCSVUtility.readFromCSV(tempFile.toString())

        then: "The resulting array should match the original values"
        resultArray == [123456789L, 987654321L, 1122334455L, 5566778899L]

        cleanup:
        Files.deleteIfExists(tempFile)
    }

    def "readFromCSV should throw IOException for non-existent file"() {
        when: "Reading from a non-existent file"
        LongArrayCSVUtility.readFromCSV("non_existent_file.csv")

        then: "An IOException should be thrown"
        thrown(IOException)
    }

    def "readFromCSV should throw IOException for empty file"() {
        given: "A temporary empty CSV file"
        Path tempFile = Files.createTempFile("test", ".csv")

        when: "Reading from the empty CSV file"
        LongArrayCSVUtility.readFromCSV(tempFile.toString())

        then: "An IOException should be thrown"
        thrown(IOException)

        cleanup:
        Files.deleteIfExists(tempFile)
    }
}
