package com.carsonmclean.camclean_reflex;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SinglePlayerActivity extends AppCompatActivity {
    ReactionGame reactionGame = new ReactionGame();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        DialogFragment dialogFragment = new InformationDialogFragment();
        dialogFragment.show(getFragmentManager(), "info");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class InformationDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.single_player_dialog_title);
            builder.setMessage(R.string.single_player_dialog_body);
//            builder.setNegativeButton(R.string.single_player_dialog_go_back, new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int id) {
//                    // TODO: Go back to main menu
//
//                    // User wants to go back
//                }
//            });
            builder.setPositiveButton(R.string.single_player_dialog_okay, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User wants to go play
                    // http://stackoverflow.com/questions/4934333/android-how-to-count-time-over-a-long-period
                    reactionGame.startTimer();
                }
            });

            return builder.create();
        }
    }
}
