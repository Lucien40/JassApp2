package com.huberlulu.jassapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class StartMenu extends AppCompatActivity {

    private android.view.Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TRANSITION ANIMATION:

        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

        setContentView(R.layout.activity_start_menu);

        //CREATING TOOLBAR

        getSupportActionBar().setTitle("Menu");




    }


    @Override
    public void onResume() {
        super.onResume();

        //TRANSITION
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        setContentView(R.layout.activity_start_menu);

        getSupportActionBar().setTitle("Menu");


    }

    //ONCLICK METHODS::

    public void Menu(View view) {
        Intent intent = new Intent(this, Play.class);
        startActivity(intent);
    }

    public void Settings(View view){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void Rules(View view){
        Intent intent = new Intent(this,Rules.class);
        startActivity(intent);
    }

    public void Credits(View view){
        Intent intent = new Intent(this,Credits.class);
        startActivity(intent);
    }
}
