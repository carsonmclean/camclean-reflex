package com.carsonmclean.camclean_reflex.Utilities;

import android.os.SystemClock;

/**
 * Created by carsonmclean on 2/10/15.
 */
public class ReactionTimerGame {
    DialogPasser dialogPasser;
    long startTime, reactionTime;


    // Constructor
    public ReactionTimerGame(DialogPasser dialogPasser) {
        this.dialogPasser = dialogPasser;


    }

    public void onClick() {
        reactionTime = (SystemClock.elapsedRealtime() - startTime);
        // Want to send message back to Activity, need object to pass message
        dialogPasser.createDialog("Results","Your reaction time was " + reactionTime + " milliseconds.");
    }

    public void startGame() {
        startTime = SystemClock.elapsedRealtime();
    }
}
