package com.woowahan.intern.viewtest;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.astuetz.PagerSlidingTabStrip;

/**
 * Created by Administrator on 2015-06-03.
 */
public class BusinessActivity extends AppCompatActivity{

    private ViewPager mViewPager;
    private PagerSlidingTabStrip mPagerSlidingTabStrip;
    private BusinessFragmentPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        mPagerAdapter = new BusinessFragmentPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.business_viewpager);
        mViewPager.setAdapter(mPagerAdapter);

        mPagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.business_tabs);
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
