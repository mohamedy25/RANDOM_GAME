package com.example.random_num;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.random_num.MainActivity;

public class ScoreActivity extends AppCompatActivity {
    private TextView tvNumGames, tvNumCorrect,tvName;
    private Button btnBack;
    private Intent intent, receivedIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        tvNumGames = findViewById(R.id.textView7);
        tvNumCorrect = findViewById(R.id.textView8);
        btnBack = findViewById(R.id.button4);
        tvName=findViewById(R.id.textView);

        intent = new Intent(ScoreActivity.this, MainActivity.class);
        receivedIntent = getIntent();

        String name = receivedIntent.getStringExtra("NAME");
        tvName.setText("hi"+name );

        tvNumGames.setText("Games: " + MainActivity.pubGame);
        tvNumCorrect.setText("Correct: " + MainActivity.pubCount);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
                finish();
            }
        });
    }
}
