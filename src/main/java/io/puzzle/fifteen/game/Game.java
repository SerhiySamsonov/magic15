package io.puzzle.fifteen.game;

import io.puzzle.fifteen.board.Coordinate;
import io.puzzle.fifteen.board.Direction;
import io.puzzle.fifteen.board.Puzzle;

import java.util.Scanner;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private final static String HELP = "help";
    private final static String START = "start";
    private final static String EXIT = "exit";
    private static final String WELCOME = """
            Welcome to 15 puzzle game!
            _________________________________________________________
            To solve the puzzle you need to order tiles from 1 to 15,
            where tile number 1 is at the top left corner
            and the 'empty' one is at the bottom right corner.
            _________________________________________________________
            """;
    private static final String HELP_TEXT = """
            To move a tile specify its vertical/horizontal coordinates and direction
            you want to move it to separated by comas, e.g.:
            
            ----->
            |4	|3	|10	|14	|
            |8	|9	|2	|12	|
            |11	| 	|7	|15	|
            |5	|13	|6	|1	|
            <-----
            3, 1, right
            ----->
            |4	|3	|10	|14	|
            |8	|9	|2	|12	|
            |	|11 |7	|15	|
            |5	|13	|6	|1	|
            _________________________________________________________
            """;
    private static final String HINT_COMMANDS = """
            Commands:
                help - see hints
                exit - leave
                start - start the game
            _________________________________________________________
            """;
    private static final String SOLVED = """
            *********************************************************
            You did it! The puzzle is solved
            *********************************************************
            """;

    public void launch() {
        try {
            System.out.println(WELCOME);
            System.out.println(HINT_COMMANDS);
            while (scanner.hasNextLine()) {
                var input = scanner.nextLine();
                switch (input.trim().toLowerCase()) {
                    case HELP -> System.out.println(HELP_TEXT);
                    case START -> startPuzzle();
                    case EXIT -> System.exit(0);
                    default -> System.out.println(HINT_COMMANDS);
                }
            }

        } catch (Throwable e) {
            System.out.println("Game crashed:( Reason: " + e);
        }
    }

    public void startPuzzle() {
        Puzzle puzzle = new Puzzle();
        System.out.println(puzzle);
        while (scanner.hasNextLine() || puzzle.isSolved()) {
            var inputs = scanner.nextLine().split(",");
            int y = Integer.parseInt(inputs[0].trim());
            int x = Integer.parseInt(inputs[1].trim());
            Direction direction = Direction.fromString(inputs[2].trim());
            puzzle.moveTile(new Coordinate(x - 1, y - 1), direction);
            System.out.println(puzzle);
        }
        System.out.println(SOLVED);
    }

}
