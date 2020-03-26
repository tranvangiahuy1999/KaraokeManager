package com.example.admin.myapplication;

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

import com.example.admin.myapplication.adapter.SnacksListAdapter;
import com.example.admin.myapplication.database.DatabaseAccess;
import com.example.admin.myapplication.objects.snacks;

import java.util.ArrayList;

public class Enter_Room extends AppCompatActivity {
    ListView SnackListView;
    SnacksListAdapter adapter;
    ArrayList<snacks> snacksList;
    TextView textRoomNameInfo, timeInView, roomPayView, snackPayView, sumPayView;
    Button changeRoomInfoBtn, payRoomInfoBtn, exitRoomInfoBtn;
    com.example.admin.myapplication.objects.room room;
    Dialog quantityDialog;
    EditText quantityOrderView;
    Button addBtn;
    float temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_room);

        snacksList = new ArrayList<>();

        room = (com.example.admin.myapplication.objects.room) getIntent().getSerializableExtra("room");
        addControls();
        addView();
        onClickListView();
        textRoomNameInfo.setText(room.getRoomName());
        timeInView.setText("Thời gian vào: " + room.getTimeIn());
    }

    public void addControls(){
        SnackListView = findViewById(R.id.SnackListView);
        textRoomNameInfo = findViewById(R.id.textRoomNameInfo);
        timeInView = findViewById(R.id.timeInView);
        roomPayView = findViewById(R.id.roomPayView);
        snackPayView = findViewById(R.id.snackPayView);
        sumPayView = findViewById(R.id.sumPayView);
        changeRoomInfoBtn = findViewById(R.id.changeRoomInfoBtn);
        payRoomInfoBtn = findViewById(R.id.payRoomInfoBtn);
        exitRoomInfoBtn = findViewById(R.id.exitRoomInfoBtn);
    }

    public void showQuantity(snacks snack){
        quantityDialog = new Dialog(this);
        quantityDialog.setContentView(R.layout.order_snack_popup);
        addControls1();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        final Cursor cursor = databaseAccess.getSnackMoney(snack.getSnack_id());

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantity = quantityOrderView.getText().toString();
                if(chkEditText(quantityOrderView)){
                    if(cursor != null && cursor.moveToFirst()) {
                        temp = room.getSnackMoney() + cursor.getFloat(0) * Float.parseFloat(quantity);
                        Log.d("DDD",Float.toString(temp));
                        room.setSnackMoney(temp);
                    }
                }

                Toast.makeText(v.getContext(), "Đặt món thành công",Toast.LENGTH_SHORT).show();
                quantityDialog.dismiss();
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
    }

    private void onClickListView(){
        SnackListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = SnackListView.getItemAtPosition(position);
                snacks snack = (snacks) o;

                showQuantity(snack);

//                Intent intent = new Intent(Enter_Room.this, Order_Activity.class);
//                intent.putExtra("room", room);
//                intent.putExtra("snack", snack);
//                startActivity(intent);
//                finish();
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
