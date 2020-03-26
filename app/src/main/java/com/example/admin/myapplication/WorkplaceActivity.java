package com.example.admin.myapplication;

import com.example.admin.myapplication.adapter.MainViewPagerAdapter;
import com.example.admin.myapplication.fragment.Fragment_roomMap;
import com.example.admin.myapplication.fragment.Fragment_opTion;
import com.example.admin.myapplication.fragment.Fragment_payMent;

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
        mainViewPagerAdapter.addFragment(new Fragment_roomMap(),"SƠ ĐỒ");
        mainViewPagerAdapter.addFragment(new Fragment_payMent(), "HOÁ ĐƠN");
        mainViewPagerAdapter.addFragment(new Fragment_opTion(),"TUỲ CHỌN");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.map);
        tabLayout.getTabAt(1).setIcon(R.drawable.menu);
        tabLayout.getTabAt(2).setIcon(R.drawable.option);
    }
}
