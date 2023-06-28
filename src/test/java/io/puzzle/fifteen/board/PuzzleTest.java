package io.puzzle.fifteen.board;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleTest {
    private static final int[][] SOLVED_BOARD = {
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12},
        {13, 14, 15, 0}
    };

    private static final int[][] UNSOLVED_BOARD = {
            {0, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 1}
    };

    @Test
    void testToString() {
        int height = 4, width = 4;
        var board = new Puzzle(height, width);
        var result = board.toString();
        System.out.println(result);
        //'0' should be printed as empty
        assertFalse(result.contains("|0\t"));

        //not expecting values greater than (height * width - 1)
        assertFalse(
                result.contains(String.valueOf(height * width))
        );
        assertTrue(
                result.contains(String.valueOf(height * width - 1))
        );
    }

    @Test
    void testBoardUniqueness() {
        var stateCopyFlat = Arrays.stream(new Puzzle().getBoard()).
                flatMapToInt(Arrays::stream).
                toArray();
        var distinctElementsState = Arrays.stream(stateCopyFlat).
                distinct().
                toArray();
        assertEquals(stateCopyFlat.length, distinctElementsState.length);
    }

    @Test
    void testBoardPuzzleSolvedSuccess() {
        Puzzle puzzle = new Puzzle(SOLVED_BOARD);
        assertTrue(puzzle.isSolved());
    }

    @Test
    void testIsBoardPuzzleSolvedFailsWhenNotSolved() {
        Puzzle puzzle = new Puzzle(UNSOLVED_BOARD);
        assertFalse(puzzle.isSolved());
    }

    @Test
    void testIsBoardPuzzleSolvedFailsForNewPuzzle() {
        Puzzle puzzle = new Puzzle();
        assertFalse(puzzle.isSolved());
    }

    @Test
    void testMoveTileSuccess() {
        int[][] initialBoard = {
                {0, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 1}
        };
        Puzzle puzzle = new Puzzle(initialBoard);
        puzzle.moveTile(new Coordinate(1, 0), Direction.LEFT);
        var board = puzzle.getBoard();
        assertEquals(0, board[0][1]);
        assertEquals(2, board[0][0]);
    }

    @Test
    void testMoveTileTargetNotEmptyFail() {
        int[][] initialBoard = {
                {0, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 1}
        };
        Puzzle puzzle = new Puzzle(initialBoard);
        puzzle.moveTile(new Coordinate(2, 0), Direction.LEFT);
        var board = puzzle.getBoard();
        assertEquals(UNSOLVED_BOARD[0][2], board[0][2]);
        assertEquals(UNSOLVED_BOARD[0][1], board[0][1]);
    }

    @Test
    void testMoveTileSourceEmptyFail() {
        int[][] initialBoard = {
                {0, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 1}
        };
        Puzzle puzzle = new Puzzle(initialBoard);
        puzzle.moveTile(new Coordinate(0, 0), Direction.RIGHT);
        var board = puzzle.getBoard();
        assertEquals(0, board[0][0]);
        assertEquals(2, board[0][1]);
    }
}