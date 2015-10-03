package com.carsonmclean.camclean_reflex.Utilities;

import android.os.SystemClock;

/**
 * Created by carsonmclean on 2/10/15.
 */
public class ReactionTimerGame {
    long startTime, reactionTime;

    // Constructor
    public ReactionTimerGame() {

    }

    public void onClick() {
        System.out.println("On click worked!");
        reactionTime = (SystemClock.elapsedRealtime() - startTime);
    }

    public void startGame() {
        System.out.println("Game has started");
        startTime = SystemClock.elapsedRealtime();
    }
}
