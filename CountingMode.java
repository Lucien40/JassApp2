package com.huberlulu.jassapp;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.NfcF;
import android.os.Parcelable;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static android.R.attr.data;
import static android.R.attr.name;
import static com.huberlulu.jassapp.R.array.power;
import static com.huberlulu.jassapp.R.string.Player1;
import static com.huberlulu.jassapp.R.string.Player2;
import static com.huberlulu.jassapp.R.string.Player3;
import static com.huberlulu.jassapp.R.string.Player4;


public class CountingMode extends AppCompatActivity {

    Game        game;
    String      name;
    TextView player1;
    TextView player2;
    TextView player3;
    TextView player4;
    TextView   Team1;
    TextView   Team2;
    private TextView mTextView;
    private NfcAdapter mNfcAdapter;
    private PendingIntent mPendingIntent;
    private IntentFilter[] mIntentFilters;
    private String[][] mNFCTechLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting_mode);

        player1 = (TextView) findViewById(R.id.CountingTeam1Player1);
        player2 = (TextView) findViewById(R.id.CountingTeam1Player2);
        player3 = (TextView) findViewById(R.id.CountingTeam2Player1);
        player4 = (TextView) findViewById(R.id.CountingTeam2Player2);
        Team1   = (TextView) findViewById(R.id.CountingTeam1Name);
        Team2   = (TextView) findViewById(R.id.CountingTeam2Name);


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

        player1.setText(game.getPlayers().get(0).getName());
        player2.setText(game.getPlayers().get(1).getName());
        player3.setText(game.getPlayers().get(2).getName());
        player4.setText(game.getPlayers().get(3).getName());
        Team1.setText(game.getTeams().get(0).Name);
        Team2.setText(game.getTeams().get(1).Name);

        mTextView = (TextView) findViewById(R.id.startText);

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (mNfcAdapter != null) {
            mTextView.setText("Please Scan the first card");
        } else {
            mTextView.setText("This phone is not NFC enabled.");
        }

        // create an intent with tag data and deliver to this activity
        mPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent. FLAG_ACTIVITY_SINGLE_TOP), 0);

        // set an intent filter for all MIME data
        IntentFilter ndefIntent = new IntentFilter(NfcAdapter. ACTION_NDEF_DISCOVERED);
        try {
            ndefIntent.addDataType("*/*");
            mIntentFilters = new IntentFilter[]{ndefIntent};
        } catch (Exception e) {
            Log.e("TagDispatch", e.toString());
        }

        mNFCTechLists = new String[][]{new String[]{NfcF.class.getName()}};


    }
    @Override
    public void onNewIntent(Intent intent) {


        // Get instance of Vibrator from current Context
        Vibrator v = (Vibrator) getSystemService(Context. VIBRATOR_SERVICE);

        // Vibrate for 400 milliseconds
        v.vibrate(400);
        String action = intent.getAction();
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        String s = action + "\n\n" + tag.toString();

        // parse through all NDEF messages and their records and pick text type only
        Parcelable data[] = intent.getParcelableArrayExtra( NfcAdapter.EXTRA_NDEF_MESSAGES);

        if (data != null) {
            try {
                for (int i = 0; i < data.length; i++) {
                    NdefRecord[] recs = ((NdefMessage) data[i]).getRecords();
                    for (int j = 0; j < recs.length; j++) {
                        if (recs[j].getTnf() == NdefRecord.TNF_WELL_KNOWN &&
                                Arrays.equals(recs[j].getType(), NdefRecord.RTD_TEXT)) {


                            byte[] payload = recs[j].getPayload();
                            String textEncoding = ((payload[0] & 0200) == 0) ? "UTF-8" : "UTF-16";
                            int langCodeLen = payload[0] & 0077;

                            s = new String(payload, langCodeLen + 1, payload.length - langCodeLen - 1, textEncoding) ;
                        }
                    }
                }
            } catch (Exception e) {
                Log.e("TagDispatch", e.toString());
            }

        }

        /*
        if (f == 4) {
            player1.setBackground(getResources().getDrawable(R.drawable.rect));
            player2.setBackground(getResources().getDrawable(R.drawable.rect));
            player3.setBackground(getResources().getDrawable( R.drawable.rect));
            player4.setBackground(getResources().getDrawable( R.drawable.rect));
            handPoints = 0;
            int highCard = compareCard(currentHand,cardColor,cardValue,power);
            for (int i=0; i<4; i++){
                handPoints  +=  cardPointMap.get(currentHand.get(i));
            }


            if ((highCard + firstCard) % 4 == 0){
                Total1 = Total1 + handPoints;
                pointsTeam1.setText(""+Total1);
                Player1.setBackground(getResources().getDrawable( R.drawable.selectorrect));
            }
            else if ((highCard + firstCard) % 4 == 1){
                Total2 = Total2 + handPoints;
                pointsTeam2.setText(""+Total2);
                Player2.setBackground(getResources().getDrawable( R.drawable.selectorrect));
            }
            else if ((highCard + firstCard) % 4 == 2){
                Total1 = Total1 + handPoints;
                pointsTeam1.setText(""+Total1);
                Player3.setBackground(getResources().getDrawable( R.drawable.selectorrect));
            }
            else if ((highCard + firstCard) % 4 == 3){
                Total2 = Total2 + handPoints;
                pointsTeam2.setText(""+Total2);
                Player4.setBackground(getResources().getDrawable( R.drawable.selectorrect));
            }

            currentHand.clear();
            f = 0;

            firstCard = (highCard+firstCard)%4 ;
        }
        f++;*/
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mNfcAdapter != null)
            mNfcAdapter.enableForegroundDispatch(this, mPendingIntent, mIntentFilters, mNFCTechLists);
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mNfcAdapter != null)
            mNfcAdapter.disableForegroundDispatch(this);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // do something useful
                Intent resultIntent = new Intent(this,GameModes.class);
                resultIntent.putExtra("name",game.getName());
                startActivity(resultIntent);

                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }
}
