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

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
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
    Context context;
    String FILENAME;
    private ArrayList<Integer> reactions;


    // http://www.mopri.de/2010/timertask-bad-do-it-the-android-way-use-a-handler/
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
    public ReactionTimerGame(MessagePasser messagePasser, Button button, Context context) {
        this.messagePasser = messagePasser;
        this.button = button;
        this.context = context;
        FILENAME = "reactionTimerGame.sav";
        reactions = new ArrayList<>();
        loadFromFile();
    }

    public void onClick() {
        reactionTime = (SystemClock.elapsedRealtime() - startTime);
        if (gameRunning) {
            gameRunning = false;
            if (!validClick) { // Clicked before color change
                // http://stackoverflow.com/questions/4378533/cancelling-a-handler-postdelayed-process
                handler.removeCallbacks(runnable);
                buttonMessage(button,"Tap to restart");
                buttonColor(button,0x6f69441a); // Main Menu Brown
                messagePasser.createToast("Too early!"); //  TODO: Cancel the timer and color change. ie, go to break state
            } else { // Good reaction time
                buttonMessage(button,"Tap to restart");
                buttonColor(button,0x6f69441a); // Main Menu Brown
                messagePasser.createToast("Your reaction time was " + reactionTime + " milliseconds.");
                recordReaction((int)reactionTime);
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

    // http://stackoverflow.com/questions/363681/generating-random-integers-in-a-range-with-java
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

    //  CMPUT 301 Fall 2015 lonelyTwitter Lab Code
    private void loadFromFile() {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
//          https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015/09/24
            Type listType = new TypeToken<ArrayList<Integer>>() {}.getType();
            reactions = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            reactions = new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //  CMPUT 301 Fall 2015 lonelyTwitter Lab Code
    public void saveInFile() {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void recordReaction(Integer reaction) {
        reactions.add(reaction);
        saveInFile();
    }

}

