package tictactoe;

import java.util.Scanner;

public class Main {
    static int[][] winCells = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String st = scanner.nextLine();
        char[][] cells = {{st.charAt(0), st.charAt(1), st.charAt(2)},
                {st.charAt(3), st.charAt(4), st.charAt(5)},
                {st.charAt(6), st.charAt(7), st.charAt(8)}};
        printCells(cells);

        askToEnterCoordinatesAndCheckThem(scanner, cells);


    }

    public static void askToEnterCoordinatesAndCheckThem(Scanner scanner, char[][] cells) {
        System.out.println("Enter the coordinates: ");
        String xy = scanner.nextLine().replaceAll(" ", "");

        if (!isNumbers(xy)) {
            System.out.println("You should enter numbers!");
            askToEnterCoordinatesAndCheckThem(scanner, cells);
        } else {
            int x = Character.getNumericValue(xy.charAt(0));
            int y = Character.getNumericValue(xy.charAt(1));
            if (x < 1 || x > 3 || y < 1 || y > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                askToEnterCoordinatesAndCheckThem(scanner, cells);
            } else if (cells[x - 1][y - 1] == 'X' || cells[x - 1][y - 1] == 'O') {
                System.out.println("This cell is occupied! Choose another one!");
                askToEnterCoordinatesAndCheckThem(scanner, cells);
            } else {
                cells[x - 1][y - 1] = 'X';
                printCells(cells);
            }
        }
    }

    public static boolean isNumbers(String xy) {
        return xy.matches("\\d+");
    }


    public static void printCells(char[][] cells) {
        System.out.println("---------");
        System.out.printf("| %c %c %c |\n", cells[0][0], cells[0][1], cells[0][2]);
        System.out.printf("| %c %c %c |\n", cells[1][0], cells[1][1], cells[1][2]);
        System.out.printf("| %c %c %c |\n", cells[2][0], cells[2][1], cells[2][2]);
        System.out.println("---------");
    }

    public static void printResult(String st) {

        String LineForCountO = st;
        String LineForCountX = st;
        int countO = LineForCountO.replaceAll("O", "").length();
        int countX = LineForCountX.replaceAll("X", "").length();

        if (isOWins(st) && isXWins(st) || Math.abs(countO - countX) > 1) {
            System.out.println("Impossible");
        } else if (isXWins(st) && !isOWins(st)) {
            System.out.println("X wins");
        } else if (!isXWins(st) && isOWins(st)) {
            System.out.println("O wins");
        } else if (!isEmpty(st) && !isOWins(st) && !isXWins(st)) {
            System.out.println("Draw");
        } else if (isEmpty(st) && !isOWins(st) && !isXWins(st)) {
            System.out.println("Game not finished");
        }

    }

    public static boolean isEmpty(String st) {
        return st.contains("_");
    }

    public static boolean isOWins(String st) {
        for (int[] winC : winCells) {
            if (st.charAt(winC[0]) == 'O' && st.charAt(winC[1]) == 'O' && st.charAt(winC[2]) == 'O') {
                return true;
            }
        }
        return false;
    }

    public static boolean isXWins(String st) {
        for (int[] winC : winCells) {
            if (st.charAt(winC[0]) == 'X' && st.charAt(winC[1]) == 'X' && st.charAt(winC[2]) == 'X') {
                return true;
            }
        }
        return false;
    }

}
