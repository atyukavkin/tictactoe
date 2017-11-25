package com.tyukavkin.tictactoe;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.tyukavkin.tictactoe.Board.X;
import static com.tyukavkin.tictactoe.Board.ZERO;

/**
 * Created by Andrey Tyukavkin on 11/25/2017.
 */

public class GameTest {

    private static Logger LOGGER = LoggerFactory.getLogger(GameTest.class);

    private char[][] boardWinnerByFirstRow = new char[][]{
            {ZERO, ZERO, ZERO},
            {ZERO, X, X},
            {X, ZERO, X}
    };
    private char[][] boardWinnerByThirdRow = new char[][]{
            {ZERO, X, X},
            {X, X, ZERO},
            {ZERO, ZERO, ZERO}
    };
    private char[][] boardWinnerByColumn = new char[][]{
            {ZERO, X, ZERO},
            {ZERO, X, X},
            {ZERO, ZERO, X}
    };
    private char[][] boardWinnerByDiagonal = new char[][]{
            {X, X, ZERO},
            {ZERO, X, X},
            {ZERO, ZERO, X}
    };

    @Test
    public void isWinnerByRows() throws Exception {
        Board board = new Board();
        board.setBoard(boardWinnerByFirstRow);
        Assert.assertTrue(board.isWinner());
        board.printBoard();
        board.setBoard(boardWinnerByThirdRow);
        Assert.assertTrue(board.isWinner());
        board.printBoard();
    }

    @Test
    public void isWinnerByColumns() throws Exception {
        Board board = new Board();
        board.setBoard(boardWinnerByColumn);
        Assert.assertTrue(board.isWinner());
        board.printBoard();
    }

    @Test
    public void isWinnerByDiagonal() throws Exception {
        Board board = new Board();
        board.setBoard(boardWinnerByDiagonal);
        Assert.assertTrue(board.isWinner());
        board.printBoard();
    }
}
