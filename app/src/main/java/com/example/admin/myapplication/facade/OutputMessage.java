package com.example.admin.myapplication.facade;

import android.content.Context;
import android.widget.Toast;

public class OutputMessage {
    public void sendMessage(Context context, boolean success, String type){
        String text;
        if(type == "account"){
            text = "tài khoản";
        } else{
            text = "thông tin khách hàng";
        }
        if(success){
            Toast.makeText(context, "Thêm " + text + " thành công", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(context, "Thêm "+ text +" thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}

