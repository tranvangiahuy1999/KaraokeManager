package com.example.admin.myapplication.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.adapter.SnacksListAdapter;
import com.example.admin.myapplication.data_access.DatabaseAccess;
import com.example.admin.myapplication.model.snacks;

import java.util.ArrayList;

public class Enter_Room extends AppCompatActivity {
    ListView SnackListView;
    SnacksListAdapter adapter;
    ArrayList<snacks> snacksList = new ArrayList<>();
    TextView textRoomNameInfo, timeInView, snackPayView, sumPayView;
    Button payRoomInfoBtn, exitRoomInfoBtn;
    com.example.admin.myapplication.model.room room;
    Dialog quantityDialog;
    EditText quantityOrderView;
    Button addBtn;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_room);
        mContext = this;

        room = (com.example.admin.myapplication.model.room) getIntent().getSerializableExtra("room");
        addControls();
        addView();
        onClickListView();

        exitRoomInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Enter_Room.this, WorkplaceActivity.class);
                startActivity(intent);
                finish();
            }
        });

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        Cursor timeIn = databaseAccess.getRoomTimeIn(room.getRoomID());
        Cursor total = databaseAccess.getRoomTotalPayment(room.getRoomID());

        textRoomNameInfo.setText(room.getRoomName());
        if(timeIn.moveToFirst() && timeIn.getCount() > 0 && total.moveToFirst() && total.getCount() > 0) {
            timeInView.setText("Thời gian mở: " + timeIn.getString(0));
            snackPayView.setText("Tạm thanh toán: " + total.getString(0));
        }
        databaseAccess.close();
    }

    public void addControls(){
        SnackListView = findViewById(R.id.SnackListView);
        textRoomNameInfo = findViewById(R.id.textRoomNameInfo);
        timeInView = findViewById(R.id.timeInView);
        snackPayView = findViewById(R.id.snackPayView);
        sumPayView = findViewById(R.id.sumPayView);
        payRoomInfoBtn = findViewById(R.id.payRoomInfoBtn);
        exitRoomInfoBtn = findViewById(R.id.exitRoomInfoBtn);
    }

    public void addView(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        Cursor cursor = databaseAccess.viewSnackData();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data to show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                snacksList.add(new snacks(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getFloat(3)));
            }

            adapter = new SnacksListAdapter(this , snacksList);
            SnackListView.setAdapter(adapter);
        }
        databaseAccess.close();
    }

    public void showQuantity(final snacks snack){
        quantityDialog = new Dialog(this);
        quantityDialog.setContentView(R.layout.order_snack_popup);
        addControls1();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(mContext);
                databaseAccess.open();

                final Cursor snackmoney = databaseAccess.getSnackMoney(snack.getSnack_id());
                String quantity = quantityOrderView.getText().toString();
                if(chkEditText(quantityOrderView)){
                    if(snackmoney != null && snackmoney.moveToFirst()) {
                        Cursor roomtotal = databaseAccess.getRoomTotalPayment(room.getRoomID());
                        float total = 0;
                        if(roomtotal.getCount() > 0 && roomtotal.moveToFirst() && snackmoney.getCount() > 0 && snackmoney.moveToFirst()){
                            total = roomtotal.getFloat(0) + snackmoney.getFloat(0) * Float.parseFloat(quantity);
                        }
                        snackPayView.setText("Tạm thanh toán: " + total);
                        databaseAccess.updateRoomTotal(room.getRoomID(), total);
                    }
                }

                Toast.makeText(v.getContext(), "Đặt thành công",Toast.LENGTH_SHORT).show();
                quantityDialog.dismiss();

                databaseAccess.close();
            }
        });

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        quantityDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        quantityDialog.getWindow().setLayout((6 * width)/7, (4 * height)/5);
        quantityDialog.show();
    }

    public void addControls1(){
        quantityOrderView = quantityDialog.findViewById(R.id.quantityOrderView);
        addBtn = quantityDialog.findViewById(R.id.addBtn);
    }

    private void onClickListView(){
        SnackListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = SnackListView.getItemAtPosition(position);
                snacks snack = (snacks) o;
                showQuantity(snack);
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