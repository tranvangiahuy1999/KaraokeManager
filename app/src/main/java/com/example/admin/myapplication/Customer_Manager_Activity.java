package com.example.admin.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.myapplication.database.DatabaseAccess;

public class Customer_Manager_Activity extends AppCompatActivity {
    EditText cusnameView, phoneView, cusaddView;
    Button addCusBtn;

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
                String cusName = cusnameView.getText().toString();
                String cusPhone = phoneView.getText().toString();
                String cusAddress = cusaddView.getText().toString();

                if(chkEditText(cusnameView) && chkEditText(phoneView) && chkEditText(cusaddView)){
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                    databaseAccess.open();

                    boolean cursor = databaseAccess.insertCustomer(cusName, cusPhone, cusAddress);

                    if(cursor){
                        Toast.makeText(getApplicationContext(), "Thêm khách hàng thành công", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    private boolean chkEditText(EditText editText) {
        if (editText.getText().toString().trim().length() > 0)
            return true;
        else {
            editText.setError("Xin kiểm tra lại thông tin đã nhập");
        }
        return false;
    }
}
