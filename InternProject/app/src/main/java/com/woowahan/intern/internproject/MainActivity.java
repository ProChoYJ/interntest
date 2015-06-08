package com.woowahan.intern.internproject;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;


public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private PagerSlidingTabStrip mPagerSlidingTabStrip;
    private MainFragmentPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("조영진 타이틀");


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

        MenuItem menuItem = menu.findItem(R.id.main_back_btn);

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
}
