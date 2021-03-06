package com.example.admin.myapplication.activity;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.adapter.MainViewPagerAdapter;
import com.example.admin.myapplication.fragment.roomap_fragment;
import com.example.admin.myapplication.fragment.option_fragment;
import com.example.admin.myapplication.fragment.payment_fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WorkplaceActivity extends AppCompatActivity {

    public TabLayout tabLayout;
    public ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workplace);
        addCreate();
        viewPagerController();
    }

    public void addCreate(){
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
    }

    public void viewPagerController(){
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new roomap_fragment(),"SƠ ĐỒ");
        mainViewPagerAdapter.addFragment(new payment_fragment(), "HOÁ ĐƠN");
        mainViewPagerAdapter.addFragment(new option_fragment(),"TUỲ CHỌN");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.map);
        tabLayout.getTabAt(1).setIcon(R.drawable.menu);
        tabLayout.getTabAt(2).setIcon(R.drawable.option);
    }
}
