package com.carsonmclean.camclean_reflex.Utilities;

import android.os.Handler;
import android.os.SystemClock;
import android.widget.Button;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by carsonmclean on 2/10/15.
 */
public class ReactionTimerGame {
    MessagePasser messagePasser;
    Button button;
    long startTime, reactionTime;
    boolean gameRunning, validClick;


    // Constructor
    public ReactionTimerGame(MessagePasser messagePasser, Button button) {
        this.messagePasser = messagePasser;
        this.button = button;
    }

    public void onClick() {
        reactionTime = (SystemClock.elapsedRealtime() - startTime);
        if (gameRunning) {
            gameRunning = false;
            if (!validClick) { // Clicked before color change
                messagePasser.createToast("Too early!"); //  TODO: Cancel the timer and color change. ie, go to break state
            } else { // Good reaction time
                messagePasser.createToast("Your reaction time was " + reactionTime + " milliseconds.");
            }
        } else { // Game not running, start the game. Gives break between rounds.
            validClick = false;
            startGame();
        }
    }

    public void startGame() {
        buttonColor(button, 0xffff0000); // RED
        gameRunning = true;

        // http://www.mopri.de/2010/timertask-bad-do-it-the-android-way-use-a-handler/
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                buttonColor(button,0xff00ff00); // GREEN
                startTime = SystemClock.elapsedRealtime();
                validClick = true;
            }
        };
        handler.postDelayed(runnable,getRandomNumber());
    }

    // http://stackoverflow.com/questions/363681/generating-random-integers-in-a-range-with-java
    public long getRandomNumber() {
        Random random = new Random();
        return (random.nextInt(2000 - 10) + 10); // TODO: Check this gives proper range
    }

    public void buttonColor(Button button,int color) {
        button.setBackgroundColor(color);
    }
}

