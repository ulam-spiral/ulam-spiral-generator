package com.ulamspiral.generator.prime;

public final class UlamSpiralCalculator {

    private UlamSpiralCalculator() {}

    /**
     * Provides a long[][] matrix with all natural numbers fitting into spiral of a given size.
     * For efficiency, instead of spiraling through the matrix (naive spiral walk) it calculates
     * north-west and south-west diagonals using the following formulas:
     * NW: 4n^2 + 1
     * SW: 4n^2 + 2n + 1
     * And fills the remaining empty cells by adding/subtracting from known values.
     */
    public static long[][] calculateUlamSpiral(int size) throws MatrixValidationException {
        validateSize(size);
        long [][] matrix = new long[size][size];
        fillCenter(matrix);
        fillTop(matrix);
        fillBottom(matrix);
        fillLeft(matrix);
        fillRight(matrix);
        return matrix;
    }

    private static void fillCenter(long[][] matrix) {
        matrix[matrix.length/2][matrix.length/2] = 1;
    }

    private static void fillBottom(long[][] matrix) {
        for (int i = matrix.length - 1; i > matrix.length/2; i--) {
            long[] row = matrix[i];
            int startIndex = matrix.length - i - 1;
            row[startIndex] = southWestDiagonal(matrix.length/2 - startIndex);
            for (int j = startIndex + 1; j < row.length - startIndex; j++) {
                row[j] = row[j - 1] + 1;
            }
        }
    }

    private static void fillLeft(long[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length/2; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][j] = matrix[i-1][j] + 1;
                }
            }
        }
    }

    private static void fillRight(long[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = matrix.length/2 + 1; j < matrix.length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][j] = matrix[i-1][j] - 1;
                }
            }
        }
    }

    private static void fillTop(long[][] matrix) {
        for (int i = 0; i < matrix.length/2; i++) {
            long[] row = matrix[i];
            row[i] = northWestDiagonal(matrix.length/2 - i);
            for (int j = i + 1; j < row.length - i; j++) {
                row[j] = row[j - 1] - 1;
            }
        }
    }

    private static long northWestDiagonal(int ringNumber) {
        long val = (ringNumber * 2L) + 1L;
        long valSquared = val * val;
        return valSquared - (4L * ringNumber);
    }

    private static long southWestDiagonal(int ringNumber) {
        long val = (ringNumber * 2L) + 1L;
        long valSquared = val * val;
        return valSquared - (2L * ringNumber);
    }

    private static void validateSize(int size) throws MatrixValidationException {
        if (size < 1) {
            throw new MatrixValidationException("Spiral size must be at least 1.");
        }
        if (size % 2 == 0) {
            throw new MatrixValidationException("Spiral size (side length) must be an odd number.");
        }
    }
}
