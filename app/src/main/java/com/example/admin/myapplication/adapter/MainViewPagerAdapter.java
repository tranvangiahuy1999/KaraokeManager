package com.example.admin.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.ArrayList;

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> arrayFragment = new ArrayList<>();
    private ArrayList<String> arrayTitle = new ArrayList<>();

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return arrayFragment.get(position);
    }

    @Override
    public int getCount() {
        return arrayFragment.size();
    }
    public void addFragment(Fragment fragment, String title){
        arrayTitle.add(title);
        arrayFragment.add(fragment);
    }

    @Override
    public CharSequence getPageTitle(int position){
        return arrayTitle.get(position);
    }
}
