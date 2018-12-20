package com.berkturan.androidbootcampweek3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

public class GamePlace extends AppCompatActivity {
    Button LogOffButton,ScoreButton,SendButton,AdsButton;
    EditText ResultEditText;
    private DatabaseReference mDatabase;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_place);
        LogOffButton= findViewById(R.id.LogOff); SendButton = findViewById(R.id.SendButton);
        ScoreButton = findViewById(R.id.ScoresButton);
        ResultEditText = findViewById(R.id.ResultEditText);
        AdsButton = findViewById(R.id.AdsButton);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        MobileAds.initialize(this, "ca-app-pub-4708612837484976~7927547599");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });
        LogOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent2 = new Intent(GamePlace.this,MainActivity.class);
                startActivity(intent2);
            }
        });


        ScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GamePlace.this,ScoreActivity.class);
                startActivity(intent);
            }
        });
        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("message").setValue(ResultEditText.getText().toString());
            }
        });

        AdsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });
    }
}
