package com.huberlulu.jassapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;

import static android.R.attr.data;
import static android.R.attr.name;

public class GameModes extends AppCompatActivity {

    private android.view.Menu menu;
    public Game game;
    String name;
    private int mRequestCode = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Animations and Layout
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        setContentView(R.layout.activity_game_modes);

        ActionBar ab = getSupportActionBar();

        // Enable the Up button

        ab.setTitle("Game Modes");

        //Extracting game name from parent class

        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");


        //Getting game instance from corresponding saved preference
        SharedPreferences settings = getSharedPreferences(name, 0);
        Gson gson = new Gson();
        game = gson.fromJson(settings.getString(name,null), Game.class);
        Log.i("GameModes Constructor",game.getPlayers().get(0).getName());


        //Saving newly created game instance
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(game.getName(),gson.toJson(game));
        editor.apply();


    }

    @Override
    public void onResume() {
        super.onResume();

        //Animations and Layout
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        setContentView(R.layout.activity_game_modes);

        getSupportActionBar().setTitle("Game Modes");


    }

    public void BasicMode(View view){
        Intent intent = new Intent(this, BasicMode.class);
        startActivity(intent);
    }

    public void CountingMode(View view){
        Intent intent = new Intent(this, CountingMode.class);
        intent.putExtra("name",name);
        startActivityForResult(intent, mRequestCode);
    }
}
