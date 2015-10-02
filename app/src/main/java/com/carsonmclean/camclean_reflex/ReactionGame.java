package com.carsonmclean.camclean_reflex;

import android.os.Handler;
import android.os.SystemClock;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by carsonmclean on 2/10/15.
 */
public class ReactionGame {
    public int numberOfPlayers;
    long startTime = 0;
    long endTime = 0;
    long reactionTime;


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            startTime = SystemClock.elapsedRealtime();
            // TODO: Change button color
        }
    };

    public void startTimer() {
//      http://www.mopri.de/2010/timertask-bad-do-it-the-android-way-use-a-handler/
        Handler handler = new Handler();
        handler.postDelayed(runnable, 1000); // TODO: Change to random time
    }

    public void buttonClick(int numberOfPlayers, int playerNumber) {
        endTime = SystemClock.elapsedRealtime();
        if (startTime == 0) {
            // Too fast
        } else {
            reactionTime = (endTime - startTime);
            System.out.println(reactionTime);
        }
    }
}
