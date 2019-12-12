package com.example.a327_prototype_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddRecycleActivity extends AppCompatActivity {
    TextView scoreCounter;
    Button addPaper, addMetal, addGlass, zero;
    int score = 0;
    EditText editTextPaper, editTextMetal, editTextGlass;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recycle);
        scoreCounter = findViewById(R.id.textView_No_1);
        addPaper = findViewById(R.id.button_paper);
        addMetal = findViewById(R.id.button_metal);
        addGlass = findViewById(R.id.button_glass);
        editTextPaper = findViewById(R.id.editText_paper);
        editTextMetal = findViewById(R.id.editText_metal);
        editTextGlass = findViewById(R.id.editText_glass);
        zero = findViewById(R.id.button_zero);

        editTextPaper.setGravity(Gravity.CENTER_HORIZONTAL);
        editTextMetal.setGravity(Gravity.CENTER_HORIZONTAL);
        editTextGlass.setGravity(Gravity.CENTER_HORIZONTAL);

        // load score
        SharedPreferences myScore = this.getSharedPreferences("MyMyScore",Context.MODE_PRIVATE);
        score = myScore.getInt("score", 0);
        // you must setText to update the score every time
        scoreCounter.setText("Score: " + score);




        addPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = editTextPaper.getText().toString();
                if(number.isEmpty()) {
                    editTextPaper.setError("Enter a number!");
                } else {
                    int x = Integer.parseInt(editTextPaper.getText().toString());
                    score += 10 * x;
                    editTextPaper.setText("");
                    // save score
                    SharedPreferences myScore = getSharedPreferences("MyMyScore", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = myScore.edit();
                    editor.putInt("score", score);
                    editor.commit();
                    scoreCounter.setText("Score: " + score);
                }

            }
        });

        addMetal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = editTextMetal.getText().toString();
                if(number.isEmpty()) {
                    editTextMetal.setError("Enter a number!");
                } else {
                    int x = Integer.parseInt(editTextMetal.getText().toString());
                    score += 20 * x;
                    editTextMetal.setText("");

                    // save score
                    SharedPreferences myScore = getSharedPreferences("MyMyScore", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = myScore.edit();
                    editor.putInt("score", score);
                    editor.commit();

                    scoreCounter.setText("Score: " + score);

                }

            }
        });

        addGlass.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String number = editTextGlass.getText().toString();
                if(number.isEmpty()) {
                    editTextGlass.setError("Enter a number!");
                } else {
                    int x = Integer.parseInt(editTextGlass.getText().toString());
                    score += 20 * x;
                    editTextGlass.setText("");

                    // save score
                    SharedPreferences myScore = getSharedPreferences("MyMyScore", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = myScore.edit();
                    editor.putInt("score", score);
                    editor.commit();

                    scoreCounter.setText("Score: " + score);
                }

            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = 0;
                editTextPaper.setText("");
                editTextGlass.setText("");
                editTextMetal.setText("");

                // save score
                SharedPreferences myScore = getSharedPreferences("MyMyScore", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = myScore.edit();
                editor.putInt("score", score);
                editor.commit();

                scoreCounter.setText("Score: " + score);
            }
        });
    }
}
