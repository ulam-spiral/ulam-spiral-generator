package com.ulamspiral.generator.prime;

final class NaiveSpiralWalk {

    private NaiveSpiralWalk() {}

    static long[][] calculate(int size) {
        long[][] matrix = new long[size][size];
        int row = size / 2;
        int col = size / 2;
        long value = 1;
        matrix[row][col] = value++;

        int[] dRow = {0, -1, 0, 1};
        int[] dCol = {1, 0, -1, 0};
        int dirIndex = 0;

        int legLength = 1;
        long total = (long) size * size;

        while (value <= total) {
            for (int turn = 0; turn < 2 && value <= total; turn++) {
                for (int step = 0; step < legLength && value <= total; step++) {
                    row += dRow[dirIndex];
                    col += dCol[dirIndex];
                    matrix[row][col] = value++;
                }
                dirIndex = (dirIndex + 1) % 4;
            }
            legLength++;
        }
        return matrix;
    }
}
