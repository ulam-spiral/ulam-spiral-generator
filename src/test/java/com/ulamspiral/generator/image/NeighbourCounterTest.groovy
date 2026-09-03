package com.ulamspiral.generator.image

import spock.lang.Specification

class NeighbourCounterTest extends Specification {

    def 'should properly count the number of true cells within radius'() {
        given:
        def input = new boolean[][]{
                [false, false, false],
                [false, false, false],
                [false, false, false],
        }

        expect:
        NeighbourCounter.numberOfTrueNeighbours(0, 0, 1, input) == 0
        NeighbourCounter.numberOfTrueNeighbours(0, 1, 1, input) == 0
        NeighbourCounter.numberOfTrueNeighbours(0, 2, 1, input) == 0

        NeighbourCounter.numberOfTrueNeighbours(1, 0, 1, input) == 0
        NeighbourCounter.numberOfTrueNeighbours(1, 1, 1, input) == 0
        NeighbourCounter.numberOfTrueNeighbours(1, 2, 1, input) == 0

        NeighbourCounter.numberOfTrueNeighbours(2, 0, 1, input) == 0
        NeighbourCounter.numberOfTrueNeighbours(2, 1, 1, input) == 0
        NeighbourCounter.numberOfTrueNeighbours(2, 2, 1, input) == 0
    }

    def 'should properly count the number of true cells within radius - 2'() {
        given:
        def input = new boolean[][]{
                [false, false, false],
                [false, true, false],
                [false, false, false],
        }

        expect:
        NeighbourCounter.numberOfTrueNeighbours(0, 0, 1, input) == 1
        NeighbourCounter.numberOfTrueNeighbours(0, 1, 1, input) == 1
        NeighbourCounter.numberOfTrueNeighbours(0, 2, 1, input) == 1

        NeighbourCounter.numberOfTrueNeighbours(1, 0, 1, input) == 1
        NeighbourCounter.numberOfTrueNeighbours(1, 1, 1, input) == 1
        NeighbourCounter.numberOfTrueNeighbours(1, 2, 1, input) == 1

        NeighbourCounter.numberOfTrueNeighbours(2, 0, 1, input) == 1
        NeighbourCounter.numberOfTrueNeighbours(2, 1, 1, input) == 1
        NeighbourCounter.numberOfTrueNeighbours(2, 2, 1, input) == 1
    }

    def 'should properly count the number of true cells within radius - 3'() {
        given:
        def input = new boolean[][]{
                [true, false, false],
                [false, true, false],
                [false, false, false],
        }

        expect:
        NeighbourCounter.numberOfTrueNeighbours(0, 0, 1, input) == 2
        NeighbourCounter.numberOfTrueNeighbours(0, 1, 1, input) == 2
        NeighbourCounter.numberOfTrueNeighbours(0, 2, 1, input) == 1

        NeighbourCounter.numberOfTrueNeighbours(1, 0, 1, input) == 2
        NeighbourCounter.numberOfTrueNeighbours(1, 1, 1, input) == 2
        NeighbourCounter.numberOfTrueNeighbours(1, 2, 1, input) == 1

        NeighbourCounter.numberOfTrueNeighbours(2, 0, 1, input) == 1
        NeighbourCounter.numberOfTrueNeighbours(2, 1, 1, input) == 1
        NeighbourCounter.numberOfTrueNeighbours(2, 2, 1, input) == 1
    }

    def 'should properly count the number of true cells within radius - 4'() {
        given:
        def input = new boolean[][]{
                [true, true, true],
                [true, true, true],
                [true, true, true],
        }

        expect:
        NeighbourCounter.numberOfTrueNeighbours(0, 0, 1, input) == 4
        NeighbourCounter.numberOfTrueNeighbours(0, 1, 1, input) == 6
        NeighbourCounter.numberOfTrueNeighbours(0, 2, 1, input) == 4

        NeighbourCounter.numberOfTrueNeighbours(1, 0, 1, input) == 6
        NeighbourCounter.numberOfTrueNeighbours(1, 1, 1, input) == 9
        NeighbourCounter.numberOfTrueNeighbours(1, 2, 1, input) == 6

        NeighbourCounter.numberOfTrueNeighbours(2, 0, 1, input) == 4
        NeighbourCounter.numberOfTrueNeighbours(2, 1, 1, input) == 6
        NeighbourCounter.numberOfTrueNeighbours(2, 2, 1, input) == 4
    }

    def 'should properly count the number of true cells within radius - 5'() {
        given:
        def input = new boolean[][]{
                [true, false, false, false, true],
                [false, true, false, true, false],
                [true, false, false, true, true],
                [false, true, false, false, false],
                [false, false, true, false, false],
        }

        expect:
        NeighbourCounter.numberOfTrueNeighbours(0, 0, 1, input) == 2
        NeighbourCounter.numberOfTrueNeighbours(0, 1, 1, input) == 2
        NeighbourCounter.numberOfTrueNeighbours(0, 2, 1, input) == 2
        NeighbourCounter.numberOfTrueNeighbours(0, 3, 1, input) == 2
        NeighbourCounter.numberOfTrueNeighbours(0, 4, 1, input) == 2

        NeighbourCounter.numberOfTrueNeighbours(1, 0, 1, input) == 3
        NeighbourCounter.numberOfTrueNeighbours(1, 1, 1, input) == 3
        NeighbourCounter.numberOfTrueNeighbours(1, 2, 1, input) == 3
        NeighbourCounter.numberOfTrueNeighbours(1, 3, 1, input) == 4
        NeighbourCounter.numberOfTrueNeighbours(1, 4, 1, input) == 4

        NeighbourCounter.numberOfTrueNeighbours(2, 0, 1, input) == 3
        NeighbourCounter.numberOfTrueNeighbours(2, 1, 1, input) == 3
        NeighbourCounter.numberOfTrueNeighbours(2, 2, 1, input) == 4
        NeighbourCounter.numberOfTrueNeighbours(2, 3, 1, input) == 3
        NeighbourCounter.numberOfTrueNeighbours(2, 4, 1, input) == 3

        NeighbourCounter.numberOfTrueNeighbours(3, 0, 1, input) == 2
        NeighbourCounter.numberOfTrueNeighbours(3, 1, 1, input) == 3
        NeighbourCounter.numberOfTrueNeighbours(3, 2, 1, input) == 3
        NeighbourCounter.numberOfTrueNeighbours(3, 3, 1, input) == 3
        NeighbourCounter.numberOfTrueNeighbours(3, 4, 1, input) == 2

        NeighbourCounter.numberOfTrueNeighbours(4, 0, 1, input) == 1
        NeighbourCounter.numberOfTrueNeighbours(4, 1, 1, input) == 2
        NeighbourCounter.numberOfTrueNeighbours(4, 2, 1, input) == 2
        NeighbourCounter.numberOfTrueNeighbours(4, 3, 1, input) == 1
        NeighbourCounter.numberOfTrueNeighbours(4, 4, 1, input) == 0
    }

    def 'should properly count the number of true cells within radius - 6'() {
        given:
        def input = new boolean[][]{
                [true, false, false, false, true],
                [false, true, false, true, false],
                [true, false, false, true, true],
                [false, true, false, false, false],
                [false, false, true, false, false]
        }

        expect:
        NeighbourCounter.numberOfTrueNeighbours(0, 0, 2, input) == 3
        NeighbourCounter.numberOfTrueNeighbours(0, 1, 2, input) == 5
        NeighbourCounter.numberOfTrueNeighbours(0, 2, 2, input) == 7
        NeighbourCounter.numberOfTrueNeighbours(0, 3, 2, input) == 5
        NeighbourCounter.numberOfTrueNeighbours(0, 4, 2, input) == 4

        NeighbourCounter.numberOfTrueNeighbours(1, 0, 2, input) == 4
        NeighbourCounter.numberOfTrueNeighbours(1, 1, 2, input) == 6
        NeighbourCounter.numberOfTrueNeighbours(1, 2, 2, input) == 8
        NeighbourCounter.numberOfTrueNeighbours(1, 3, 2, input) == 6
        NeighbourCounter.numberOfTrueNeighbours(1, 4, 2, input) == 4

        NeighbourCounter.numberOfTrueNeighbours(2, 0, 2, input) == 5
        NeighbourCounter.numberOfTrueNeighbours(2, 1, 2, input) == 7
        NeighbourCounter.numberOfTrueNeighbours(2, 2, 2, input) == 9
        NeighbourCounter.numberOfTrueNeighbours(2, 3, 2, input) == 7
        NeighbourCounter.numberOfTrueNeighbours(2, 4, 2, input) == 5

        NeighbourCounter.numberOfTrueNeighbours(3, 0, 2, input) == 4
        NeighbourCounter.numberOfTrueNeighbours(3, 1, 2, input) == 6
        NeighbourCounter.numberOfTrueNeighbours(3, 2, 2, input) == 7
        NeighbourCounter.numberOfTrueNeighbours(3, 3, 2, input) == 6
        NeighbourCounter.numberOfTrueNeighbours(3, 4, 2, input) == 4

        NeighbourCounter.numberOfTrueNeighbours(4, 0, 2, input) == 3
        NeighbourCounter.numberOfTrueNeighbours(4, 1, 2, input) == 4
        NeighbourCounter.numberOfTrueNeighbours(4, 2, 2, input) == 5
        NeighbourCounter.numberOfTrueNeighbours(4, 3, 2, input) == 4
        NeighbourCounter.numberOfTrueNeighbours(4, 4, 2, input) == 3
    }

}
