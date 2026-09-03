package com.ulamspiral.generator.csv;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads/writes a prime map (boolean[][]) from/to a .csv file.
 */
public final class BooleanMatrixCSVUtility {

    private BooleanMatrixCSVUtility(){}

    public static void saveToCSV(boolean[][] array, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (boolean[] booleans : array) {
                for (int j = 0; j < booleans.length; j++) {
                    writer.write(booleans[j] ? "1" : "0");
                    if (j < booleans.length - 1) {
                        writer.write(",");
                    }
                }
                writer.write("\n");
            }
        }
    }

    public static boolean[][] readFromCSV(String filePath) throws IOException {
        List<boolean[]> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                boolean[] row = new boolean[values.length];
                for (int i = 0; i < values.length; i++) {
                    row[i] = values[i].trim().equals("1");
                }
                rows.add(row);
            }
        }

        boolean[][] array = new boolean[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            array[i] = rows.get(i);
        }
        return array;
    }
}
