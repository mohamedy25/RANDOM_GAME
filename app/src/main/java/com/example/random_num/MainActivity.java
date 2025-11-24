package com.example.random_num;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static int pubCount = 0, pubGame = 0;

    private TextView tv1, tv2, tv3, tv4, tv5, tv6, tvCurrent, count,PlayerName,tvage;
    private Button start, btnNewGame, page, exit;

    private Handler handler = new Handler();
    private Random random = new Random();
    private boolean istart = false;

    private int n1, n2, n3, n4, n5, n6;
    private int matchCount = 0;
    private int c = 0;

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
        tvage = findViewById(R.id.tvage);

        tvCurrent = findViewById(R.id.textView7);
        count = findViewById(R.id.textView8);

        start = findViewById(R.id.button);
        btnNewGame = findViewById(R.id.button3);
        page = findViewById(R.id.button4);
        exit = findViewById(R.id.button5);

        PlayerName = findViewById(R.id.tvName);
        showVerificationDialog();
        generateNewNumbers();
        updateCounter();

        page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent npage = new Intent(MainActivity.this, ScoreActivity.class);
                npage.putExtra("NAME", PlayerName.getText().toString());
                startActivity(npage);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c < 7) {
                    if (!istart && matchCount < 6) {
                        istart = true;
                        start.setText("Stop");
                        handler.post(updateNumber);
                    } else if (istart) {
                        istart = false;
                        c++;
                        start.setText("Start");
                        handler.removeCallbacks(updateNumber);

                        int finalNum = Integer.parseInt(tvCurrent.getText().toString());
                        checkMatch(finalNum);
                    }
                }
            }
        });

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateNewNumbers();
                istart = false;
                start.setText("Start");
                pubGame++;
                matchCount = 0;
                updateCounter();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomExitDialog();
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

        if (current == n1) { highlight(tv1); pubCount++; }
        if (current == n2) { highlight(tv2); pubCount++; }
        if (current == n3) { highlight(tv3); pubCount++; }
        if (current == n4) { highlight(tv4); pubCount++; }
        if (current == n5) { highlight(tv5); pubCount++; }
        if (current == n6) { highlight(tv6); pubCount++; }
    }

    private void highlight(TextView tv) {
        if (tv.getCurrentTextColor() != Color.RED && matchCount < 6) {
            tv.setTextColor(Color.RED);
            matchCount++;
            updateCounter();
        }
    }

    private void updateCounter() {
        count.setText(matchCount + " of 6");
    }

    private void resetColors() {
        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
        tv5.setTextColor(Color.BLACK);
        tv6.setTextColor(Color.BLACK);
        c = 0;
    }

    public void showCustomExitDialog() {
Dialog d = new Dialog(MainActivity.this);
d.setContentView(R.layout.my_dialog);
Button yes =d.findViewById(R.id.positiveButton);
Button no =d.findViewById(R.id.negativeButton);
yes.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        finish();
        System.exit(0);
    }});
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();
    }
    private void showVerificationDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.verify);
        dialog.setCancelable(false);

        EditText nameInput = dialog.findViewById(R.id.nameInput);
        EditText ageInput = dialog.findViewById(R.id.age);
        Button goAhead = dialog.findViewById(R.id.button6);

        goAhead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = nameInput.getText().toString().trim();
                String inputAge = ageInput.getText().toString().trim();

                if(inputName.isEmpty() || inputAge.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter both name and age", Toast.LENGTH_SHORT).show();
                } else {
                    PlayerName.setText(inputName);
                    tvage.setText(inputAge);
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }
}
