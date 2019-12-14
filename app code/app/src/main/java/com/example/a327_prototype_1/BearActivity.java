package com.example.a327_prototype_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BearActivity extends AppCompatActivity {
    ImageView bear;
    TextView textView1, textView2;
    Globals g = Globals.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bear);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bear = findViewById(R.id.imageView_bear);
        textView1 = findViewById(R.id.textView_home_step);
        textView2 = findViewById(R.id.textView_home_recycle);
        textView2.setText("" + g.getDataRecycle());
        textView1.setText("" + g.getDataSteps());

        int steps = g.getDataSteps();
        if(steps < 10) {
            bear.setImageResource(R.drawable.bear_sad_sitting);
        } else if(steps < 20 && steps >= 10) {
            bear.setImageResource(R.drawable.bear_mad);
        } else if(steps >= 20) {
            bear.setImageResource(R.drawable.bear_happy);
        }








        bottomNavigationView.setSelectedItemId(R.id.bear);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.bear:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.setting:
                        startActivity(new Intent(getApplicationContext(),SettingActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.recycle:
                        startActivity(new Intent(getApplicationContext(),RecycleActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}