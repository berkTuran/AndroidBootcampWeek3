package com.berkturan.androidbootcampweek3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

public class GamePlace extends AppCompatActivity {
    Button LogOffButton,ScoreButton,SendButton;
    EditText ResultEditText;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_place);
        LogOffButton= findViewById(R.id.LogOff); SendButton = findViewById(R.id.SendButton);
        ScoreButton = findViewById(R.id.ScoresButton);
        ResultEditText = findViewById(R.id.ResultEditText);
        mDatabase = FirebaseDatabase.getInstance().getReference();

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

    }
}
