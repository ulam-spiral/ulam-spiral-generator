package com.ulamspiral.generator.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Reads/writes prime lookup arrays from/to .csv files.
 */
public final class LongArrayCSVUtility {

    private LongArrayCSVUtility(){}

    public static void saveToCSV(long[] array, String path) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (int i = 0; i < array.length; i++) {
                writer.write(Long.toString(array[i]));
                if (i < array.length - 1) {
                    writer.write(",");
                }
            }
            writer.newLine();
        }
    }

    public static long[] readFromCSV(String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = reader.readLine();
            if (line != null) {
                String[] stringValues = line.split(",");
                long[] longArray = new long[stringValues.length];
                for (int i = 0; i < stringValues.length; i++) {
                    longArray[i] = Long.parseLong(stringValues[i].trim());
                }
                return longArray;
            } else {
                throw new IOException("Null input at file " + path);
            }
        }
    }

}
