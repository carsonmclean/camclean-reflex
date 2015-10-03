package com.carsonmclean.camclean_reflex;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;


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

    public void startGame() {
//      http://www.mopri.de/2010/timertask-bad-do-it-the-android-way-use-a-handler/
        Handler handler = new Handler();
        handler.postDelayed(runnable, 10000); // TODO: Change to random time
    }

    public void buttonClick() {
        endTime = SystemClock.elapsedRealtime();
        if (startTime == 0) {
            // Too fast
            System.out.println("startTime = 0");
        } else {
            reactionTime = (endTime - startTime);
            System.out.println(reactionTime);

        }
    }
//http://developer.android.com/guide/topics/ui/dialogs.html
    public class ReactionTimeDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Results");
            builder.setMessage("Your reaction time was " + reactionTime);
            builder.setPositiveButton("Play again", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
//http://stackoverflow.com/questions/1397361/how-do-i-restart-an-android-activity
                    Intent intent = getIntent();
                    //finish();
                    startActivity(intent);
                }
            }
    }

}
