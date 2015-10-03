package com.carsonmclean.camclean_reflex.Utilities;

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


    // Constructor
    public ReactionTimerGame(MessagePasser messagePasser, Button button) {
        this.messagePasser = messagePasser;
        this.button = button;

    }

    public void onClick() {
        reactionTime = (SystemClock.elapsedRealtime() - startTime);
        buttonColor(button, 0xff000000);
        // Want to send message back to Activity, need object to pass message
        messagePasser.createToast("Your reaction time was " + reactionTime + " milliseconds.");
    }

    public void startGame() {
        buttonColor(button, 0xffff0000);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                buttonColor(button,0xff00ff00);
                startTime = SystemClock.elapsedRealtime();
            }
        };
        timer.schedule(timerTask, getRandomNumber());
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

