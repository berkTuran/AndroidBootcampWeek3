package com.berkturan.androidbootcampweek3;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText EmailEditText, PasswordEditText;
    Button SignInButton, SignUpButton;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EmailEditText = findViewById(R.id.EmailEditText); PasswordEditText = findViewById(R.id.PasswordEditText);
        SignInButton = findViewById(R.id.SignInButton); SignUpButton = findViewById(R.id.SignUpButton);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            updateUI(user);
        } else {
            // No user is signed in
            SignUpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(EmailEditText.getText().toString()!="" && PasswordEditText.getText().toString()!=""){
                        String email = EmailEditText.getText().toString();
                        String password = PasswordEditText.getText().toString();
                        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                    updateUI(firebaseUser);
                                }
                            }
                        });


                    }
                }
            });
            SignInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(EmailEditText.getText().toString()!="" && PasswordEditText.getText().toString()!=""){
                        String email = EmailEditText.getText().toString();
                        String password = PasswordEditText.getText().toString();
                        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                updateUI(firebaseUser);
                            }
                        });
                    }
                }
            });
        }

    }

    public void updateUI(FirebaseUser firebaseUser){
        if(firebaseUser!=null){
            Intent intent  =new Intent(this,GamePlace.class);
            intent.putExtra("CurrentUser",firebaseUser);
            startActivity(intent);
        }
    }
}
