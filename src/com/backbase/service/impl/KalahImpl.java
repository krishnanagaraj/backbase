package com.backbase.service.impl;

import com.backbase.bean.Player;
import com.backbase.controller.KalahController;
import com.backbase.service.iface.IKalah;
import com.backbase.util.KalahConstants;

import java.util.InputMismatchException;
import java.util.Scanner;

public class KalahImpl implements IKalah {

    private Scanner input;

    public KalahImpl() {
        input = new Scanner(System.in);
    }

    /**
     * Displays the current board
     */
    @Override
    public void gameBoard(KalahController kalahController) {
        int[] board = kalahController.getBoard();
        Player[] players = kalahController.getPlayers();

        System.out.println();
        System.out.println("            " + players[0].getName());
        System.out.println();
        System.out.print("     ");
        for (int i = 1; i <= KalahController.PLAYER_SIDE_PITS; i++)
            System.out.print(moveSpacesRight(board[i], 4));
        System.out.println();

        System.out.println(moveSpacesRight(board[0], 5) +
                "                          " + moveSpacesRight(board[KalahController.HALF_PITS], 5));

        System.out.print("     ");
        for (int i = KalahController.TOTAL_NUM_OF_PITS - 1; i > KalahController.HALF_PITS; i--)
            System.out.print(moveSpacesRight(board[i], 4));
        System.out.println();

        System.out.println();
        System.out.println("            " + players[1].getName());
    }

    /**
     * Retrieves the user move
     */
    @Override
    public int getUserMove(KalahController kalahController) {
        int pit;
        int[] board = kalahController.getBoard();

        System.out.println();

        pit = getIntAnswer(String.format(KalahConstants.WHICH_PIT_TO_REMOVE, kalahController.getPlayerToMove().getName()));

        while ((pit < 1) || (pit > KalahController.PLAYER_SIDE_PITS) || (board[pit + kalahController.getPlayerNum() * KalahController.HALF_PITS] == 0)) {
            System.out.println(KalahConstants.INVALID_MOVE);
            pit = getIntAnswer(String.format(KalahConstants.WHICH_PIT_TO_REMOVE, kalahController.getPlayerToMove().getName()));
        }

        // Adjust to player's side
        return pit + kalahController.getPlayerNum() * KalahController.HALF_PITS;

    }

    /**
     * Ask player a question
     */
    @Override
    public int getIntAnswer(String question) {
        int answer = 0;
        boolean valid = false;

        // Ask for a number
        System.out.print(question + " ");
        while (!valid) {
            try {
                answer = input.nextInt();
                valid = true;
            } catch (InputMismatchException ex) {
                reportToUser("Invalid input");
                valid = false;
                input.nextLine();
                System.out.print(question + " ");
            }
        }
        input.nextLine();
        return answer;
    }

    /**
     * Convey a message to the player
     */
    @Override
    public void reportToUser(String message) {
        // Reports something to the user
        System.out.println(message);
    }

    /**
     * Ask question and retrieve an answer
     */
    @Override
    public String getAnswer(String message) {
        System.out.println(message);
        return input.nextLine();
    }

    public static String moveSpacesRight(int number, int width) {
        // Hold the converted number
        String outNum;

        // Convert to a string
        outNum = number + "";

        while (outNum.length() < width)
            // Pad with blanks at rear
            outNum += " ";
        return outNum;
    }
}
