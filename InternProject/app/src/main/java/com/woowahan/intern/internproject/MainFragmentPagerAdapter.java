package com.woowahan.intern.internproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.woowahan.intern.internproject.business.BusinessListFragment;
import com.woowahan.intern.internproject.main.MainListFragment;

/**
 * Created by user on 2015. 6. 7..
 */
public class MainFragmentPagerAdapter extends FragmentPagerAdapter{

    private final String[] TITLES = {"홈", "치킨","중식","피자"};//{ "Widget", "SystemInfo", "File", "Peoples" };

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
//        mainpos = position;
        switch (position){
            case 0:
                // newInstance(id)
                MainListFragment mlf = MainListFragment.newInstance();
                mlf.setCategoryNum(0);
                fragment = mlf;
                break;

            case 1:
                BusinessListFragment blf = BusinessListFragment.newInstance();
                blf.setBusinessId(1);
                fragment = blf;
                break;
            case 2:
                BusinessListFragment cblf = BusinessListFragment.newInstance();
                cblf.setBusinessId(2);
                fragment = cblf;
                break;
            case 3:
                BusinessListFragment pblf = BusinessListFragment.newInstance();
                pblf.setBusinessId(3);
                fragment = pblf;
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

    //// pos getter setter

    public int getMainpos() {
        return mainpos;
    }

    public void setMainpos(int mainpos) {
        this.mainpos = mainpos;
    }
}
