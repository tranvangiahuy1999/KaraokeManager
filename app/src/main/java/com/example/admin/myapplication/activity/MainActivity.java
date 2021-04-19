package com.example.admin.myapplication.activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.data_access.DatabaseAccess;
import com.example.admin.myapplication.model.account;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    public static final String TAG = MainActivity.class.getSimpleName();

    EditText userName;
    EditText passWord;
    Button loGin;
    ProgressDialog pDialog;
    ArrayList<account> accountList;

//    public static final String URL_LOGIN = "https://tranvangiahuy1999.000webhostapp.com/KaraokeManager/Scripts/login.php";
//    public static final String KEY_USERNAME = "username";
//    public static final String KEY_PASSWORD = "password";

// anh nam

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addControls();
        addClickEvent();
    }

    private void addControls(){
        userName = findViewById(R.id.editText);
        passWord = findViewById(R.id.editText2);
        loGin = findViewById(R.id.button);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Log in...");
        pDialog.setCanceledOnTouchOutside(false);
    }

    private void addClickEvent(){
        loGin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userName.getText().toString();
                String password = passWord.getText().toString();

                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());

                databaseAccess.open();
                boolean cursor = databaseAccess.checkAccount(username, password);

                if(chkEditText(userName) && chkEditText(passWord)){
                    if(cursor){
                        accountList = new ArrayList<>();
                        Intent intent = new Intent(MainActivity.this, WorkplaceActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Tài khoản của bạn không tồn tại",Toast.LENGTH_SHORT).show();
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