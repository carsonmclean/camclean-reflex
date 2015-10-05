/*
    Copyright 2015 Carson McLean

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
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

    // Moritz; http://www.mopri.de/2010/timertask-bad-do-it-the-android-way-use-a-handler/; 2015-10-04
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            buttonColor(button,0x8000ff00); // GREEN
            buttonMessage(button,"Click!");
            startTime = SystemClock.elapsedRealtime();
            validClick = true;
        }
    };


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
                // Varun; http://stackoverflow.com/questions/4378533/cancelling-a-handler-postdelayed-process; 2015-10-04
                handler.removeCallbacks(runnable);
                buttonMessage(button,"Tap to restart");
                buttonColor(button,0x6f69441a); // Main Menu Brown
                messagePasser.createToast("Too early!"); //  TODO: Cancel the timer and color change. ie, go to break state
            } else { // Good reaction time
                buttonMessage(button,"Tap to restart");
                buttonColor(button,0x6f69441a); // Main Menu Brown
                messagePasser.createToast("Your reaction time was " + reactionTime + " milliseconds.");
            }
        } else { // Game not running, start the game. Gives break between rounds.
            validClick = false;
            startGame();
        }
    }

    public void startGame() {
        buttonColor(button, 0x80ff0000); // RED
        buttonMessage(button,"Wait...");
        gameRunning = true;
        handler.postDelayed(runnable,getRandomNumber());
    }

    // Greg Case; http://stackoverflow.com/questions/363681/generating-random-integers-in-a-range-with-java; 2015-10-03
    public long getRandomNumber() {
        Random random = new Random();
        return (random.nextInt(2000 - 10) + 10); // TODO: Check this gives proper range
    }

    public void buttonColor(Button button,int color) {
        button.setBackgroundColor(color);
    }

    public void buttonMessage(Button button,String message) {
        button.setText(message);
        button.setTextColor(0xffffffff);
        button.setTextSize(50);
    }
}

