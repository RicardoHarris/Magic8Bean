package com.example.student.magic8bean;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.FloatMath;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

//MainActivity is Subclass of Superclass AppCompatActivity
//Creates public subClass(MainActivity) whose super class is AppCompatActivity
public class MainActivity extends AppCompatActivity {
    private TextView answerText;

    private SensorManager sensorMan;
    private Sensor accelerometer;
    private double accel;
    private double currentAccel;
    private double previousAccel;

    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            double x = event.values[0];
            double y = event.values[1];
            double z = event.values[2];

            previousAccel = currentAccel;
            currentAccel = Math.sqrt(x * x + y * y + z * z);
            double delta = currentAccel - previousAccel;
            accel = accel * 0.9 + delta;

            if(accel > 15) {
                Toast toast = Toast.makeText(getApplication(), "YOU SHUFFLED THE BEANS", Toast.LENGTH_SHORT);
                toast.show();
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.Really);
                mediaPlayer.start();
            }
        }
//Would call superFunction
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
//First action of app(onCreate)
    protected void onCreate(Bundle savedInstanceState) {
//calls onCreate within superClass with parameter of bundle savedInstanceStete
        super.onCreate(savedInstanceState);
//activity_main is an XML File(what you see) that
        setContentView(R.layout.activity_main);

        sensorMan = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        accel = 0.0f;
        currentAccel = SensorManager.GRAVITY_EARTH;
        previousAccel = SensorManager.GRAVITY_EARTH;

        answerText = (TextView) findViewById(R.id.answerText);
        answerText.setText(Predictions.get().getPrediction());
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorMan.registerListener(sensorListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorMan.unregisterListener(sensorListener);
    }
}