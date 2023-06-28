package io.puzzle.fifteen.board;

import io.puzzle.fifteen.exception.TileException;

import java.util.*;
import java.util.stream.IntStream;

public class Puzzle {
    private final int height;
    private final int width;

    private final int[][] board;

    public Puzzle() {
        this(4, 4);
    }

    public Puzzle(int height, int width) {
        this.height = height;
        this.width = width;
        this.board = new int[this.height][this.width];
        initBoard();
    }

    Puzzle(int[][] board) {
        this.board = board;
        this.height = board.length;
        if (board.length > 0) {
            this.width = board[0].length;
        } else {
            this.width = 0;
        }
    }

    private void initBoard() {
        LinkedList<Integer> tiles = new LinkedList<>(
                IntStream.
                range(0, this.height * this.width).
                boxed().
                toList()
        );

        tiles.add(tiles.pollFirst());

        while (tiles.getFirst() == 1) {
            Collections.shuffle(tiles);
        }

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                this.board[y][x] = Objects.requireNonNull(tiles.poll());
            }
        }
    }

    public void moveTile(Coordinate source, Direction direction) {
        try {
            var target = source.getNeighbour(direction);

            if (!isEmpty(target)) {
                throw new TileException("Target not empty!");
            } else if (isEmpty(source)){
                throw new TileException("Source empty!");
            } else {
                board[target.y()][target.x()] = board[source.y()][source.x()];
                board[source.y()][source.x()] = 0;
            }
        } catch (IndexOutOfBoundsException | IllegalArgumentException | TileException e) {
            System.out.println("Can't move tile, reason: " + e.getMessage());
        }
    }

    public boolean isSolved() {
        int height = board.length;
        if (height == 0) {
            return false;
        }
        int width = board[0].length;

        //fail fast check of upper left corner and lower right corner
        if (board[0][0] != 1 || board[height - 1][width - 1] != 0) {
            return false;
        } else {
            //deep check
            int prev = 0;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (board[y][x] == prev + 1 || board[y][x] == 0) {
                        prev++;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isEmpty(Coordinate coordinate) {
        return this.board[coordinate.y()][coordinate.x()] == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int h = 0; h < this.height; h++) {
            for (int w = 0; w < this.width; w++) {
                sb.append('|').
                        append(this.board[h][w] != 0 ? this.board[h][w] : " ").
                        append('\t');
            }
            sb.append('|').append('\n');
        }
        return sb.toString();
    }

    //bcs implementing deep copy of 2d array wasn't pretty:)
    int[][] getBoard() {
        return this.board;
    }
}
