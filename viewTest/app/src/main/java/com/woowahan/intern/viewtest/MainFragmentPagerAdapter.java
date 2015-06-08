package com.woowahan.intern.viewtest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.woowahan.intern.viewtest.textexample.TestExampleFragment;


/**
 * Created by user on 2015. 5. 28..
 */
public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES = {"BTN1", "BTN2"};//{ "Widget", "SystemInfo", "File", "Peoples" };

    private SparseArray<BaseFragment> mFragments;
    private int mainpos;

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new SparseArray<>();
        // ID set

    }

    public BaseFragment getFragment(int position){
        return mFragments.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        BaseFragment fragment = null;
        switch (position){
            case 0:
                // newInstance(id)
                fragment = TestExampleFragment.newInstance();
                break;

            case 1:
                fragment = TestExample2Fragment.newInstance();
                break;


        }
        if(fragment != null) {
            mFragments.put(position, fragment);
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
}
