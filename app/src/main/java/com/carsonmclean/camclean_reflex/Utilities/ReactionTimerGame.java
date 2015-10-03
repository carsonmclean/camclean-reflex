package com.carsonmclean.camclean_reflex.Utilities;

import android.os.SystemClock;
import android.widget.Button;

/**
 * Created by carsonmclean on 2/10/15.
 */
public class ReactionTimerGame {
    MessagePasser messagePasser;
    Button button;
    long startTime, reactionTime;


    // Constructor
    public ReactionTimerGame(MessagePasser messagePasser, Button button) {
        this.messagePasser = messagePasser;
        this.button = button;

    }

    public void onClick() {
        reactionTime = (SystemClock.elapsedRealtime() - startTime);
        buttonColor(button,0xff000000);
        // Want to send message back to Activity, need object to pass message
        messagePasser.createToast("Your reaction time was " + reactionTime + " milliseconds.");
    }

    public void startGame() {
        buttonColor(button,0xffff0000);
        startTime = SystemClock.elapsedRealtime();
    }

    public void buttonColor(Button button,int color ) {
        button.setBackgroundColor(color);
    }
}
