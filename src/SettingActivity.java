package com.example.a327_prototype_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
//setting activity
public class SettingActivity extends AppCompatActivity {
    Button openMap;//open the map
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.setting);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.bear://bottom selection to Bear Activity
                        startActivity(new Intent(getApplicationContext(),BearActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home://bottom selection to Home Activity
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.setting://bottom selection to Bear Activity, which still stay in same page
                        return true;
                    case R.id.recycle://bottom selection to Recycle Activity
                        startActivity(new Intent(getApplicationContext(),RecycleActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        openMap = findViewById(R.id.button_maps);
        openMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//click to start the function for activity
                Intent intent = new Intent(SettingActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}
