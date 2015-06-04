package com.woowahan.intern.viewtest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.woowahan.intern.viewtest.businessexample.TestExpandableListViewActivity;
import com.woowahan.intern.viewtest.textexample.TestExampleFragment;

/**
 * Created by Administrator on 2015-06-03.
 */
public class BusinessFragmentPagerAdapter extends FragmentPagerAdapter{

    private final String[] TITLES = {"MENU"};//{ "Widget", "SystemInfo", "File", "Peoples" };

    private SparseArray<BaseFragment> mFragments;

    public BusinessFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new SparseArray<>();

    }

    public BaseFragment getFragment(int position){
        return mFragments.get(position);
    }


    //////////////////////////////////////////// �����Ͻ� ���� ������ �����
    @Override
    public Fragment getItem(int position) {
        BaseFragment fragment = null;
        switch (position){
            case 0:
                fragment = TestExpandableListViewActivity.newInstance();
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
