package com.backbase.bean;

import com.backbase.controller.KalahController;
import com.backbase.service.iface.IKalah;

/**
 * Bean capturing the player information
 */
public class Player {

    /**
     * Player's name
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the player's selected pit
     *
     * @param state
     * @param view
     * @return
     */
    public int getMove(KalahController state, IKalah view) {
        return view.getUserMove(state);
    }
}
