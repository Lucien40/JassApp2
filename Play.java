package com.huberlulu.jassapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Play extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Play");
    }

    public void NewGame(View view){
        Intent intent = new Intent(this, NewGame.class);
        startActivity(intent);
    }

    public void LoadGame(View view){
        Intent intent = new Intent(this, LoadGame.class);
        startActivity(intent);
    }
}
