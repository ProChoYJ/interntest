package com.woowahan.intern.internproject;

import android.app.ActionBar;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private ViewPager mViewPager;
    private PagerSlidingTabStrip mPagerSlidingTabStrip;
    private MainFragmentPagerAdapter mPagerAdapter;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, SplashActivity.class));

        setTitle("배달 헬미");

//        buildGoogleApiClient();
        // construcor set intent data -> ID
        mPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mViewPager.setAdapter(mPagerAdapter);
        mPagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);

        // tab full
        //mPagerSlidingTabStrip.setShouldExpand(true);

        mPagerSlidingTabStrip.setViewPager(mViewPager);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//

        return super.onOptionsItemSelected(item);
    }

    ///////// back event
    @Override
    public void onBackPressed() {

        BaseFragment curFragment = mPagerAdapter.getFragment(mViewPager.getCurrentItem());
        if(curFragment != null && curFragment.onBackPressed()){
            return ;
        }

        super.onBackPressed();
    }


    ///////// setfragment
    public void setCurrentItem(int pagerIndex) {
        mViewPager.setCurrentItem(pagerIndex);
    }


    ///// google service

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle connectionHint) {


        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {

            GPSLocation.getInstance().setLat("" + mLastLocation.getLatitude());
            Log.d("json", "" + GPSLocation.getInstance().getLat());

            GPSLocation.getInstance().setLng("" + mLastLocation.getLongitude());
            Log.d("json", "" + GPSLocation.getInstance().getLng());
        }
    }



    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        //Todo Google Play Service Fail 에러처리.
        Log.e("json", "connection fail");
    }

}
