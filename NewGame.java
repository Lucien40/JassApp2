package com.huberlulu.jassapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;

public class NewGame extends AppCompatActivity {
    EditText Team1 ;
    EditText Team2 ;
    EditText Player1 ;
    EditText Player2;
    EditText Player3;
    EditText Player4;
    ArrayList<String> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("New Game");




    }

    @Override
    public void onResume() {
        super.onResume();

        //Animations and Layout
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        setContentView(R.layout.activity_new_game);

        getSupportActionBar().setTitle("New Game");


    }

    public void GO(View view){

        Team1 = (EditText) findViewById(R.id.NewTeam1Name);
        Team2 = (EditText) findViewById(R.id.NewTeam2Name);
        Player1 = (EditText) findViewById(R.id.NewPlayer1Name);
        Player2 = (EditText) findViewById(R.id.NewPlayer2Name);
        Player3 = (EditText) findViewById(R.id.NewPlayer3Name);
        Player4 = (EditText) findViewById(R.id.NewPlayer4Name);

        //Creating new game based on user inputs
        ArrayList<String> playerNames1 = new ArrayList<>();
        playerNames1.add(EditTextText(Player1));
        playerNames1.add(EditTextText(Player2));
        ArrayList<String> playerNames2 = new ArrayList<>();
        playerNames2.add(EditTextText(Player3));
        playerNames2.add(EditTextText(Player4));
        String team1Name = EditTextText(Team1);
        Log.i(team1Name+"hi",team1Name+"hi");
        String team2Name = EditTextText(Team2);
        Game newGame = new Game(playerNames1,team1Name,playerNames2,team2Name,Calendar.getInstance().getTime());

        //Saving newly created game instance
        SharedPreferences settings = getSharedPreferences(newGame.getName(), 0);
        SharedPreferences.Editor editor = settings.edit();
        Gson gson = new Gson();

        editor.putString(newGame.getName(),gson.toJson(newGame));
        editor.apply();

        //Calling child activity
        Intent intent = new Intent(this, GameModes.class);
        intent.putExtra("name",newGame.getName());
        startActivity(intent);
    }

    private String EditTextText(EditText view){

        if (view.getText().toString().matches("")){
            Log.i("EditTextText",view.getHint().toString()+" hint");
            return view.getHint().toString();
        }
        else{
            Log.i("EditTextText",view.getText().toString()+" text");
            return view.getText().toString();
        }


    }
}
