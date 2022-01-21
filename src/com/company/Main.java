package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static int creatingRow;
    static int creatingCol;
    static int mines;
    static String minesSymbol = "*";
    static String markSymbol = "!";

    public static void main(String[] args) {
        gameName();
        playGame(getBoard());
    }

    public static void gameName() {
        for (int i = 0; i < 32; i++) {
            System.out.print(" ");
        }
        System.out.print("MINES");
        for (int i = 0; i < 32; i++) {
            System.out.print(" ");
        }
        System.out.println();
        for (int i = 0; i < 70; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    public static void playGame(String[][] board) {
        while (!isGameOver()) {
            playRound(board);
        }
    }

    public static String[][] getBoard() {
        System.out.println("Welcome! Please input two digits from 1 to 10 to create your own board.");
        getSizesOFTheBoard();
        String[][] board = new String[creatingRow][creatingCol];
        estimateMines();
        printBoard(board, creatingRow, creatingCol);
        return board;
    }

    public static void getSizesOFTheBoard() {
        System.out.print("Enter creatingRow = ");
        creatingRow = getNumber();
        System.out.print("Enter creatingCol = ");
        creatingCol = getNumber();
        if (creatingRow > 10 || creatingCol > 10) {
            System.out.println("Invalid input! Try again");
            System.out.println();
            getSizesOFTheBoard();
        }
    }

    public static void estimateMines() {
        Random random = new Random();
        mines = random.nextInt(10) - 1;
        if (mines <= 0) {
            estimateMines();
        }
    }

    public static void printBoard(String[][] board, int row, int col) {
        printNumberOfMines(mines);
        printTheUpperIndexes(board, row, col);
        printTheRows(board, row, col);
        System.out.println();
    }

    public static void printNumberOfMines(int mines) {
        for (int i = 0; i < 30; i++) {
            System.out.print("_");
        }
        System.out.println();
        System.out.print("There are" + " " + Main.mines + " " + "mines on the board!");
        System.out.println();
    }

    public static void printTheUpperIndexes(String[][] board, int row, int col) {
        System.out.print("   ");
        for (col = 0; col < board[0].length; col++) {
            System.out.print(" ");
            System.out.print(col);
        }
        System.out.println();
        System.out.print("   ");
        for (col = 0; col < board[0].length; col++) {
            System.out.print(" ");
            System.out.print("_");
        }
    }

    public static void printTheRows(String[][] board, int row, int col) {
        for (row = 0; row < board.length; row++) {
            System.out.println("   ");
            System.out.print(" " + row + "|");
            for (col = 0; col < board[0].length; col++) {
                board[row][col] = ".";
                System.out.print(" ");
                System.out.print(board[row][col]);
            }
        }
        System.out.println();
    }

    public static boolean isGameOver() {
        if (minesSymbol.equals(markSymbol)) {
            return true;
        }
        return false;
    }

    public static void playRound(String[][] board) {
        System.out.println("Enter 1 to OPEN a hidden square");
        System.out.println("Enter 2 to MARK a hidden square");
        int option = getNumber();
        switch (option) {
            case 1:
                openHiddenSquare(board);
                break;
            case 2:
                markHiddenSquare(board);
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }

    public static void openHiddenSquare(String[][] board) {
        System.out.print("Enter row = ");
        int row = getNumber();
        System.out.print("Enter col = ");
        int col = getNumber();
        if (row > creatingRow || col > creatingCol) {
            System.out.println("There are no such coordinates");
        } else {
            printTheNumbers(board, row, col);
        }
    }

    public static void printTheNumbers(String[][] board, int row, int col) {
        int counter = 0;
        for (row = 0; row < board.length; row++) {
            for (col = 0; col < board[0].length; col++) {
                if (row > 0 && col > 0) {
                    if (board[row - 1][col - 1].equals(minesSymbol) || board[row - 1][col].equals(minesSymbol) || board[row - 1][col + 1].equals(minesSymbol)) {
                        counter++;
                    } else if ((board[row][col - 1].equals(minesSymbol)) || board[row][col + 1].equals(minesSymbol)) {
                        counter++;
                    } else if ((board[row + 1][col - 1].equals(minesSymbol)) || board[row + 1][col].equals(minesSymbol) || board[row + 1][col + 1].equals(minesSymbol)) {
                        counter++;
                    }
                    board[row][col] = String.valueOf(counter);
                }

            }
        }
    }

    public static void markHiddenSquare(String[][] board) {
        System.out.print("Enter row = ");
        int row = getNumber();
        System.out.print("Enter col = ");
        int col = getNumber();
        if (row > creatingRow || col > creatingCol) {
            System.out.println("There are no such coordinates!");
        } else {
            putTheMinesOnTheBoard(board);
            board[row][col] = markSymbol;
            printBoard(board, row, col);
        }
    }

    public static void putTheMinesOnTheBoard(String[][] board) {
        Random random = new Random();
        int row = 0;
        int col = 0;
        int counter = 0;
        while (counter <= mines) {
            row = random.nextInt(creatingRow);
            col = random.nextInt(creatingCol);
            board[row][col] = minesSymbol;
            counter++;
        }
        if (isGameOver())
            printBoard(board, row, col);
    }

    public static int getNumber() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
