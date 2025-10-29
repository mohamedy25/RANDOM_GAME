package com.example.random_num;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tv1, tv2, tv3, tv4, tv5, tv6, tvCurrent, tvAverage;
    private Button btnStartStop, btnNewGame;
    private int n1, n2, n3, n4, n5, n6;
    private boolean isRunning = false;
    private Handler handler = new Handler();
    private Random random = new Random();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int num = random.nextInt(39) + 1;
            tvCurrent.setText(String.valueOf(num));
            handler.postDelayed(this, 100);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.textView1);
        tv2 = findViewById(R.id.textView2);
        tv3 = findViewById(R.id.textView3);
        tv4 = findViewById(R.id.textView4);
        tv5 = findViewById(R.id.textView5);
        tv6 = findViewById(R.id.textView6);
        tvCurrent = findViewById(R.id.textView7);
        tvAverage = findViewById(R.id.textView8);
        btnStartStop = findViewById(R.id.button);
        btnNewGame = findViewById(R.id.button3);

        newGame();

        btnStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    isRunning = true;
                    handler.post(runnable);
                    btnStartStop.setText("stop");
                } else {
                    isRunning = false;
                    handler.removeCallbacks(runnable);
                    btnStartStop.setText("start");
                    checkMatch();
                }
            }
        });

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });
    }

    private void newGame() {
        n1 = random.nextInt(39) + 1;
        n2 = random.nextInt(39) + 1;
        n3 = random.nextInt(39) + 1;
        n4 = random.nextInt(39) + 1;
        n5 = random.nextInt(39) + 1;
        n6 = random.nextInt(39) + 1;

        tv1.setText(String.valueOf(n1));
        tv2.setText(String.valueOf(n2));
        tv3.setText(String.valueOf(n3));
        tv4.setText(String.valueOf(n4));
        tv5.setText(String.valueOf(n5));
        tv6.setText(String.valueOf(n6));

        tv1.setTextColor(0xFF000000);
        tv2.setTextColor(0xFF000000);
        tv3.setTextColor(0xFF000000);
        tv4.setTextColor(0xFF000000);
        tv5.setTextColor(0xFF000000);
        tv6.setTextColor(0xFF000000);

        tvCurrent.setText("0");
    }

    private void checkMatch() {
        int current = Integer.parseInt(tvCurrent.getText().toString());

        if (current == n1) tv1.setTextColor(0xFF4CAF50);
        if (current == n2) tv2.setTextColor(0xFF4CAF50);
        if (current == n3) tv3.setTextColor(0xFF4CAF50);
        if (current == n4) tv4.setTextColor(0xFF4CAF50);
        if (current == n5) tv5.setTextColor(0xFF4CAF50);
        if (current == n6) tv6.setTextColor(0xFF4CAF50);
    }
}
