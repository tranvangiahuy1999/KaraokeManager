package com.example.admin.myapplication.facade;

import android.widget.EditText;

import java.util.ArrayList;

public class GetInputValue {
    public ArrayList<String> getValue(ArrayList<EditText> arrayEditText, String type){
        ArrayList<String> data = new ArrayList<String>();
        switch (type){
            case "account":
                // get username and password
                data.add(arrayEditText.get(0).getText().toString());
                data.add( arrayEditText.get(1).getText().toString() );
                break;
            case "customer":
                // get customer name, phone, address
                data.add(arrayEditText.get(0).getText().toString());
                data.add( arrayEditText.get(1).getText().toString() );
                data.add( arrayEditText.get(2).getText().toString() );
                break;
        }
        return data;
    }
}
