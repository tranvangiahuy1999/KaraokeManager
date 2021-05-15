package com.example.admin.myapplication.facade;

import android.widget.EditText;

import java.util.ArrayList;

public class CheckInputValue {
    public boolean checkEditText(ArrayList<EditText> arrayEditText) {
        boolean result = true;
        for(int i = 0; i < arrayEditText.size(); i++){
            EditText editText = arrayEditText.get( i );
            if (editText.getText().toString().trim().length() <= 0){
                editText.setError("Xin kiểm tra lại thông tin đã nhập");
                result = false;
                break;
            }
        }
        return result;
    }

}
