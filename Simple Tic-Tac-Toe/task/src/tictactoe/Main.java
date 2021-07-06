package tictactoe;

import java.util.Arrays;
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
//        System.out.println("---------");
//        System.out.printf("| %c %c %c |\n", st.charAt(0), st.charAt(1), st.charAt(2));
//        System.out.printf("| %c %c %c |\n", st.charAt(3), st.charAt(4), st.charAt(5));
//        System.out.printf("| %c %c %c |\n", st.charAt(6), st.charAt(7), st.charAt(8));
//        System.out.println("---------");
        char[][] cells = {{st.charAt(0), st.charAt(1), st.charAt(2)},
                {st.charAt(3), st.charAt(4), st.charAt(5)},
                {st.charAt(6), st.charAt(7), st.charAt(8)}};
        printCells(cells);


        System.out.println("Enter the coordinates: ");
        String xSt = scanner.nextLine();
        String ySt = scanner.nextLine();

        if (!xSt.matches("\\d") || !ySt.matches("\\d")) {
            System.out.println("You should enter numbers!");
        } else {
            int x = Integer.parseInt(xSt);
            int y = Integer.parseInt(ySt);
            if (x < 1 || x > 3 || y < 1 || y > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (cells[x - 1][y - 1] == 'X' || cells[x - 1][y - 1] == 'O') {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                cells[x - 1][y - 1] = 'X';
                printCells(cells);
            }
        }
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
