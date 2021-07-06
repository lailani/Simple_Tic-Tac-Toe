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

    static char whoseMove = 'X';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] cells = {{'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}};

        startGame(scanner, cells);

        askToEnterCoordinatesAndCheckThem(scanner, cells);


    }

    public static void startGame(Scanner scanner, char[][] cells) {
        printCells(cells);
        askToEnterCoordinatesAndCheckThem(scanner, cells);
    }

    public static void askToEnterCoordinatesAndCheckThem(Scanner scanner, char[][] cells) {
        System.out.println("Enter the coordinates: ");
        String xy = scanner.nextLine().replaceAll(" ", "");

        if (!isInputCoordinatesNumbers(xy)) {
            System.out.println("You should enter numbers!");
            askToEnterCoordinatesAndCheckThem(scanner, cells);
        } else if (isInputCoordinatesOutOf1to3(xy)) {
            System.out.println("Coordinates should be from 1 to 3!");
            askToEnterCoordinatesAndCheckThem(scanner, cells);
        } else if (isCellOccupy(cells, xy)) {
            System.out.println("This cell is occupied! Choose another one!");
            askToEnterCoordinatesAndCheckThem(scanner, cells);
        } else {
            makeMove(cells, xy);
            printCells(cells);
            printResult(cells);
            askToEnterCoordinatesAndCheckThem(scanner, cells);

        }
    }

    public static void makeMove(char[][] cells, String xy) {
        int x = Character.getNumericValue(xy.charAt(0));
        int y = Character.getNumericValue(xy.charAt(1));
        cells[x - 1][y - 1] = whoseMove;
        changeMove();
    }

    public static void changeMove() {
        if (whoseMove == 'X') {
            whoseMove = 'O';
        } else if (whoseMove == 'O') {
            whoseMove = 'X';
        }
    }

    public static boolean isInputCoordinatesNumbers(String xy) {
        return xy.matches("\\d+");
    }

    public static boolean isInputCoordinatesOutOf1to3(String xy) {
        int x = Character.getNumericValue(xy.charAt(0));
        int y = Character.getNumericValue(xy.charAt(1));
        return x < 1 || x > 3 || y < 1 || y > 3;
    }

    public static boolean isCellOccupy(char[][] cells, String xy) {
        int x = Character.getNumericValue(xy.charAt(0));
        int y = Character.getNumericValue(xy.charAt(1));
        return cells[x - 1][y - 1] == 'X' || cells[x - 1][y - 1] == 'O';
    }


    public static void printCells(char[][] cells) {
        System.out.println("---------");
        System.out.printf("| %c %c %c |\n", cells[0][0], cells[0][1], cells[0][2]);
        System.out.printf("| %c %c %c |\n", cells[1][0], cells[1][1], cells[1][2]);
        System.out.printf("| %c %c %c |\n", cells[2][0], cells[2][1], cells[2][2]);
        System.out.println("---------");
    }

    public static void printResult(char[][] cells) {
        String st = String.valueOf(cells[0]) + String.valueOf(cells[1]) + String.valueOf(cells[2]);
        String LineForCountO = st;
        String LineForCountX = st;
        int countO = LineForCountO.replaceAll("O", "").length();
        int countX = LineForCountX.replaceAll("X", "").length();

        if (isOWins(st) && isXWins(st) || Math.abs(countO - countX) > 1) {
            System.out.println("Impossible");
        } else if (isXWins(st) && !isOWins(st)) {
            System.out.println("X wins");
            System.exit(0);
        } else if (!isXWins(st) && isOWins(st)) {
            System.out.println("O wins");
            System.exit(0);
        } else if (!isEmpty(st) && !isOWins(st) && !isXWins(st)) {
            System.out.println("Draw");
            System.exit(0);
        }
//        else if (isEmpty(st) && !isOWins(st) && !isXWins(st)) {
//            System.out.println("Game not finished");
//        }

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
