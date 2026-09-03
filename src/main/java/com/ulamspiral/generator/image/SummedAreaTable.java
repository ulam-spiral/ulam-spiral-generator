package com.ulamspiral.generator.image;

/**
 * Answers "how many true cells are in the (2*radius+1) x (2*radius+1) window around (x, y)" in O(1)
 * per query, after an O(rows*cols) build - a summed-area table (integral image). Same as brute-force scanning
 * every cell in the window on each call, but without repeating that scan every time.
 */
public final class SummedAreaTable {

    private final int[][] table;
    private final int rows;
    private final int cols;

    public SummedAreaTable(boolean[][] matrix) {
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        this.table = new int[rows + 1][cols + 1];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int value = matrix[i][j] ? 1 : 0;
                table[i + 1][j + 1] = value + table[i][j + 1] + table[i + 1][j] - table[i][j];
            }
        }
    }

    public int countTrueInWindow(int x, int y, int radius) {
        int r1 = Math.max(0, x - radius);
        int r2 = Math.min(rows - 1, x + radius);
        int c1 = Math.max(0, y - radius);
        int c2 = Math.min(cols - 1, y + radius);
        return table[r2 + 1][c2 + 1] - table[r1][c2 + 1] - table[r2 + 1][c1] + table[r1][c1];
    }
}
