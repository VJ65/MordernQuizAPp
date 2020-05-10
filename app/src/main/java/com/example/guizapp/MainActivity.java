package com.example.guizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
private Button startbtn,regBtn;
private EditText email,password;
private ProgressBar bar;
    private FirebaseAuth objectFirebaseAuth;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar=findViewById (R.id.ProgressBar1);
        email=findViewById (R.id.studentEmailET);
        objectFirebaseAuth = FirebaseAuth.getInstance();
        password=findViewById (R.id.studentPasswordET);
        regBtn=findViewById (R.id.registerButton);
        startbtn= findViewById(R.id.button);
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              signInUser ();
            }
        });


        regBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                bar.setVisibility (View.VISIBLE);
                objectFirebaseAuth.createUserWithEmailAndPassword(email.getText ().toString (), password.getText ().toString ())
                        .addOnSuccessListener (new OnSuccessListener<AuthResult> () {
                            @Override
                            public void onSuccess (AuthResult authResult) {
                                bar.setVisibility (View.INVISIBLE);
                                Toast.makeText (MainActivity.this, "You are Registered", Toast.LENGTH_SHORT).show ();
                            }
                        }).addOnFailureListener (new OnFailureListener () {
                    @Override
                    public void onFailure (@NonNull Exception e) {
                        bar.setVisibility (View.INVISIBLE);
                        Toast.makeText (MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show ();
                    }
                });
            }
        });
    }
    public void signInUser() {
        try {
            bar.setVisibility(View.VISIBLE);
            if (!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                if (objectFirebaseAuth.getCurrentUser() != null) {
                    objectFirebaseAuth.signOut();
                    bar.setVisibility(View.INVISIBLE);
                    Toast.makeText(this, "User Logged Out Successfully", Toast.LENGTH_SHORT).show();
                    Intent ci=new Intent(MainActivity.this,categoryActivity.class);
                    startActivity(ci);
                } else {
                    objectFirebaseAuth.signInWithEmailAndPassword(email.getText().toString(),
                            password.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult> () {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    bar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(MainActivity.this, "User Logged In", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener () {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //signin.setEnabled(true);
                            email.requestFocus();

                            bar.setVisibility(View.INVISIBLE);
                            Toast.makeText(MainActivity.this, "Fails To Sig-in User: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } else if (email.getText().toString().isEmpty()) {
                email.requestFocus();
                bar.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "Please Enter The Email", Toast.LENGTH_SHORT).show();
            } else if (password.getText().toString().isEmpty()) {
                password.requestFocus();
                bar.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "Please Enter The Password", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {

            email.requestFocus();
            bar.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Logging In Error" + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
