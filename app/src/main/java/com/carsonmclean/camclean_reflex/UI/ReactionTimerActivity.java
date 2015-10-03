package com.carsonmclean.camclean_reflex.UI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.carsonmclean.camclean_reflex.R;
import com.carsonmclean.camclean_reflex.Utilities.DialogPasser;
import com.carsonmclean.camclean_reflex.Utilities.ReactionTimerGame;

public class ReactionTimerActivity extends AppCompatActivity {
    private ReactionTimerGame reactionTimerGame = new ReactionTimerGame(); // TODO: Use the constructor
    private DialogPasser dialogPasser = new DialogPasser(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_timer);

        dialogPasser.createDialog("Instructions","When the button changes color, click as fast as you can!");


        // http://stackoverflow.com/questions/16636752/android-button-onclicklistener
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Got here");
                reactionTimerGame.onClick();
            }
        });

        reactionTimerGame.startGame();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reaction_timer, menu);
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
}
