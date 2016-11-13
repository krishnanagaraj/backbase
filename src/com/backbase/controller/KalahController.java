package com.backbase.controller;

import com.backbase.bean.Player;

/**
 * Constructs the game
 */
public class KalahController {

    private int[] board;
    private int playerToMoveNumber;
    private Player[] players;

    // Total pits
    public final static int TOTAL_NUM_OF_PITS = 14;

    // Pits on one side of the board
    public final static int PLAYER_SIDE_PITS = 6;

    // Half of the total number of pits
    public final static int HALF_PITS = 7;

    /**
     * Constructs the game
     */
    public KalahController(int playerNum, Player[] thePlayers) {
        initialize(playerNum, thePlayers);
    }

    /**
     * Initialization of the game board
     */
    public void initialize(int playerNum, Player[] thePlayers) {

        int[] kalahBoard = {0, 6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6};

        board = new int[TOTAL_NUM_OF_PITS];

        for (int i = 0; i < TOTAL_NUM_OF_PITS; i++) {
            board[i] = kalahBoard[i];
        }

        playerToMoveNumber = playerNum;
        players = thePlayers;
    }

    /**
     * Make a move, removing stones from the given pit.
     */
    public void playerMoves(int pit) {
        int playerKalah, opponentKalah;
        int stones;

        // Decide which kalah belongs to the current player
        playerKalah = playerToMoveNumber * HALF_PITS;
        opponentKalah = HALF_PITS - playerKalah;

        // Make the move
        stones = board[pit];
        board[pit] = 0;

        while (stones > 0) {
            pit--;
            if (pit == -1)
                pit = TOTAL_NUM_OF_PITS - 1;
            // Nothing dropped in opponent's kalah
            if (pit != opponentKalah) {
                // Remove a stone from the pit
                board[pit]++;
                stones--;
            }
        }

        // Handle move-again and captures
        // If end in own kalah, move again
        if (pit != playerKalah) {
            if ((board[pit] == 1) && (board[TOTAL_NUM_OF_PITS - pit] > 0) &&
                    ((playerToMoveNumber == 0 && (pit < HALF_PITS)) ||
                            (playerToMoveNumber == 1 && (pit > HALF_PITS)))) {

                board[playerKalah] = board[playerKalah] + board[TOTAL_NUM_OF_PITS - pit] + 1;
                board[pit] = board[TOTAL_NUM_OF_PITS - pit] = 0;
            }
            playerToMoveNumber = 1 - playerToMoveNumber;
        }
    }


    /**
     * Current player score
     */
    public int retrieveScore() {
        int total = 0;                          // Sums the score

        for (int i = 0; i <= PLAYER_SIDE_PITS; i++)
            total += board[i] - board[i + HALF_PITS];

        if (playerToMoveNumber == 0)
            return total;
        else
            return -total;
    }


    /**
     * Check if game is over
     *
     * @return true if the game is over
     */
    public boolean isGameOver() {
        int pit;
        int upper;
        pit = playerToMoveNumber * HALF_PITS;
        upper = pit + PLAYER_SIDE_PITS;

        // Look for a non-empty pit
        do {
            pit++;
            if (board[pit] > 0)
                // If non-empty pit then game not over
                return false;
        } while (pit < upper);

        // If all pits empty, then game finished
        return true;
    }

    public int[] getBoard() {
        return board;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getPlayerNum() {
        return playerToMoveNumber;
    }

    public Player getPlayerToMove() {
        return players[playerToMoveNumber];
    }

}
