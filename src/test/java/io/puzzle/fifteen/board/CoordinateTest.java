package io.puzzle.fifteen.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    private static final Coordinate COORDINATE = new Coordinate(1, 1);

    @Test
    void getNeighbourUp() {
        var neighbour = COORDINATE.getNeighbour(Direction.UP);
        assertEquals(COORDINATE.x(), neighbour.x());
        assertEquals(COORDINATE.y() - 1, neighbour.y());
    }

    @Test
    void getNeighbourDown() {
        var neighbour = COORDINATE.getNeighbour(Direction.DOWN);
        assertEquals(COORDINATE.x(), neighbour.x());
        assertEquals(COORDINATE.y() + 1, neighbour.y());
    }

    @Test
    void getNeighbourLeft() {
        var neighbour = COORDINATE.getNeighbour(Direction.LEFT);
        assertEquals(COORDINATE.x() - 1, neighbour.x());
        assertEquals(COORDINATE.y(), neighbour.y());
    }

    @Test
    void getNeighbourRight() {
        var neighbour = COORDINATE.getNeighbour(Direction.RIGHT);
        assertEquals(COORDINATE.x() + 1, neighbour.x());
        assertEquals(COORDINATE.y(), neighbour.y());
    }
}