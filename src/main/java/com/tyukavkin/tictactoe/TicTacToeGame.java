package com.tyukavkin.tictactoe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

import static com.tyukavkin.tictactoe.Board.BOARD_SIZE;

public class TicTacToeGame {

    private static Logger LOGGER = LoggerFactory.getLogger(TicTacToeGame.class);

    public static void main(String[] args) {

        Board board = new Board();
        Scanner scanner = new Scanner(System.in);

        String errorMessage = "Please input integer 0 - " + (BOARD_SIZE - 1);
        do {
            LOGGER.info("The board is");
            board.printBoard();
            int row = BOARD_SIZE + 1;
            int column = BOARD_SIZE + 1;
            do {
                LOGGER.info("The player is " + board.getCurrentPlayer());
                String input = scanner.next();

                try {
                    row = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    LOGGER.error(errorMessage);
                    continue;
                }
                input = scanner.next();
                try {
                    column = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    LOGGER.error(errorMessage);
                }
                if (row >= BOARD_SIZE || column >= BOARD_SIZE) {
                    LOGGER.error(errorMessage);
                }
            } while (row > BOARD_SIZE || column > BOARD_SIZE || !board.placeMark(row, column));

            board.swopPlayer();

        } while (!board.isWinner() && !board.isBoardFull());

        board.printBoard();
        if (board.isBoardFull() && !board.isWinner()) {
            LOGGER.info("There is no winner :(");
        } else {
            board.swopPlayer();
            LOGGER.info("The player " + Character.toUpperCase(board.getCurrentPlayer()) + " is winner!");
        }
    }
}
