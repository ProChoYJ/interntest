package com.woowahan.intern.internproject;

import android.location.Location;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import android.os.Handler;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.logging.LogRecord;

/**
 * Created by user on 2015. 6. 9..
 */
public class SplashActivity extends AppCompatActivity{
    private Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        Handler handeler = new Handler() {

            public void handleMessage(Message msg){
                   finish();
            }

        };

        handeler.sendEmptyMessageDelayed(0,2000);

    }

}
