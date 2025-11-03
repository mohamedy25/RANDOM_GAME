package com.example.random_num;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tv1, tv2, tv3, tv4, tv5, tv6, tvCurrent, count;
    private Button start, btnNewGame;
    private Handler handler = new Handler();
    private Random random = new Random();
    private boolean istart = false;

    private int n1, n2, n3, n4, n5, n6;
    private int matchCount = 0;
    private int clickCount = 0;

    private Runnable updateNumber = new Runnable() {
        @Override
        public void run() {
            if (istart) {
                int n = random.nextInt(39) + 1;
                tvCurrent.setText(String.valueOf(n));
                handler.postDelayed(this, 1000);
            }
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
        count = findViewById(R.id.textView8);
        start = findViewById(R.id.button);
        btnNewGame = findViewById(R.id.button3);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(clickCount<6)
                if (!istart && matchCount < 6) {
                    istart = true;
                    start.setText("Stop");
                    handler.post(updateNumber);
                } else if (istart) {
                    istart = false;
                    start.setText("Start");
                    handler.removeCallbacks(updateNumber);
                    int finalNum = Integer.parseInt(tvCurrent.getText().toString());
                    checkMatch(finalNum);

                    clickCount++;
                    updateCounter();
                }else if (clickCount ==0)
                ;{

                }
            }
        });

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateNewNumbers();
                start.setText("Start");
                istart = false;
                matchCount = 0;
                clickCount = 0;
                updateCounter();
            }
        });

        generateNewNumbers();
        updateCounter();
    }

    private void generateNewNumbers() {
        resetColors();
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
    }

    private void checkMatch(int current) {
        if (matchCount >= 6) return;

        if (current == n1) highlight(tv1);
        if (current == n2) highlight(tv2);
        if (current == n3) highlight(tv3);
        if (current == n4) highlight(tv4);
        if (current == n5) highlight(tv5);
        if (current == n6) highlight(tv6);
    }

    private void highlight(TextView tv) {
        if (tv.getCurrentTextColor() != Color.GREEN && matchCount < 6) {
            tv.setTextColor(Color.GREEN);
            matchCount++;
            updateCounter();
        }
    }

    private void updateCounter() {
        count.setText(clickCount + " of 6");
    }

    private void resetColors() {
        tv1.setTextColor(Color.RED);
        tv2.setTextColor(Color.RED);
        tv3.setTextColor(Color.RED);
        tv4.setTextColor(Color.RED);
        tv5.setTextColor(Color.RED);
        tv6.setTextColor(Color.RED);
    }
}
