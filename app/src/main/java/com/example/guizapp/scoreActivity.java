package com.example.guizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class scoreActivity extends AppCompatActivity {
private TextView scored,totel;
private Button donebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        scored = findViewById(R.id.scored);
        totel = findViewById(R.id.totel);
        donebtn = findViewById(R.id.done);
        scored.setText(String.valueOf(getIntent().getIntExtra("score",0)));
        totel.setText("OUT OF"+String.valueOf(getIntent().getIntExtra("total",0)));
        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
