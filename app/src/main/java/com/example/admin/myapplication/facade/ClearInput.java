package com.example.admin.myapplication.facade;

import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ClearInput {
    public void clear(ArrayList<EditText> arrayEditext){
        for(int i = 0; i< arrayEditext.size(); i++){
            arrayEditext.get( i ).setText( "" );
        }
    }
}