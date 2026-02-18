package br.com.dvlprmatheus;

import br.com.dvlprmatheus.model.Board;
import br.com.dvlprmatheus.model.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static br.com.dvlprmatheus.utils.BoardTemplate.BOARD_TEMPLATE;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;

public class Main {

    private static final int BOARD_SIZE = 9;
    private static final String POSITION_EMPTY = "0,false";

    private static final int OP_START = 1;
    private static final int OP_PUT_NUMBER = 2;
    private static final int OP_REMOVE_NUMBER = 3;
    private static final int OP_SHOW_BOARD = 4;
    private static final int OP_SHOW_STATUS = 5;
    private static final int OP_CLEAR = 6;
    private static final int OP_FINISH = 7;
    private static final int OP_EXIT = 8;

    private static Board board;

    public static void main(String[] args) {
        Map<String, String> positions = parsePositions(args);

        try (Scanner scanner = new Scanner(System.in)) {
            runGameLoop(scanner, positions);
        }
    }

    private static Map<String, String> parsePositions(String[] args) {
        if (args == null || args.length == 0) {
            return Map.of();
        }
        return Stream.of(args)
                .collect(toMap(
                        k -> k.split(";")[0],
                        v -> v.split(";")[1]
                ));
    }

    private static void runGameLoop(Scanner scanner, Map<String, String> positions) {
        while (true) {
            printMenu();
            int option = readIntOption(scanner);

            switch (option) {
                case OP_START -> startGame(scanner, positions);
                case OP_PUT_NUMBER -> inputNumber(scanner);
                case OP_REMOVE_NUMBER -> removeNumber(scanner);
                case OP_SHOW_BOARD -> showCurrentGame();
                case OP_SHOW_STATUS -> showGameStatus();
                case OP_CLEAR -> clearGame(scanner);
                case OP_FINISH -> finishGame();
                case OP_EXIT -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Choose one of the menu options.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Sudoku ---");
        System.out.println("1 - Start new game");
        System.out.println("2 - Put number");
        System.out.println("3 - Remove number");
        System.out.println("4 - View board");
        System.out.println("5 - Check game status");
        System.out.println("6 - Clear game");
        System.out.println("7 - Finish game");
        System.out.println("8 - Exit");
        System.out.print("Option: ");
    }

    private static int readIntOption(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }

    private static boolean ensureGameStarted() {
        if (isNull(board)) {
            System.out.println("Game has not been started yet. Choose option 1 to begin.");
            return false;
        }
        return true;
    }

    private static void startGame(Scanner scanner, Map<String, String> positions) {
        if (nonNull(board)) {
            System.out.println("Game has already been started.");
            return;
        }

        List<List<Space>> spaces = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_SIZE; j++) {
                String key = "%s,%s".formatted(i, j);
                String positionConfig = nonNull(positions.get(key)) ? positions.get(key) : POSITION_EMPTY;
                String[] parts = positionConfig.split(",");
                int expected = Integer.parseInt(parts[0]);
                boolean fixed = Boolean.parseBoolean(parts[1]);
                spaces.get(i).add(new Space(expected, fixed));
            }
        }

        board = new Board(spaces);
        System.out.println("Game is ready to start!");
    }

    private static void inputNumber(Scanner scanner) {
        if (!ensureGameStarted()) return;

        int col = readNumberInRange(scanner, "Enter column (0 to 8)", 0, 8);
        int row = readNumberInRange(scanner, "Enter row (0 to 8)", 0, 8);
        int value = readNumberInRange(scanner, "Enter number for position [%d,%d] (1 to 9)".formatted(col, row), 1, 9);

        if (!board.changeValue(col, row, value)) {
            System.out.printf("Position [%d,%d] is fixed and cannot be changed.%n", col, row);
        }
    }

    private static void removeNumber(Scanner scanner) {
        if (!ensureGameStarted()) return;

        System.out.println("Enter column of cell to remove (0 to 8)");
        int col = readNumberInRange(scanner, null, 0, 8);
        System.out.println("Enter row of cell to remove (0 to 8)");
        int row = readNumberInRange(scanner, null, 0, 8);

        if (!board.clearValue(col, row)) {
            System.out.printf("Position [%d,%d] is fixed and cannot be changed.%n", col, row);
        }
    }

    private static void showCurrentGame() {
        if (!ensureGameStarted()) return;

        Object[] args = new Object[81];
        int argPos = 0;
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (var column : board.getSpaces()) {
                Integer actual = column.get(row).getActual();
                args[argPos++] = " " + (isNull(actual) ? " " : actual);
            }
        }
        System.out.println("Current board:");
        System.out.printf(BOARD_TEMPLATE + "%n", args);
    }

    private static void showGameStatus() {
        if (!ensureGameStarted()) return;

        System.out.printf("Current status: %s%n", board.getStatus().getLabel());
        System.out.println(board.hasErrors() ? "The board contains errors." : "The board has no errors.");
    }

    private static void clearGame(Scanner scanner) {
        if (!ensureGameStarted()) return;

        readNextLine(scanner);
        System.out.print("Are you sure you want to clear the game and lose all progress? (y/n): ");
        String confirm = readNextLine(scanner).trim().toLowerCase();

        while (!confirm.equals("y") && !confirm.equals("n") && !confirm.equals("yes") && !confirm.equals("no")) {
            System.out.print("Enter 'y' or 'n': ");
            confirm = readNextLine(scanner).trim().toLowerCase();
        }

        if (confirm.equals("y") || confirm.equals("yes")) {
            board.reset();
            System.out.println("Game cleared.");
        }
    }

    private static void finishGame() {
        if (!ensureGameStarted()) return;

        if (board.gameIsFinished()) {
            System.out.println("Congratulations! You finished the game.");
            showCurrentGame();
            board = null;
        } else if (board.hasErrors()) {
            System.out.println("The board still has errors. Review and fix them.");
        } else {
            System.out.println("There are still cells to fill.");
        }
    }

    private static String readNextLine(Scanner scanner) {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return "";
    }

    private static int readNumberInRange(Scanner scanner, String prompt, int min, int max) {
        if (prompt != null) {
            System.out.println(prompt);
        }
        while (true) {
            try {
                int value = scanner.nextInt();
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("Enter a number between %d and %d.%n", min, max);
            } catch (Exception e) {
                scanner.nextLine();
                System.out.printf("Invalid value. Enter a number between %d and %d.%n", min, max);
            }
        }
    }
}
