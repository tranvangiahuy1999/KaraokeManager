package com.example.admin.myapplication.facade;

import android.widget.Toast;

import com.example.admin.myapplication.data_access.DatabaseAccess;

import java.util.ArrayList;

public class AddToDatabase {
    public boolean addNewData(ArrayList<String> data,DatabaseAccess databaseAccess){
        boolean cursor;
        databaseAccess.open();

        if(data.size() == 2){
            cursor = databaseAccess.insertAccount(data.get( 0 ), data.get( 1 ));
        } else{
            cursor = databaseAccess.insertCustomer(data.get( 0 ), data.get( 1 ), data.get( 2 ));
        }
        return cursor;
    }
}
