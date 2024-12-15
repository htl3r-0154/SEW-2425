import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Sudoku {

    private static final int SIZE = 9;
    private static final String SOLVED_FILE = "sudoku.solved";

    public static void main(String[] args) throws IOException {
        String folderPath = "resources/Sudokus"; // Ordner mit den Sudoku-Dateien
        solveAllSudokusInFolder(folderPath);
    }

    public static void solveAllSudokusInFolder(String folderPath) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(SOLVED_FILE), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            for (Path path : Files.newDirectoryStream(Paths.get(folderPath), "*.sudoku")) {
                List<String> lines = Files.readAllLines(path);

                for (String line : lines) {
                    String[] parts = line.split(": ");
                    if (parts.length != 2) continue;

                    String name = parts[0];
                    String puzzle = parts[1];

                    System.out.println("Sudoku Name: " + name);
                    printSudoku(puzzle);

                    String solution = solveSudoku(puzzle);

                    System.out.println("Gelöstes Sudoku:");
                    printSudoku(solution);

                    writer.write(name + ";" + puzzle + ";" + solution);
                    writer.newLine();
                }
            }
        }
    }

    public static String solveSudoku(String puzzle) {
        int[][] board = parseSudoku(puzzle);
        if (solve(board)) {
            return formatSudoku(board);
        } else {
            return "Keine Lösung gefunden";
        }
    }

    private static int[][] parseSudoku(String puzzle) {
        int[][] board = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE * SIZE; i++) {
            char c = puzzle.charAt(i);
            board[i / SIZE][i % SIZE] = (c == '.') ? 0 : Character.getNumericValue(c);
        }
        return board;
    }

    private static String formatSudoku(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int num : row) {
                sb.append(num);
            }
        }
        return sb.toString();
    }

    private static void printSudoku(String puzzle) {
        for (int i = 0; i < SIZE; i++) {
            if (i % 3 == 0) {
                System.out.println("+---+---+---+");
            }
            for (int j = 0; j < SIZE; j++) {
                if (j % 3 == 0) System.out.print("|");
                char c = puzzle.charAt(i * SIZE + j);
                System.out.print((c == '.' ? ' ' : c));
            }
            System.out.println("|");
        }
        System.out.println("+---+---+---+");
    }

    private static boolean solve(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solve(board)) {
                                return true;
                            } else {
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValid(int[][] board, int row, int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num || board[row - row % 3 + i / 3][col - col % 3 + i % 3] == num) {
                return false;
            }
        }
        return true;
    }
}
