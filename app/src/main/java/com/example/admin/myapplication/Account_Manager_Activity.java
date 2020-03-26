package com.example.admin.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.myapplication.database.DatabaseAccess;

public class Account_Manager_Activity extends AppCompatActivity {
    EditText usernameView, passwordView;
    Button addAccBtn;

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
                String username = usernameView.getText().toString();
                String password = passwordView.getText().toString();

                if(chkEditText(usernameView) && chkEditText(passwordView)){
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                    databaseAccess.open();

                    boolean cursor = databaseAccess.insertAccount(username, password);

                    if(cursor){
                        Toast.makeText(getApplicationContext(), "Thêm tài khoản thành công", Toast.LENGTH_SHORT).show();
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
