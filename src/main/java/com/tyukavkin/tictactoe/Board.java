package com.tyukavkin.tictactoe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Andrey Tyukavkin on 11/20/2017.
 */
public class Board {

    private static Logger LOGGER = LoggerFactory.getLogger(Board.class);

    public static final char ZERO = '0';
    public static final char X = 'x';
    public static final char EMPTY = ' ';
    public static int BOARD_SIZE = 3;
    private char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
    private char currentPlayer = ZERO;

    public Board() {
        initBoard();
    }

    public void initBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    public void printBoard() {
        printBoardLine();
        for (int i = 0; i < BOARD_SIZE; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("|");
            for (int j = 0; j < BOARD_SIZE; j++) {
                stringBuilder.append(printMark(board[i][j])).append("|");
            }
            LOGGER.info(stringBuilder.toString());
            printBoardLine();
        }
    }

    private String printMark(char mark) {
        if (mark == EMPTY) {
            return "" + mark + "";
        }
        return mark == ZERO ? "\u001b[31m" + mark + "\u001b[0m" : "\u001b[32m" + mark + "\u001b[0m";
    }

    private void printBoardLine() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE * 2; i++) {
            stringBuilder.append("-");
        }
        LOGGER.info(stringBuilder.toString());
    }

    public boolean isWinner() {
        return isWinnerByRows() || isWinnerByColumns() || isWinnerByDiagonals();
    }

    private boolean isWinnerByDiagonals() {
        boolean isWinnerByDiagonal = true;
        char value = board[0][0];
        for (int z = 1; z < BOARD_SIZE; z++) {
            if (board[z][z] != value || board[z][z] == EMPTY) {
                isWinnerByDiagonal = false;
                break;
            }
        }
        if (isWinnerByDiagonal) {
            value = board[BOARD_SIZE - 1][BOARD_SIZE - 1];
            for (int z = 1; z < BOARD_SIZE; z++) {
                if (board[BOARD_SIZE - z - 1][BOARD_SIZE - z - 1] != value || board[BOARD_SIZE - z - 1][BOARD_SIZE - z - 1] == EMPTY) {
                    isWinnerByDiagonal = false;
                    break;
                }
            }
        }
        if (isWinnerByDiagonal) {
            LOGGER.info("Winner by diagonal");
        }
        return isWinnerByDiagonal;
    }

    private boolean isWinnerByColumns() {
        boolean isWinnerByColumn = true;
        for (int j = 0; j < BOARD_SIZE; j++) {
            isWinnerByColumn = true;
            char value = board[0][j];
            for (int i = 1; i < BOARD_SIZE; i++) {
                if (board[i][j] != value || board[i][j] == EMPTY) {
                    isWinnerByColumn = false;
                    break;
                }
            }
            if (isWinnerByColumn) {
                break;
            }
        }
        if (isWinnerByColumn) {
            LOGGER.info("Winner by column");
        }
        return isWinnerByColumn;
    }

    private boolean isWinnerByRows() {
        boolean isWinnerByRow = true;
        for (int i = 0; i < BOARD_SIZE; i++) {
            isWinnerByRow = true;
            char value = board[i][0];
            for (int j = 1; j < BOARD_SIZE; j++) {
                if (board[i][j] != value || board[i][j] == EMPTY) {
                    isWinnerByRow = false;
                    break;
                }
            }
            if (isWinnerByRow) {
                break;
            }
        }
        if (isWinnerByRow) {
            LOGGER.info("Winner by row");
        }
        return isWinnerByRow;
    }

    public void swopPlayer() {
        currentPlayer = (ZERO == currentPlayer ? X : ZERO);
    }

    public boolean placeMark(int row, int column) {
        if (row >= BOARD_SIZE || column >= BOARD_SIZE
                || row < 0 || column < 0) {
            return false;
        }
        if (board[row][column] == EMPTY) {
            board[row][column] = currentPlayer;
            return true;
        }
        return false;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isBoardFull() {
        boolean isFull = true;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    isFull = false;
                }
            }
        }
        return isFull;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }
}
