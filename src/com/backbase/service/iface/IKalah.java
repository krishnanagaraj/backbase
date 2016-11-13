package com.backbase.service.iface;

import com.backbase.controller.KalahController;


public interface IKalah {

    /**
     * Displays the current board
     */
    public void gameBoard(KalahController kalahController);

    /**
     * Retrieves the user move
     */
    public int getUserMove(KalahController kalahController);

    /**
     * Ask player a question
     */
    public int getIntAnswer(String question);

    /**
     * Convey a message to the player
     */
    public void reportToUser(String message);

    /**
     * Ask question and retrieve an answer
     */
    public String getAnswer(String question);


}

