package com.backbase.controller;


import com.backbase.bean.Player;
import com.backbase.service.iface.IKalah;
import com.backbase.service.impl.KalahImpl;
import com.backbase.util.KalahConstants;

/**
 * Handles the Kalah game
 */
public class MainController {

    /**
     * Core method handling the Kalah game
     *
     * @param args
     */
    public static void main(String args[]) {
        int totalScore;

        // Initialize the game
        Player[] players = new Player[2];
        IKalah iKalah = new KalahImpl();

        players[0] = createPlayer(iKalah, KalahConstants.PLAYER1);
        players[1] = createPlayer(iKalah, KalahConstants.PLAYER2);

        // Obtain the current game state
        KalahController kalahController = new KalahController(0, players);
        iKalah.gameBoard(kalahController);

        while (!kalahController.isGameOver()) {
            int move = kalahController.getPlayerToMove().getMove(kalahController, iKalah);
            kalahController.playerMoves(move);
            iKalah.gameBoard(kalahController);
        }

        // Obtain the score
        totalScore = kalahController.retrieveScore();
        if (totalScore > 0)
            iKalah.reportToUser(players[kalahController.getPlayerNum()].getName() + " won by " + totalScore);
        else if (totalScore < 0)
            iKalah.reportToUser(players[1 - kalahController.getPlayerNum()].getName() + " won by " + -totalScore);
        else
            iKalah.reportToUser(KalahConstants.DRAW_GAME);
    }

    /**
     * Create a new player
     *
     * @param view
     * @param playerMsg
     * @return
     */
    public static Player createPlayer(IKalah view, String playerMsg) {
        String playerName = view.getAnswer(String.format(KalahConstants.PLAYER_ENTRY, playerMsg));
        Player player = new Player();
        player.setName(playerName);
        return player;
    }
}