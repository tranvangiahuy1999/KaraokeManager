package com.example.admin.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.data_access.DatabaseAccess;
import com.example.admin.myapplication.facade.FacadeImplement;

import java.util.ArrayList;

public class Account_Manager_Activity extends AppCompatActivity {
    EditText usernameView, passwordView;
    Button addAccBtn;
    FacadeImplement facadeImplement = new FacadeImplement();
    ArrayList<EditText> arrayEditText = new ArrayList<EditText>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manager);


        usernameView = findViewById(R.id.usernameView);
        passwordView = findViewById(R.id.passwordView);
        addAccBtn = findViewById(R.id.addAccBtn);

        addAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                arrayEditText.add(usernameView);
                arrayEditText.add(passwordView);
                facadeImplement.addInformation(arrayEditText,"account", databaseAccess,getApplicationContext());

            }

        });
    }

}
