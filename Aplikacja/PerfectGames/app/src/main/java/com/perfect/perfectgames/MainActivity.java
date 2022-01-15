package com.perfect.perfectgames;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;




public class MainActivity extends AppCompatActivity {
    private GpsTracker gpsTracker;
    private static int time_out = 3000;
    int PERMISSION_ID = 44;
    TextView tst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
     //   tst = (TextView) findViewById(R.id.test);

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        getLocation();
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               Intent login = new Intent(MainActivity.this, Login.class);
               startActivity(login);
               finish();
           }
       },time_out);
    }
    public void getLocation(){
        gpsTracker = new GpsTracker(MainActivity.this);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            Log.v("GPS", String.valueOf(latitude));
            Log.v("GPS", String.valueOf(longitude));
        }else{
            gpsTracker.showSettingsAlert();
        }
    }
    }
