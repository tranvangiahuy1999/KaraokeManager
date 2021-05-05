package com.example.admin.myapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.admin.myapplication.activity.Account_Manager_Activity;
import com.example.admin.myapplication.activity.Customer_Manager_Activity;
import com.example.admin.myapplication.activity.MainActivity;
import com.example.admin.myapplication.R;
import com.example.admin.myapplication.log.LogWriter;
import com.example.admin.myapplication.model.account;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fragment_opTion extends Fragment {

    View viewSetting;
    Button accountManagerBtn,customerManagerBtn,signOutBtn;
    Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewSetting = inflater.inflate(R.layout.activity_fragment_setting, container, false);
        accountManagerBtn = viewSetting.findViewById(R.id.accountManagerBtn);
        customerManagerBtn = viewSetting.findViewById(R.id.customerManagerBtn);
        signOutBtn = viewSetting.findViewById(R.id.signOutBtn);
        mContext = container.getContext();

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
                getActivity().finish();
                String username = account.getInstance().getUsername();
                SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy-hh:mm:ss");
                String format = s.format(new Date());
                LogWriter.getInstance().appendLog(mContext.getApplicationContext(),"Username: " + username + ",Activity: Logout,TimeStamp: " + format);
            }
        });

        accountManagerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Account_Manager_Activity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        customerManagerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Customer_Manager_Activity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return viewSetting;
    }
}
