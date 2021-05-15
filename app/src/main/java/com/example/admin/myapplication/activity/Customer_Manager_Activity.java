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

public class Customer_Manager_Activity extends AppCompatActivity {
    EditText cusnameView, phoneView, cusaddView;
    Button addCusBtn;
    FacadeImplement facadeImplement = new FacadeImplement();
    ArrayList<EditText> arrayEditText = new ArrayList<EditText>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_manager);

        cusnameView = findViewById(R.id.cusnameView);
        phoneView = findViewById(R.id.phoneView);
        cusaddView = findViewById(R.id.cusaddView);
        addCusBtn = findViewById(R.id.addCusBtn);

        addCusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayEditText.add(cusnameView);
                arrayEditText.add(phoneView);
                arrayEditText.add(cusaddView);
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                facadeImplement.addInformation(arrayEditText,"customer", databaseAccess, getApplicationContext());
            }
        });

    }
}
