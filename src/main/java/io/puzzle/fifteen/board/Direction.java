package io.puzzle.fifteen.board;

public enum Direction {
    LEFT,
    UP,
    DOWN,
    RIGHT;

    public static Direction fromString(String str) {
        return valueOf(str.toUpperCase());
    }
}
