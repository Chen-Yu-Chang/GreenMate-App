package com.example.a327_prototype_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
//recycle activity
public class RecycleActivity extends AppCompatActivity {
    private Button database;//database button
    private Button startRecycle;//recycle button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.recycle);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.bear://bottom selection of bear to Bear Activity
                        startActivity(new Intent(getApplicationContext(),BearActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home://bottom selection of home to Home Activity
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.setting://bottom selection of setting to Setting Activity
                        startActivity(new Intent(getApplicationContext(),SettingActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.recycle://bottom selection of recycle, which stays in the same page
                        return true;
                }
                return false;
            }
        });
        database = findViewById(R.id.button_database);
        startRecycle = findViewById(R.id.button_addPoints);
        database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//clicking on database button to start activity
                Intent intent = new Intent(RecycleActivity.this,DatabaseActivity.class);
                startActivity(intent);
            }
        });

        startRecycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//clicking on recycle button to start activity
                Intent intent = new Intent(RecycleActivity.this,AddRecycleActivity.class);
                startActivity(intent);
            }
        });

    }
}
