package com.ulamspiral.generator.image;

/**
 * for comparison only
 */
public final class NeighbourCounter {
    private NeighbourCounter(){}

    public static int numberOfTrueNeighbours(int x, int y, int radius, boolean[][] matrix) {
        int count = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int i = x - radius; i <= x + radius; i++) {
            for (int j = y - radius; j <= y + radius; j++) {
                if (i >= 0 && i < rows && j >= 0 && j < cols) {
                    if (matrix[i][j]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }


}
