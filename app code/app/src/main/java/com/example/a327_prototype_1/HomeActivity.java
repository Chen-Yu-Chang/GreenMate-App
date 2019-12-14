package com.example.a327_prototype_1;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements SensorEventListener, StepListener {

    private TextView TvSteps;
    private Button BtnStop;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;
    Globals g = Globals.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);
        TvSteps = findViewById(R.id.textView_steps);

        BtnStop = findViewById(R.id.button_stop);






        sensorManager.registerListener(HomeActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);

        // load score
        SharedPreferences mySteps = this.getSharedPreferences("nameSteps",Context.MODE_PRIVATE);
        numSteps = mySteps.getInt("numSteps", 0);
        // you must setText to update the score every time
        TvSteps.setText(TEXT_NUM_STEPS + numSteps);
        g.setDataSteps(numSteps);




        BtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //sensorManager.unregisterListener(HomeActivity.this);
                numSteps = 0;
                // save score
                SharedPreferences mySteps = getSharedPreferences("nameSteps", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mySteps.edit();
                editor.putInt("numSteps", numSteps);
                editor.commit();
                TvSteps.setText(TEXT_NUM_STEPS + numSteps);


            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.bear:
                        startActivity(new Intent(getApplicationContext(),BearActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
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

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    @Override
    public void step(long timeNs) {
        numSteps++;
        // save score
        SharedPreferences mySteps = getSharedPreferences("nameSteps", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySteps.edit();
        editor.putInt("numSteps", numSteps);
        editor.commit();
        TvSteps.setText(TEXT_NUM_STEPS + numSteps);




    }



}
