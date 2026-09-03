package com.ulamspiral.generator.image

import spock.lang.Specification

import java.util.concurrent.ThreadLocalRandom

class SummedAreaTableTest extends Specification {

    // same fixtures as NeighbourCounterTest, so both are proven against the same hand-verified truth

    def 'should count zero neighbours in an all-false matrix'() {
        given:
        def input = new boolean[][]{
                [false, false, false],
                [false, false, false],
                [false, false, false],
        }
        def table = new SummedAreaTable(input)

        expect:
        table.countTrueInWindow(0, 0, 1) == 0
        table.countTrueInWindow(1, 1, 1) == 0
        table.countTrueInWindow(2, 2, 1) == 0
    }

    def 'should count a single true cell reachable from every position in a 3x3 grid at radius 1'() {
        given:
        def input = new boolean[][]{
                [false, false, false],
                [false, true, false],
                [false, false, false],
        }
        def table = new SummedAreaTable(input)

        expect:
        table.countTrueInWindow(0, 0, 1) == 1
        table.countTrueInWindow(0, 1, 1) == 1
        table.countTrueInWindow(0, 2, 1) == 1
        table.countTrueInWindow(1, 0, 1) == 1
        table.countTrueInWindow(1, 1, 1) == 1
        table.countTrueInWindow(1, 2, 1) == 1
        table.countTrueInWindow(2, 0, 1) == 1
        table.countTrueInWindow(2, 1, 1) == 1
        table.countTrueInWindow(2, 2, 1) == 1
    }

    def 'should count correctly with two true cells and asymmetric corner clipping'() {
        given:
        def input = new boolean[][]{
                [true, false, false],
                [false, true, false],
                [false, false, false],
        }
        def table = new SummedAreaTable(input)

        expect:
        table.countTrueInWindow(0, 0, 1) == 2
        table.countTrueInWindow(0, 1, 1) == 2
        table.countTrueInWindow(0, 2, 1) == 1
        table.countTrueInWindow(1, 0, 1) == 2
        table.countTrueInWindow(1, 1, 1) == 2
        table.countTrueInWindow(1, 2, 1) == 1
        table.countTrueInWindow(2, 0, 1) == 1
        table.countTrueInWindow(2, 1, 1) == 1
        table.countTrueInWindow(2, 2, 1) == 1
    }

    def 'should count every cell in an all-true matrix, respecting edge clipping'() {
        given:
        def input = new boolean[][]{
                [true, true, true],
                [true, true, true],
                [true, true, true],
        }
        def table = new SummedAreaTable(input)

        expect:
        table.countTrueInWindow(0, 0, 1) == 4
        table.countTrueInWindow(0, 1, 1) == 6
        table.countTrueInWindow(0, 2, 1) == 4
        table.countTrueInWindow(1, 0, 1) == 6
        table.countTrueInWindow(1, 1, 1) == 9
        table.countTrueInWindow(1, 2, 1) == 6
        table.countTrueInWindow(2, 0, 1) == 4
        table.countTrueInWindow(2, 1, 1) == 6
        table.countTrueInWindow(2, 2, 1) == 4
    }

    def 'should match a hand-verified 5x5 sparse matrix at radius 1'() {
        given:
        def input = new boolean[][]{
                [true, false, false, false, true],
                [false, true, false, true, false],
                [true, false, false, true, true],
                [false, true, false, false, false],
                [false, false, true, false, false],
        }
        def table = new SummedAreaTable(input)

        expect:
        table.countTrueInWindow(0, 0, 1) == 2
        table.countTrueInWindow(0, 1, 1) == 2
        table.countTrueInWindow(0, 2, 1) == 2
        table.countTrueInWindow(0, 3, 1) == 2
        table.countTrueInWindow(0, 4, 1) == 2
        table.countTrueInWindow(1, 0, 1) == 3
        table.countTrueInWindow(1, 1, 1) == 3
        table.countTrueInWindow(1, 2, 1) == 3
        table.countTrueInWindow(1, 3, 1) == 4
        table.countTrueInWindow(1, 4, 1) == 4
        table.countTrueInWindow(2, 0, 1) == 3
        table.countTrueInWindow(2, 1, 1) == 3
        table.countTrueInWindow(2, 2, 1) == 4
        table.countTrueInWindow(2, 3, 1) == 3
        table.countTrueInWindow(2, 4, 1) == 3
        table.countTrueInWindow(3, 0, 1) == 2
        table.countTrueInWindow(3, 1, 1) == 3
        table.countTrueInWindow(3, 2, 1) == 3
        table.countTrueInWindow(3, 3, 1) == 3
        table.countTrueInWindow(3, 4, 1) == 2
        table.countTrueInWindow(4, 0, 1) == 1
        table.countTrueInWindow(4, 1, 1) == 2
        table.countTrueInWindow(4, 2, 1) == 2
        table.countTrueInWindow(4, 3, 1) == 1
        table.countTrueInWindow(4, 4, 1) == 0
    }

    def 'should match the same 5x5 sparse matrix at radius 2'() {
        given:
        def input = new boolean[][]{
                [true, false, false, false, true],
                [false, true, false, true, false],
                [true, false, false, true, true],
                [false, true, false, false, false],
                [false, false, true, false, false],
        }
        def table = new SummedAreaTable(input)

        expect:
        table.countTrueInWindow(0, 0, 2) == 3
        table.countTrueInWindow(0, 1, 2) == 5
        table.countTrueInWindow(0, 2, 2) == 7
        table.countTrueInWindow(0, 3, 2) == 5
        table.countTrueInWindow(0, 4, 2) == 4
        table.countTrueInWindow(1, 0, 2) == 4
        table.countTrueInWindow(1, 1, 2) == 6
        table.countTrueInWindow(1, 2, 2) == 8
        table.countTrueInWindow(1, 3, 2) == 6
        table.countTrueInWindow(1, 4, 2) == 4
        table.countTrueInWindow(2, 0, 2) == 5
        table.countTrueInWindow(2, 1, 2) == 7
        table.countTrueInWindow(2, 2, 2) == 9
        table.countTrueInWindow(2, 3, 2) == 7
        table.countTrueInWindow(2, 4, 2) == 5
        table.countTrueInWindow(3, 0, 2) == 4
        table.countTrueInWindow(3, 1, 2) == 6
        table.countTrueInWindow(3, 2, 2) == 7
        table.countTrueInWindow(3, 3, 2) == 6
        table.countTrueInWindow(3, 4, 2) == 4
        table.countTrueInWindow(4, 0, 2) == 3
        table.countTrueInWindow(4, 1, 2) == 4
        table.countTrueInWindow(4, 2, 2) == 5
        table.countTrueInWindow(4, 3, 2) == 4
        table.countTrueInWindow(4, 4, 2) == 3
    }

    def 'should handle radius 0 - just the cell itself'() {
        given:
        def input = new boolean[][]{
                [true, false],
                [false, true],
        }
        def table = new SummedAreaTable(input)

        expect:
        table.countTrueInWindow(0, 0, 0) == 1
        table.countTrueInWindow(0, 1, 0) == 0
        table.countTrueInWindow(1, 0, 0) == 0
        table.countTrueInWindow(1, 1, 0) == 1
    }

    def 'should handle a radius far larger than the matrix - clips to the whole grid'() {
        given:
        def input = new boolean[][]{
                [true, false, true],
                [false, true, false],
                [true, false, true],
        }
        def table = new SummedAreaTable(input)

        expect:
        table.countTrueInWindow(1, 1, 100) == 5
        table.countTrueInWindow(0, 0, 100) == 5
    }

    def 'should handle a single-cell matrix'() {
        given:
        def table = new SummedAreaTable([[true]] as boolean[][])

        expect:
        table.countTrueInWindow(0, 0, 0) == 1
        table.countTrueInWindow(0, 0, 5) == 1
    }

    def 'should agree with NeighbourCounter (the reference implementation) across many random matrices, positions and radii'() {
        given:
        def random = ThreadLocalRandom.current()

        expect:
        50.times {
            int size = 5 + random.nextInt(40)
            boolean[][] matrix = new boolean[size][size]
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    matrix[i][j] = random.nextBoolean()
                }
            }
            def table = new SummedAreaTable(matrix)

            for (int check = 0; check < 20; check++) {
                int x = random.nextInt(size)
                int y = random.nextInt(size)
                int radius = random.nextInt(size + 2) // sometimes larger than the grid on purpose

                int expected = NeighbourCounter.numberOfTrueNeighbours(x, y, radius, matrix)
                int actual = table.countTrueInWindow(x, y, radius)
                assert actual == expected: "mismatch at size=$size x=$x y=$y radius=$radius"
            }
        }
        true
    }
}
