package com.company;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static int creatingRow;
    static int creatingCol;
    static int mines;
    static String minesSymbol = "*";

    public static void main(String[] args) {
        gameName();
        playGame(getBoard());

    }

    public static void gameName() {
        System.out.println("|>------MINES------<|");
    }

    public static void playGame(String[][] board) {
        boolean isPlayerPlaying = true;
        while (!isGameOver()) {
            playRound(isPlayerPlaying, board);
            isPlayerPlaying = !isPlayerPlaying;

            putTheMinesOnTheBoard(board);
        }
    }

    public static int estimateMines() {
        Random random = new Random();
        mines = random.nextInt(10) - 1;
            return mines;
    }

    public static void printNumberOfMines(int mines) {
        for (int i = 0; i < 30; i++) {
            System.out.print("_");
        }
        System.out.println();
        System.out.print("There are" + " " + Main.mines + " " + "mines on the board!");
    }

    public static void playRound(boolean isPlayerPlaying, String[][] board) {
        System.out.println();
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
        if(row>creatingRow || col>creatingCol){
            System.out.println("There are no such coordinates");
        }else {
           // printOne(board, row, col);
            printTheNumbers(board, row, col);
        }

    }

    public static void printTheNumbers(String[][] board, int row, int col) {
//        int counter = 0;
//        while (counter<=2){

        int counter = 0;
        for (row = 0; row < board.length; row++) {
            for ( col = 0; col < board[0].length; col++) {
                if(row > 0 && col > 0 && board[row][col] != null){
                    if(Objects.equals(board[row - 1][col - 1], minesSymbol) || Objects.equals(board[row - 1][col], minesSymbol) || Objects.equals(board[row - 1][col + 1], minesSymbol)){
                        counter++;
                    }else if(Objects.equals(board[row][col - 1],(minesSymbol)) || Objects.equals(board[row][col + 1],(minesSymbol))){
                        counter++;
                    }else if(Objects.equals(board[row + 1][col - 1], minesSymbol) || Objects.equals(board[row + 1][col], minesSymbol) || Objects.equals(board[row + 1][col + 1], minesSymbol)){
                        counter++;
                    }
                    board[row][col] = String.valueOf(counter);
                }

            }
        }
    }



//    public static void printOne(String[][] board, int row, int col) {
//        if (board[row - 1][col - 1].equals (minesSymbol) || board[row - 1][col].equals(minesSymbol) || board[row - 1][col + 1].equals(minesSymbol)) {
//            board[row][col] = String.valueOf(1);
//            printBoard(board, row, col);
//        } else if (board[row][col - 1].equals(minesSymbol) || board[row][col + 1].equals(minesSymbol)) {
//            board[row][col] = String.valueOf(1);
//            printBoard(board, row, col);
//        } else if (board[row + 1][col - 1].equals(minesSymbol) || board[row + 1][col].equals(minesSymbol) || board[row + 1][col + 1].equals(minesSymbol)) {
//            board[row][col] = String.valueOf(1);
//            printBoard(board, row, col);
//        }
//    }

    public static void markHiddenSquare(String[][] board) {
        String markSymbol = "!";
        System.out.print("Enter row = ");
        int row = getNumber();
        System.out.print("Enter col = ");
        int col = getNumber();
        if(row > creatingRow || col > creatingCol){
            System.out.println("There are no such coordinates!");
        }else {
            putTheMinesOnTheBoard(board);
            board[row][col] = markSymbol;
            printBoard(board, row, col);
        }
    }

    public static void printBoard(String[][] board, int row, int col) {
        printNumberOfMines(mines);
        System.out.println();
        printTheUpperIndexes(board, row, col);
        printTheRows(board, row, col);
    }

    public static void putTheMinesOnTheBoard(String[][] board) {
        Random random = new Random();
        int row = 0;
        int col = 0;
        int counter = 0;
        while (counter < mines) {
            row = random.nextInt(creatingRow);
            col = random.nextInt(creatingCol);
            board[row][col] = minesSymbol;
            counter++;
        }
            printBoard(board, row, col);
    }


    public static String[][] getBoard() {
        for (int i = 0; i < 70; i++) {
            System.out.print("_");
        }
        System.out.println();
        System.out.println("Welcome! Please input two digits from 1 to 10 to create your own board.");
        System.out.print("Enter creatingRow = ");
        creatingRow = getNumber();
        System.out.print("Enter creatingCol = ");
        creatingCol = getNumber();
        String[][] board = new String[creatingRow][creatingCol];
        if (creatingRow > 10 || creatingCol > 10) {
            System.out.println("Invalid input!");
        } else {
            estimateMines();
            System.out.println();
            printBoard(board, creatingRow, creatingCol);
        }
        System.out.println();
        return new String[creatingRow][creatingCol];
    }

    public static boolean isGameOver() {
        return false;
    }

    public static int getNumber() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static void printTheRows(String[][] board, int row, int col) {
        for (row = 0; row < board.length; row++) {
            System.out.println("   ");
            System.out.print(" " + row + "|");
            for (col = 0; col < board[0].length; col++) {
                System.out.print(" ");
                System.out.print(".");
            }
        }
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
}
