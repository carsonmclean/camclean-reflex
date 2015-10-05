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
package com.carsonmclean.camclean_reflex.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.carsonmclean.camclean_reflex.R;
import com.carsonmclean.camclean_reflex.Utilities.GameShowBuzzer;
import com.carsonmclean.camclean_reflex.Utilities.MessagePasser;

public class TwoPlayerActivity extends AppCompatActivity {
    GameShowBuzzer gameShowBuzzer;
    MessagePasser messagePasser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player);

        messagePasser = new MessagePasser(this);
        gameShowBuzzer = new GameShowBuzzer(messagePasser);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_two_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    // Zsombor Erdődy-Nagy; http://stackoverflow.com/questions/5620772/get-text-from-pressed-button; 2015-10-04
    public void onClick2(View view) {
        Button button = (Button) view;
        gameShowBuzzer.onClick(button.getText().toString());
    }
}
