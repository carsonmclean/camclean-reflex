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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.carsonmclean.camclean_reflex.R;
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

public class StatisticsActivity extends AppCompatActivity {
//  CMPUT 301 Fall 2015 lonelyTwitter Lab Code
    String FILENAME = "reactionTimerGame.sav";
    private ArrayList<Integer> reactions = new ArrayList<>();
    private ArrayAdapter<Integer> adapter;
    private ListView oldReactionsList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        loadFromFile();

        //  CMPUT 301 Fall 2015 lonelyTwitter Lab Code
        oldReactionsList = (ListView) findViewById(R.id.oldReactionsList);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistics, menu);
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


//  CMPUT 301 Fall 2015 lonelyTwitter Lab Code
    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<>(this,
                R.layout.list_item, reactions);
        oldReactionsList.setAdapter(adapter);
        adapter.notifyDataSetChanged(); // data has changed, views that use it should refresh
    }

//  CMPUT 301 Fall 2015 lonelyTwitter Lab Code
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
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
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
