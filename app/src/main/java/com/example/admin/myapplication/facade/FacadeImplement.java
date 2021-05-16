package com.example.admin.myapplication.facade;

import android.content.Context;
import android.widget.EditText;

import com.example.admin.myapplication.data_access.DatabaseAccess;

import java.util.ArrayList;

public class FacadeImplement {

    // initial data for input value
    public ArrayList<String> data;

    public void addInformation(ArrayList<EditText> arrayEditText, String type, DatabaseAccess databaseAccess, Context context) {
        // call service
       CheckInputValue validator = new CheckInputValue();
       OutputMessage output = new OutputMessage();
        ClearInput clearInput = new ClearInput();
        GetInputValue inputValue = new GetInputValue();
        AddToDatabase database = new AddToDatabase();

        // check input value in editText
        boolean isValidValue = validator.checkEditText( arrayEditText );
        if (isValidValue) {
            // get input value after success validation
            data = inputValue.getValue(arrayEditText, type);

            // add value to database
            boolean isAddedToDatabase = database.addNewData(data,databaseAccess);

            // clear input after adding to database
            clearInput.clear( arrayEditText );

            // send output message (error / success)
            output.sendMessage(context, isAddedToDatabase, type);
        }
    }
}
