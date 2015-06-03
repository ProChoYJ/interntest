package com.woowahan.intern.viewtest;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.astuetz.PagerSlidingTabStrip;

/**
 * Created by user on 2015. 6. 3..
 */
public class SubActivity extends AppCompatActivity {


    private ViewPager mViewPager;
    private PagerSlidingTabStrip mPagerSlidingTabStrip;
    private MainFragmentPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        mPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(mPagerAdapter);
        mPagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        mPagerSlidingTabStrip.setViewPager(mViewPager);
    }

    @Override
    public void onBackPressed() {

        BaseFragment curFragment = mPagerAdapter.getFragment(mViewPager.getCurrentItem());
        if(curFragment != null && curFragment.onBackPressed()){
            return ;
        }

        super.onBackPressed();
    }
}
